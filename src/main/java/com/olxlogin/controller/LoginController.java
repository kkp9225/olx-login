package com.olxlogin.controller;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.olxlogin.dto.AuthenticationRequest;
import com.olxlogin.dto.User;
import com.olxlogin.entity.UserEntity;
import com.olxlogin.exception.InvalidUserException;
import com.olxlogin.service.UserService;
import com.olxlogin.util.JwtUtils;

import io.swagger.annotations.ApiOperation;

@RestController
//@RequestMapping("/users")
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	private static final String TOKEN = "KkPaTil25011992";
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;
	
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<String> handleInvalidStockIdException(InvalidUserException invalidUserException) {
		return new ResponseEntity<String>("Local handler " + invalidUserException.toString(), HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Authenticate user", notes = "This service authenticate users")
	public ResponseEntity<String> authenticateRequest(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException badCredentialsException) {
			throw new InvalidUserException("Invalid username : " + authenticationRequest.getUsername() + "/ password: "
					+ authenticationRequest.getPassword());
		}
		String jwtToken = jwtUtils.generateToken(authenticationRequest.getUsername());
		return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
	}
	
	@GetMapping(value = "/token/validate", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Validate token", notes = "This service validates token")
	public ResponseEntity<Boolean> isValid(@RequestHeader("Authorization") String jwtToken) {
		jwtToken = jwtToken.substring(7, jwtToken.length());
		String username = jwtUtils.extractUsername(jwtToken);
		UserDetails userDetails =  userDetailsService.loadUserByUsername(username);
		Boolean isValid = jwtUtils.validateToken(jwtToken, userDetails);
		return new ResponseEntity<Boolean>(isValid, HttpStatus.OK);
	}
	
	@PostMapping("/hi")
	public ResponseEntity<?> sayHi(){
		return new ResponseEntity<>("HI! Welcome to Springboot Microservices", HttpStatus.OK);
	}
	
	@PostMapping("/user/authenticate")
	@ApiOperation(value = "This service helps to authenticate user login")
	public ResponseEntity<?> authenticate(@RequestBody User loginUser){
		Boolean isAuthenticated = userService.authenticate(loginUser);
		if(isAuthenticated) {
			return new ResponseEntity<>(TOKEN, HttpStatus.OK);
		}
		return new ResponseEntity<>("User Unauthorized", HttpStatus.UNAUTHORIZED);
	}
	
	@DeleteMapping("/user/logout")
	@ApiOperation(value = "This service helps user to logout")
	public ResponseEntity<?> logout(@RequestHeader String auth_token){
		Boolean authUser = userService.logout(auth_token,TOKEN);
		if(authUser) {
			return new ResponseEntity<>(authUser, HttpStatus.OK);
		}
		return new ResponseEntity<>("Unauthorized Access", HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/user")
	@ApiOperation(value = "This service helps user to register")
	public ResponseEntity<UserEntity> registerUser(@RequestBody User user){
		UserEntity newUser = userService.register(user);
		return new ResponseEntity<UserEntity>(newUser, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	@ApiOperation(value = "This service helps to view user details")
	public ResponseEntity<?> userDetails(@RequestHeader("Authorization") String jwtToken, @PathVariable int userId){
		jwtToken = jwtToken.substring(7, jwtToken.length());
		String username = jwtUtils.extractUsername(jwtToken);
		UserDetails userDetails =  userDetailsService.loadUserByUsername(username);
		Boolean isValid = jwtUtils.validateToken(jwtToken, userDetails);
		UserEntity uDetails = new UserEntity();
		if(isValid) {
			uDetails = userService.userDetails(userId);
		}
		return new ResponseEntity<>(uDetails,HttpStatus.OK);
	}
}
