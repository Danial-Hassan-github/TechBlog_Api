package com.blog_api.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_api.entities.User;
import com.blog_api.services.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
@MultipartConfig
public class UserController {
	@Value("${project.profileImage}")
	String path;
	@PostMapping("/")
	public void addUser(@JsonFormat User user,
			BindingResult bindingResult) {
		//System.out.println(file.getOriginalFilename());
		UserService userService=new UserService();
		if(bindingResult.hasErrors()) {
			userService.addUser(path,user);
		}else {
		userService.addUser(path,user);
		}
	}
	
	@GetMapping("/{email}/{password}")
	public User loginUser(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		UserService userService=new UserService();
		return userService.loginUser(email, password);
	}
	
	@GetMapping("/{imageName}")
	public void serveImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException {
		UserService userService=new UserService();
		InputStream resourceInputStream=userService.serveImage(path+File.separator+imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resourceInputStream, response.getOutputStream());
	}
	
	@PutMapping("/")
	public void updateUser(@JsonFormat User user) {
		UserService userService=new UserService();
		userService.updateUser(user);
	}
	
	@DeleteMapping("/remove/{user_id}")
	public void deleteUser(@PathVariable("user_id") int id) {
		UserService userService=new UserService();
		userService.deleteUser(id);
	}
}
