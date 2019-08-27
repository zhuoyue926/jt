package com.jt.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

//标识为配制类    xml <bean id="jedis"  class="redis.clients.jedis.Jedis"/>
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
	
	@Value("${redis.nodes}")
	private String nodes;
	
	@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> nodes = getNodes();
		return new JedisCluster(nodes);
	}
	
	private Set<HostAndPort> getNodes() {
		Set<HostAndPort> set = new HashSet<HostAndPort>();
		String[] nodesArray = nodes.split(",");	//获取node数组
		for (String node : nodesArray) {
			String host = node.split(":")[0];
			int port = Integer.parseInt(node.split(":")[1]);
			set.add(new HostAndPort(host, port));
		}
		return set;
	}
}	
	
	
	
	/**
	 * 配置哨兵
	 */
	/*
	 * @Value("${redis.masterName}") private String masterName;
	 * 
	 * @Value("${redis.nodes}") private String nodes;
	 * 
	 * @Bean("jedisSentinelPool") public JedisSentinelPool jedisSentinelPool() {
	 * 
	 * Set<String> sentinels = new HashSet<>(); sentinels.add(nodes); return new
	 * JedisSentinelPool(masterName, sentinels); }
	 */
	
	





	/**
	 * @Autowired	为方法注入对象
	 * @Qualifier("jedisSentinelPool") 指定对象注入  
	 * @param jedisSentinelPool
	 * @return
	 */
	/*
	 * @Bean public Jedis jedis(@Autowired JedisSentinelPool jedisSentinelPool) {
	 * 
	 * return jedisSentinelPool.getResource(); }
	 */
		
	
	
	
	
	
	
	
	
	/**
	 * 配置redis分片
	 */
	/*
	 * @Value("${redis.nodes}") private String nodes; //node,node,node
	 * 
	 * @Bean public ShardedJedis shardedJedis() { List<JedisShardInfo> shards =
	 * getShards(); return new ShardedJedis(shards); }
	 * 
	 * private List<JedisShardInfo> getShards() { List<JedisShardInfo> list = new
	 * ArrayList<JedisShardInfo>(); String[] nodeArray = nodes.split(","); for
	 * (String node : nodeArray) { String host = node.split(":")[0]; int port =
	 * Integer.parseInt(node.split(":")[1]); JedisShardInfo info = new
	 * JedisShardInfo(host, port); list.add(info); }
	 * 
	 * return list; }
	 */

	
	
	/*
	 * @Value("${redis.host}") private String host;
	 * 
	 * @Value("${redis.port}") private Integer port;
	 * 
	 * @Bean public Jedis jedis() {
	 * 
	 * return new Jedis(host, port); }
	 */

