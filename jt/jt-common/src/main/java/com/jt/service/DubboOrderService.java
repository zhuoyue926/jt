package com.jt.service;

import com.jt.pojo.Order;
import com.jt.pojo.User;

public interface DubboOrderService {

	String saveOrder(Order order);

	Order findOrderById(String id);
	
	
}
