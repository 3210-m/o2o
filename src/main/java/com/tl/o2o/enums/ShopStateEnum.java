package com.tl.o2o.enums;

import lombok.Getter;

/**
 * @author tangli
 * @create 2018-11-06 上午11:56
 **/
@Getter
public enum ShopStateEnum {
	CHECK(0,"审核中"),
	OFFLINE(-1,"非法店铺"),
	SUCCESS(1,"操作成功"),
	PASS(2,"通过认证"),
	INNER_ERROR(-1001,"内部系统错误"),
	NULL_SHOPID(-1002,"shopId为空"),
	NULL_SHOP(-1003,"shop为空");
	private int state;
	private String stateInfo;

	/**
	 * 构造方法私有：不希望第三方程序修改ENUM值
	 * @param state
	 * @param stateInfo
	 */
	private ShopStateEnum (int state,String stateInfo){
		this.state = state;
		this.stateInfo = stateInfo;
	}

	/**
	 * 根据传入的state返回相应的enum值
	 * @param state
	 * @return
	 */
	public static ShopStateEnum stateOf(int state){
		for(ShopStateEnum stateEnum:values()){
			if (stateEnum.getState() == state){
				return stateEnum;
			}
		}
		return null;
	}
}
