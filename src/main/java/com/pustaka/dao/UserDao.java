package com.pustaka.dao;

import org.springframework.stereotype.Repository;

import com.pustaka.dto.LoginDTO;
import com.pustaka.dto.UserDTO;
import com.pustaka.entity.UserEntity;

@Repository
public interface UserDao {

	public boolean findUserByEmailAndMobile(UserDTO user);

	public long addUser(UserEntity user);
	
	public UserEntity authenticateUser(LoginDTO loginDTO);

}
