package com.jt.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.cart.pojo.Cart;
import com.jt.common.mapper.SysMapper;

public interface CartMapper extends SysMapper<Cart>{
	/**
	 * 根据用户Id查询拥有的购物车
	 * @param userId
	 * @return
	 */
	List<Cart> queryCartByUserId(Long userId);
	/**
	 * 根据用户Id和商品Id查询对应的购物车
	 * @param userId
	 * @param itemId
	 * @return
	 */
	Cart queryCartByUserIdAndItemId(@Param(value="userId")Long userId,@Param(value="itemId") Long itemId);

	/**
	 * 新增购物车
	 * @param cart
	 */
	Integer saveCart(Cart cart);
	
	/**
	 * 根据用户Id和商品Id修改商品数量
	 * @param userId
	 * @param itemId
	 * @param num
	 * @return
	 */
	Integer updateNumByUserIdAndItemId(@Param(value="userId")Long userId,
			@Param(value="itemId")Long itemId,@Param(value="num")Integer num);
	/**
	 * 根据用户Id和商品Id删除购物车
	 * @param userId
	 * @param itemId
	 */
	Integer deleteCart(@Param(value="userId")Long userId,@Param(value="itemId")Long itemId);
}









