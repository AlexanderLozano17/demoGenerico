package com.demo.repositories;

import org.springframework.stereotype.Repository;

import com.demo.config.ConstantsPathApiConfig;
import com.demo.entities.UserEntity;

@Repository(ConstantsPathApiConfig.PATH_REPOSITORY_USER)
public interface UserRepository extends BaseRepository<UserEntity, Long> {

}
