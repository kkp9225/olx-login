package com.olxlogin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olxlogin.dto.User;
import com.olxlogin.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer>{

	@Query("SELECT u FROM UserEntity u where username=?1 and password=?2")
	UserEntity findUserByUsernameAndPassword(String username, String password);

	@Query("SELECT u FROM UserEntity u where id=?1")
	UserEntity findUserById(Integer userId);
	
	@Query("SELECT u FROM UserEntity u WHERE u.username=?1")
	UserEntity findUserByUsername(String username);
}
