package com.example.genaiservice.apis.user;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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
			return;
		}
		List<UserEntity> userList = userRepository.findByUserId(userEntity.getUserId());
		
		if(!CollectionUtils.isEmpty(userList)) {
			return;
		}
		
		String encPassword = passwordEncoder.encode(userEntity.getPassword());
		userEntity.setEncPassword(encPassword);
		userEntity.setCreatedAt(new Date());
		userEntity.setUpdatedAt(new Date());
		try {
			userRepository.save(userEntity);
		}catch(Exception e) {
			log.error("Exception while saving user repository ", e.getMessage());
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
