package com.tl.o2o.exception;

/**
 * 店铺操作相关异常
 * 可以中断回滚事务
 *
 * @author tangli
 * @create 2018-11-06 下午2:21
 **/
public class ShopOperationException extends RuntimeException {


	private static final long serialVersionUID = 9053958791832799173L;

	public ShopOperationException(String errorMsg) {
		super(errorMsg);
	}
}
