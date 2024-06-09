package com.example.genaiservice.apis.user;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
	
	public List<UserEntity> findByUserId(String userId);
	
}
