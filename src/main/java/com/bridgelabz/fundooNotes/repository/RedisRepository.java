package com.bridgelabz.fundooNotes.repository;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooNotes.util.JwtGenerator;

@Repository
public class RedisRepository {

	private RedisTemplate<String , Object> redisTemplate;
	
	@Autowired
	private JwtGenerator generate;
	
	public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	public long getRedisCacheId(String token) {
		String[] splittedToken = token.split("\\.");
		String redisTokenKey = splittedToken[1] + splittedToken[2];
		if(redisTemplate.opsForValue().get(redisTokenKey) == null) {
			long idForRedis = generate.parseJWT(token);
			redisTemplate.opsForValue().set(redisTokenKey, idForRedis, 3 * 60, TimeUnit.SECONDS);
		}
		return (Long) redisTemplate.opsForValue().get(redisTokenKey);
	}
}