package com.example.payingguest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.payingguest.entities.User;
import com.example.payingguest.exception.CustomException;
import com.example.payingguest.exception.UserNotFoundException;
import com.example.payingguest.request.UpdatePass;
import com.example.payingguest.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/pg_api/user")
public class UserController {

	@Autowired
	private UserService userServiceRef;

	@PostMapping("/register")
	public ResponseEntity<?> Register(@RequestBody User newUser) {
		System.out.println(newUser);
		try {
			System.out.println(newUser);
			return new ResponseEntity<>(userServiceRef.registration(newUser), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getbyemail")
	public ResponseEntity<?> getByEmail(@RequestHeader("Authorization") String token) {
		try {
			User user = userServiceRef.findUserProfileByJwt(token);
			String email = user.getEmail();
			return new ResponseEntity<>(userServiceRef.GetByEmail(email), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping()
	public ResponseEntity<?> getAllUsers() {
		try {
			return new ResponseEntity<>(userServiceRef.GetAllUsers(), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/byRole")
	public ResponseEntity<?> getAllUsersByRole(@RequestParam String role) {
		try {
			return new ResponseEntity<>(userServiceRef.GetAllUsersByRole(role), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			return userServiceRef.delete(id);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/updateUser")
	public ResponseEntity<?> update(@RequestBody User userRef) {
		try {
			return userServiceRef.update(userRef);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

//	@PutMapping("/updatepassword/{email}/{password}")
//	public ResponseEntity<?> updatePasword(@PathVariable Long id, @PathVariable String oldPassword, ) {
//		System.out.println(id + "," + password);
//		try {
//			return userServiceRef.upadatePassword(email, oldPassword,newPassword);
//		} catch (CustomException e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		} catch (UserNotFoundException e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//		}
//	}

	@PutMapping("/updatepassword")
	public ResponseEntity<?> updatePasword1(@RequestBody UpdatePass updatePassRef) {
		// System.out.println(userId + "," + password);
		try {
			return new ResponseEntity<>(userServiceRef.upadatePassword(updatePassRef.getEmail(),
					updatePassRef.getOldPassword(), updatePassRef.getNewPassword()), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}