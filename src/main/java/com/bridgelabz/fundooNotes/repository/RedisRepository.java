package com.bridgelabz.fundooNotes.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooNotes.model.NoteInfo;

@Repository
public class RedisRepository {

	@SuppressWarnings("unused")
	private RedisTemplate<String , Object> redisTemplate;
	
	private HashOperations<String, Long, Object> hashOperations;
	
	public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}
	
	public void save(NoteInfo  note) {
		hashOperations.put("note", note.getNoteId(), note);
	}
}