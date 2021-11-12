package com.olxlogin.controller;

import javax.print.attribute.standard.Media;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.olxlogin.dto.User;

@RestController
public class LoginController {
	
	private static final String TOKEN = "KkPaTil25011992";
	
	@PostMapping("/hi")
	public ResponseEntity<?> sayHi(){
		return new ResponseEntity<>("HI! Welcome to Springboot Microservices", HttpStatus.OK);
	}
	
	@PostMapping("/user/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody User login){
		return new ResponseEntity<>(TOKEN, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/logout")
	public ResponseEntity<?> logout(@RequestHeader String auth_token){
		Boolean authUser = Boolean.FALSE;
		if(auth_token.equals(TOKEN)) {
				authUser = Boolean.TRUE;
				return new ResponseEntity<>(authUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(authUser, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> userDetails(@RequestHeader String auth_token){
		User user = new User();
		if(auth_token.equals(TOKEN)) {
			user.setId(1);
			user.setUsername("kkp9225");
			user.setFirstname("kartik");
			user.setLastname("Patil");
			user.setEmail("kkp@gml.com");
			user.setPhone("9742492468");
		}
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
}
