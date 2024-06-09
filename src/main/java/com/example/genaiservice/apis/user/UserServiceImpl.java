package com.example.genaiservice.apis.user;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.genaiservice.common.CustomException;

import java.util.Date;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public void registerUser(UserEntity userEntity) {
		// TODO Auto-generated method stub
		boolean flag = validateUserEntity(userEntity);
		if(flag==false) {
			//throw an exception and return
			log.error("One or more field missing");
			throw new CustomException("One or more required field missing");
		}
		List<UserEntity> userList = userRepository.findByUserId(userEntity.getUserId());
		
		if(!CollectionUtils.isEmpty(userList)) {
			throw new CustomException("User already exist");
		}
		
		String encPassword = passwordEncoder.encode(userEntity.getPassword());
		userEntity.setEncPassword(encPassword);
		userEntity.setCreatedAt(new Date());
		userEntity.setUpdatedAt(new Date());
		try {
			userRepository.save(userEntity);
		}catch(Exception e) {
			log.error("Exception while saving user repository ", e.getMessage());
			throw new CustomException("Exception while saving user");
		}
		
		log.info("User created successfully");
	}
	
	private boolean validateUserEntity(UserEntity userEntity) {
		if(!StringUtils.hasText(userEntity.getUserId())) {
			log.error("User id is empty or nul");
			return false;
		}else if(!StringUtils.hasText(userEntity.getEmail())) {
			log.error("User email is empty or nul");
			return false;
		}else if(!StringUtils.hasText(userEntity.getPassword())) {
			log.error("Password is empty or nul");
			return false;
		}else if(!StringUtils.hasText(userEntity.getRole())) {
			log.error("Role is empty or null");
			return false;
		}else if(!StringUtils.hasText(userEntity.getName())) {
			log.error("Name is empty or null");
			return false;
		}
		return true;
	}

	@Override
	public List<UserEntity> getUserList() {
		// TODO Auto-generated method stub
		return userRepository.findByUserId("abc");
//		return null;
	}

	

}
