package com.jt.test.redis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;

public class TestJSON {
	
	/**
	 * 对象转化json时调用对象的getXXX方法
	 * @throws IOException 
	 */
	@Test
	public void toJSON() throws IOException {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(1000L);
		itemDesc.setItemDesc("商品描述信息!!");
		ObjectMapper mapper = new ObjectMapper();
		String json = 
				mapper.writeValueAsString(itemDesc);
		System.out.println(json);
		
		//将json转化为对象 {key:value....}
		ItemDesc mapperItemDesc = 
				mapper.readValue(json, ItemDesc.class);
		System.out.println("获取数据:"+mapperItemDesc);
	}
	
	/**
	 * 将List集合转化为JSON
	 * @throws IOException 
	 */
	@Test
	public void testList() throws IOException {
		ItemDesc itemDesc1 = new ItemDesc();
		itemDesc1.setItemId(1000L);
		itemDesc1.setItemDesc("商品描述信息!!");
		ItemDesc itemDesc2 = new ItemDesc();
		itemDesc2.setItemId(1000L);
		itemDesc2.setItemDesc("商品描述信息!!");
		ItemDesc itemDesc3 = new ItemDesc();
		itemDesc3.setItemId(1000L);
		itemDesc3.setItemDesc("商品描述信息!!");
		
		List<ItemDesc> list = new ArrayList<ItemDesc>();
		list.add(itemDesc1);
		list.add(itemDesc2);
		list.add(itemDesc3);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = 
		mapper.writeValueAsString(list);
		System.out.println(json);
		
		//将listjson串 转化为List对象
		List<ItemDesc> itemList = mapper.readValue(json,list.getClass());
		System.out.println(itemList);
	}
}
