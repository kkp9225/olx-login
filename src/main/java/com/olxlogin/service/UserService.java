package com.olxlogin.service;

import com.olxlogin.dto.User;
import com.olxlogin.entity.UserEntity;

public interface UserService {

	UserEntity register(User user);

	Boolean authenticate(User loginUser);

	Boolean logout(String auth_token, String token);

	UserEntity userDetails(Integer userId);

	User userDetailsByUsername(String username);

}
