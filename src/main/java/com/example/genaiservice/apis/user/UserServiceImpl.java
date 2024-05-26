package com.example.genaiservice.apis.user;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRespository;

	@Override
	public List<UserEntity> getUserList() {
		// TODO Auto-generated method stub
		return userRespository.findAll();
//		return null;
	}

}
