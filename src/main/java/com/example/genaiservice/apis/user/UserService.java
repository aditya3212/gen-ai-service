package com.example.genaiservice.apis.user;

import java.util.List;
import java.util.Map;

public interface UserService {

	List<UserEntity> getUserList();

	void registerUser(UserEntity userEntity);

	Map<String, String> signIn(UserEntity userEntity);

}
