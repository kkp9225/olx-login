package com.olxlogin.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.olxlogin.entity.UserEntity;
import com.olxlogin.exception.InvalidTokenException;
import com.olxlogin.exception.InvalidUserException;
import com.olxlogin.repo.UserRepo;
import com.olxlogin.service.UserService;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService {

	@Autowired
	UserRepo userRepo;

	private static final String TOKEN = "KkPaTil25011992";

	@Override
	public UserDetails loadUserByUsername(String username) throws InvalidUserException {
		UserEntity userDetailsEntity = new UserEntity();
		userDetailsEntity = userRepo.findUserByUsername(username);
		if (userDetailsEntity == null) {
			throw new InvalidUserException("User not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(userDetailsEntity.getRole()));
		User user = new User(userDetailsEntity.getUsername(), userDetailsEntity.getPassword(), authorities);
		return user;
	}

	@Override
	public UserEntity register(com.olxlogin.dto.User user) {
		UserEntity userEntity = new UserEntity(user.getFirstname(), user.getLastname(), user.getUsername(),
				user.getEmail(), user.getPhone(), user.getPassword(), "ROLE_USER");
		userRepo.save(userEntity);
		return userEntity;
	}

	@Override
	public Boolean authenticate(com.olxlogin.dto.User loginUser) {
		
		return null;
	}

	@Override
	public Boolean logout(String auth_token, String token) {
		
		return null;
	}

	@Override
	public UserEntity userDetails(Integer userId) {
		UserEntity user = userRepo.getById(userId);
		return user;
	}

	@Override
	public com.olxlogin.dto.User userDetailsByUsername(String username) {
		
		return null;
	}


//	@Override
//	public com.olxlogin.dto.User register(User user) {
//		UserEntity newUser = new UserEntity(user.getFirstname(), user.getLastname(), user.getUsername(),
//				user.getEmail(), user.getPhone(), user.getPassword(), user.getRole());
//		newUser = userRepo.save(newUser);
//		user = new User(newUser.getId(), newUser.getFirstname(), newUser.getLastname(), newUser.getUsername(),
//				newUser.getEmail(), newUser.getPhone(), newUser.getPassword());
//		return user;
//	}
//
//	@Override
//	public Boolean authenticate(User loginUser) {
//		UserEntity userExist = userRepo.findUserByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
//		if (userExist != null) {
//			return Boolean.TRUE;
//		}
//		throw new InvalidUserException(
//				"username : " + loginUser.getUsername() + " or " + "password : " + loginUser.getPassword());
//	}
//
//	@Override
//	public Boolean logout(String auth_token, String token) {
//		Boolean authUser;
//		if (auth_token.equals(token)) {
//			authUser = Boolean.TRUE;
//			return authUser;
//		}
//		throw new InvalidTokenException("UnAuthorized Access. Access denied.");
//	}
//
//	@Override
//	public User userDetails(Integer userId, String auth_token) {
//		UserEntity userEntity = userRepo.findUserById(userId);
//		if (userEntity != null) {
//			User user = new User(userEntity.getId(), userEntity.getFirstname(), userEntity.getLastname(),
//					userEntity.getUsername(), userEntity.getEmail(), userEntity.getPhone(), userEntity.getPassword());
//			return user;
//		}
//		throw new InvalidUserException("user Id : " + userId);
//	}

}
