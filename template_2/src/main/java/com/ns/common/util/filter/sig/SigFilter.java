package com.ns.common.util.filter.sig;

import com.ns.common.util.constant.FilterConstant;
import com.ns.common.util.constant.PathConstant;
import com.ns.common.util.constant.SigConstant;
import com.ns.common.util.filter.spi.AbsFilter;
import com.ns.common.util.sig.SigUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SigFilter extends AbsFilter {
    private static Log logger = LogFactory.getLog(SigFilter.class);

    @Override
    protected String[] getIgnorePaths() {
        return FilterConstant.SIG_FILTER_IGNORE_PATHS;
    }

    @Override
    protected String[] getFilterPaths() {
        return FilterConstant.SIG_FILTER_PATHS;
    }

    @Override
    protected boolean checkRequest(HttpServletRequest request) {
        Map<String, String> params = getParam(request);
        if (!params.containsKey(SigConstant.PARAM_TIME_STAMP)) {
            return false;
        }
        if (!params.containsKey(SigConstant.PARAM_SIG)) {
            return false;
        }

        String sig = params.remove(SigConstant.PARAM_SIG);
        try {
            if (SigUtil.isIgnoreSig(sig)) {
                return true;
            }
        } catch (Throwable e) {
            logger.warn("", e);
            return false;
        }

        try {
            if(!SigUtil.isValidSig(sig, new ArrayList<>(params.values()))) {
                return false;
            }
        } catch (Throwable e) {
            logger.warn("", e);
            return false;
        }

        try {
            long clientTime = Long.parseLong(params.get(SigConstant.PARAM_TIME_STAMP));
            if (Math.abs((clientTime - System.currentTimeMillis())) > SigConstant.EXPIRE_TIME) {
                return false;
            }
        } catch (Throwable e) {
            logger.warn("", e);
            return false;
        }

        return true;
    }

    @Override
    protected void handleBadRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(PathConstant.INVALID_SIG).forward(request, response);
    }

    /**
     * 获取GET请求中URL中的参数，和POST请求request body中的数据
     * @param request
     * @return
     */
    private Map<String, String> getParam(ServletRequest request) {
        Map<String, String> params = new HashMap<>();
        // 获取请求参数
        Map parameterMap = request.getParameterMap();
        if (parameterMap != null && !parameterMap.isEmpty()) {
            Iterator iterator = parameterMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next().toString();
                String value = request.getParameter(key);
                params.put(key, value);
            }
        }

        return params;
    }
}
