package com.jt.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import com.jt.common.vo.SysResult;

@Service
public class CartService extends BaseService<Cart>{
	@Autowired
	private CartMapper cartMapper;
	public List<Cart> queryCartByUserId(Long userId) {
		List<Cart> carts =cartMapper.queryCartByUserId(userId);
		return carts;
	}
	public SysResult saveCart(Cart cart) {
		
		Cart oldCart = cartMapper.queryCartByUserIdAndItemId(cart.getUserId(),cart.getItemId());
		if(oldCart==null){
			//可以新增
			int count =cartMapper.saveCart(cart);
			if(count==0){
				return SysResult.build(201,"新增失败");
			}
			return SysResult.oK(cart.getId());
		}else{
			//存在则数量加一
			oldCart.setNum(oldCart.getNum()+1);
			cartMapper.updateNumByUserIdAndItemId(cart.getUserId(), cart.getItemId(), cart.getNum());
			return SysResult.build(202,"该商品已存在购物车中，商品数量+1",null);
		}
	}
	public SysResult updateNum(Long userId, Long itemId, Integer num) {
		Integer count = cartMapper.updateNumByUserIdAndItemId(userId, itemId, num);
		if(count==0){
			return SysResult.build(201, "修改的商品购物车不存在");
		}
		return SysResult.oK();
	}
	public SysResult deleteCart(Long userId, Long itemId) {
		
		Integer count = cartMapper.deleteCart(userId, itemId);
		if(count==0){
			return SysResult.build(201, "删除失败");
		}
		return SysResult.oK();
	}
	
	
}
