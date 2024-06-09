package com.example.genaiservice.apis.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.genaiservice.common.CustomException;
import com.example.genaiservice.util.JwtTokenUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtTokenUtil jwtTokenUtil;
	
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

	@Override
	public Map<String, String> signIn(UserEntity userEntity) {
		if(!StringUtils.hasText(userEntity.getUserId())|| !StringUtils.hasText(userEntity.getPassword()) ) {
			throw new CustomException("User id or password is blank");
		}
		List<UserEntity> userList = userRepository.findByUserId(userEntity.getUserId());
		if(CollectionUtils.isEmpty(userList)) {
			throw new CustomException("User does not exist");
		}
		UserEntity dbUserEntity = userList.get(0);
		if(!passwordEncoder.matches(userEntity.getPassword(),dbUserEntity.getEncPassword())) {
			throw new CustomException("Wrong password");
		}
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", dbUserEntity.getUserId());
		claims.put("role", dbUserEntity.getRole());
		String token = jwtTokenUtil.createToken(claims, userEntity.getUserId());
		
		log.info("token user name is {}", jwtTokenUtil.getUserId(token));
		log.info("Expiration time is {}", jwtTokenUtil.getExpirationTime(token));
		
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		return tokenMap;
	}

}
