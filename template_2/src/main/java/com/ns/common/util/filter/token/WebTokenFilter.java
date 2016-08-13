package com.ns.common.util.filter.token;

import com.ns.common.biz.WebTokenBiz;
import com.ns.common.util.constant.FilterConstant;
import com.ns.common.util.constant.PathConstant;
import com.ns.common.util.constant.RequestConstant;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.filter.spi.AbsFilter;
import com.ns.common.util.spring.SpringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by caoxuezhu01 on 14-9-21.
 */
public class WebTokenFilter extends AbsFilter {
    private static Log logger = LogFactory.getLog(WebTokenFilter.class);
    private static WebTokenBiz tokenBiz = null;
    private static Object lock = new Object();

    private static WebTokenBiz getTokenBiz() {
        if (tokenBiz == null) {
            synchronized (lock) {
                if (tokenBiz == null) {
                    try {
                        tokenBiz = (WebTokenBiz) SpringUtil.getBean(WebTokenBiz.class);
                    } catch (NSException e) {
                        logger.warn("", e);
                    }
                }
            }
        }
        return tokenBiz;
    }

    @Override
    protected String[] getIgnorePaths() {
        return FilterConstant.WEB_TOKEN_FILTER_IGNORE_PATHS;
    }

    @Override
    protected String[] getFilterPaths() {
        return FilterConstant.WEB_TOKEN_FILTER_PATHS;
    }

    @Override
    protected boolean checkRequest(HttpServletRequest request) {
        String token = request.getParameter(RequestConstant.TOKEN);
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        WebTokenBiz tokenBiz = getTokenBiz();
        try {
            tokenBiz.check(token);
        } catch (Throwable e) {
            return false;
        }
        return true;
    }

    @Override
    protected void handleBadRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(PathConstant.WEB_INVALID_TOKEN).forward(request, response);
    }

}
