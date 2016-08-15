package com.ns.common.util.checker;

import com.ns.common.bean.mongo.Mark;
import com.ns.common.util.bean.MarkUtil;
import com.ns.common.util.exception.sys.ParameterException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * Created by xuezhucao on 16/4/11.
 */
@Component
public class MarkChecker {
    private static Log logger = LogFactory.getLog(MarkChecker.class);

    public void checkCreate(Mark mark) throws Throwable {
        if (mark == null) {
            throw new ParameterException("mark为空");
        }
        if (!MarkUtil.isValidType(mark.getType())) {
            throw new ParameterException("无效的埋点类型");
        }
    }

}
