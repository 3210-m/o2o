package com.tl.o2o.dto;

import com.tl.o2o.entity.Shop;
import com.tl.o2o.enums.ShopStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 店铺的返回类型
 * @author tangli
 * @create 2018-11-06 上午11:50
 **/
@Getter
@Setter
public class ShopExecution {
	//结果状态
	private int state;

	//状态标识
	private String stateInfo;

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	//店铺数量
	private int count;

	//
	private Shop shop;

	//店铺列表
	private List<Shop> shopList;

	public ShopExecution(){

	}

	/**
	 * 店铺操作出现错误的时候使用的构造器
	 * @param stateEnum
	 */
	public ShopExecution(ShopStateEnum stateEnum){
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	/**
	 * 店铺操作成功时
	 * @param stateEnum
	 * @param shop
	 */
	public ShopExecution(ShopStateEnum stateEnum, Shop shop){
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}

	/**
	 * 店铺操作成功时，返回Shop列表
	 * @param stateEnum
	 * @param shopList
	 */
	public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList){
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
	}
}
