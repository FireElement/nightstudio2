package com.ns.common.biz;

import com.ns.common.bean.mongo.Mark;
import com.ns.common.dao.MarkDao;
import com.ns.common.util.checker.MarkChecker;
import com.ns.common.util.constant.MarkConstant;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.uuid.UuidUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuezhucao on 16/8/4.
 */
@Service
public class MarkBiz {
    private static Log logger = LogFactory.getLog(MarkBiz.class);
    @Resource
    private MarkChecker checker;
    @Resource
    private MarkDao dao;

    public Mark getByValue(String value) throws Throwable {
        Mark result = dao.findByValue(value);
        if (result == null) {
            throw new NSException(ErrorCode.MARK_NOT_EXIST);
        }
        return result;
    }

    public Mark create(Mark mark) throws Throwable {
        checker.checkCreate(mark);
        for (int count = 0; count < MarkConstant.MAX_CREATE_TRY_COUNT; count++) {
            String value = UuidUtil.getUuid(MarkConstant.VALUE_LENGTH);
            mark.setValue(value);
            try {
                return dao.save(mark);
            } catch (Throwable e) {
                logger.warn("", e);
            }
        }
        throw new NSException(ErrorCode.CREATE_MARK_FAIL);
    }
}
