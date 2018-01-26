package com.jt.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;

@Controller
@RequestMapping("cart")
@ResponseBody
public class CartController {
	@Autowired
	private CartService cartService;
	
	@RequestMapping("query/{userId}")
	public SysResult queryCartByUserId(@PathVariable Long userId){
		
		List<Cart> carts = cartService.queryCartByUserId(userId);
		if(carts==null){
			return SysResult.build(201, "购物车为空");
		}
		return SysResult.oK(carts);
	}
	
	@RequestMapping("save")
	public SysResult saveCart(Cart cart){
		SysResult result = cartService.saveCart(cart);
		return result;
	}
	
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	public SysResult updateNum(@PathVariable Long userId,@PathVariable Long itemId,@PathVariable Integer num){
		
		SysResult result = cartService.updateNum(userId,itemId,num);
		if(result!=null){
			return result;
		}
		return SysResult.build(201, "修改数量失败", null);
	}
	
	@RequestMapping("/delete/{userId}/{itemId}")
	public SysResult deleteCart(@PathVariable Long userId,@PathVariable Long itemId){
		
		SysResult result = cartService.deleteCart(userId,itemId);
		
		return result;
	}
}
