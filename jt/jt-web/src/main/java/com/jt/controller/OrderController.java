package com.jt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.Order;
import com.jt.pojo.User;
import com.jt.service.DubboCartService;
import com.jt.service.DubboOrderService;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Reference(timeout = 3000,check = false)
	private DubboOrderService orderService;
	@Reference(timeout = 3000,check = false)
	private DubboCartService  cartService;
	
	/**
	 * 跳转订单确认页面
	 *页面跳转: order-cart
	 *页面取值: ${carts}
	 * @return
	 */
	@RequestMapping("/create")
	public String create(Model model) {
		Long userId = UserThreadLocal.get().getId();
		List<Cart> carts = 
				cartService.findCartListByUserId(userId);
		model.addAttribute("carts", carts);
		return "order-cart";
	}
	
	
	
	/**
	 * <input id="username" name="username" 
	 * 					value="天王盖地虎"/>
	 * 
	 * value = request.getParameter("XXX");
	 * ~~~SetXXX()~~~~~User对象数据
	 * 
	 * Class User{
	 * 	private String username;
	 * 	private Dog  dog;
	 * }
	 * 
	 * Class Dog{
	 * 	private String dogName;
	 * 	private String type;
	 * }
	 * <input id="tyoe"    name="dog.type" value="茶杯犬"/>
	 * <input id="dogName" name="dog.dogName" value="拆二代" />
	 */
	
	/**
	 * 实现订单入库操作
	 */
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult saveOrder(Order order) {
		//需要返回页面数据.
		Long userId = UserThreadLocal.get().getId();
		order.setUserId(userId);
		String orderId = orderService.saveOrder(order);
		return SysResult.success(orderId);
	}
	
	/**
	 * 实现订单查询
	 */
	@RequestMapping("/success")
	public String findOrderById(String id,Model model) {
		
		Order order = orderService.findOrderById(id);
		model.addAttribute("order", order);
		return "success";
	}
	
	
	
	
	
	
	
	
}
