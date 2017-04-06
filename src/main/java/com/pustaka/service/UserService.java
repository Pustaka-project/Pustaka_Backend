package com.pustaka.service;

import com.pustaka.dto.LoginDTO;
import com.pustaka.dto.UserDTO;

public interface UserService {

	public String addUser(UserDTO user);

	public UserDTO authenticateUser(LoginDTO loginDTO);
}
