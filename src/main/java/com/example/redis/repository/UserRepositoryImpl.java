package com.example.redis.repository;

import com.example.redis.model.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;

/**
 * @author jack-gu
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

	private HashOperations hashOperations;


	public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(User user) {
		hashOperations.put("USER", user.getId(), user);
	}

	@Override
	public Map<String, User> findAll() {
		return hashOperations.entries("USER");
	}

	@Override
	public User findById(String id) {
		return (User) hashOperations.get("USER", id);
	}

	@Override
	public void update(User user) {
		save(user);
	}

	@Override
	public void delete(String id) {
		hashOperations.delete("USER", id);
	}
}
