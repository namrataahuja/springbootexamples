package com.example.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	
	@Value("${spring.redis.host}")
	String hostName;

	@Value("${spring.redis.port}")
	int port;
	
	@Value("${spring.redis.password}")
	String password;

	@Bean
	JedisConnectionFactory getJedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(hostName);
		jedisConnectionFactory.setPort(port);
		jedisConnectionFactory.setPassword(password);
		return jedisConnectionFactory;
	}
	
	@Bean
	RedisTemplate<String,Object> getRedisTemplate() {
		RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(getJedisConnectionFactory());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new StringRedisSerializer());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		return redisTemplate;
	}
}
