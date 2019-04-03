package com.tl.o2o.exception;


/**
 * 通用异常，事务回滚
 *
 * @author tangli
 * @create 2019-3-29 下午12:55
 */

public class CommonOperationException extends RuntimeException {

    private static final long serialVersionUID = -5280345145209690578L;

    public CommonOperationException(String errorMsg) {
        super(errorMsg);
    }
}
