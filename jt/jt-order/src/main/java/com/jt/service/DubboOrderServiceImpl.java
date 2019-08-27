package com.jt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;

@Service
public class DubboOrderServiceImpl implements DubboOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Transactional
	@Override
	public String saveOrder(Order order) {
		//1.生成orderId
		String orderId = ""+order.getUserId() 
						 +System.currentTimeMillis();
		Date date = new Date();
		
		//2.实现订单入库
		order.setStatus(1)
			 .setOrderId(orderId)
			 .setCreated(date)
			 .setUpdated(date);
		orderMapper.insert(order);
		System.out.println("订单入库成功!!!!!!!");
		
		//3.入库订单物流
		OrderShipping orderShipping = order.getOrderShipping();
		orderShipping.setOrderId(orderId)
					 .setCreated(date)
					 .setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		System.out.println("订单物流信息入库成功!!!!");
		
		//4.入库订单商品
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrderId(orderId)
					 .setCreated(date)
					 .setUpdated(date);
			orderItemMapper.insert(orderItem);
			//insert into user values(1,"张三",18,"男"),(1,"张四",19,"男")...
		}
		System.out.println("订单入库商品成功!!!!!");
		
		return orderId;
	}

	/**
	 * 	利用id查询订单的全部信息
	 * 	思考:
	 * 		1.使用3个mapper查询数据库导致性能降低.最好的做法能否将sql一起运行.
	 * 		2.尽可能使用单表查询.
	 * 	实现方式:
	 * 		利用mybatis实现上述操作,同时完成数据的封装.
	 * 		
	 * 
	 */
	@Override
	public Order findOrderById(String id) {
		/*
		 * Order order = orderMapper.selectById(id); OrderShipping shipping =
		 * orderShippingMapper.selectById(id); QueryWrapper<OrderItem> queryWrapper =
		 * new QueryWrapper<>(); queryWrapper.eq("order_id", id); List<OrderItem> list =
		 * orderItemMapper.selectList(queryWrapper); order.setOrderShipping(shipping)
		 * .setOrderItems(list);
		 */
		Order order = orderMapper.findOrderById(id);
		return order;
	}
}
