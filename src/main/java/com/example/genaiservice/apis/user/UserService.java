package com.example.genaiservice.apis.user;

import java.util.List;

public interface UserService {

	List<UserEntity> getUserList();

	void registerUser(UserEntity userEntity);

}
