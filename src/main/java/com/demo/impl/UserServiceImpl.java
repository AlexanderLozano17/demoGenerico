package com.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.config.ConstantsPathApiConfig;
import com.demo.entities.UserEntity;
import com.demo.repositories.BaseRepository;
import com.demo.repositories.UserRepository;
import com.demo.services.UserServices;

@Service(ConstantsPathApiConfig.PATH_SERVICE_USER_V1)
public class UserServiceImpl extends BaseServiceImpl<UserEntity, Long> implements UserServices {

	@Autowired
	@Qualifier(ConstantsPathApiConfig.PATH_REPOSITORY_USER)
	private UserRepository userRepository;
	
	public UserServiceImpl(BaseRepository<UserEntity, Long> baseRepository) {
		super(baseRepository);
	}
}
