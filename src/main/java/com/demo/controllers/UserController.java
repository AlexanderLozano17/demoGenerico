package com.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.config.ConstantsPathApiConfig;
import com.demo.entities.UserEntity;
import com.demo.impl.UserServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = ConstantsPathApiConfig.PATH_CONTROLLER_USER_V1)
public class UserController extends BaseController<UserEntity, UserServiceImpl> {

}
