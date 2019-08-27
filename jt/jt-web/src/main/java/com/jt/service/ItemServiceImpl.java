package com.jt.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.util.HttpClientService;
import com.jt.util.ObjectMapperUtil;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private HttpClientService httpClient;
	//需要从后台获取商品信息

	@Override
	public Item findItemById(Long itemId) {
		String url = 
				"http://manage.jt.com/web/item/findItemById";
		Map<String,String> params = new HashMap<String, String>();
		params.put("itemId", ""+itemId);
		String result = httpClient.doGet(url, params);
		return ObjectMapperUtil.toObject(result, Item.class);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		String url = 
				"http://manage.jt.com/web/item/findItemDescById";
		Map<String,String> params = new HashMap<String, String>();
		params.put("itemId", ""+itemId);
		String result = httpClient.doGet(url, params);
		return ObjectMapperUtil.toObject(result, ItemDesc.class);
	}
}
