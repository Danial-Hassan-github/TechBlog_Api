package com.blog_api.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog_api.entities.User;
import com.blog_api.repositories.UserRepository;

@Service
public class UserService {

	public static ApplicationContext applicationContext;
	UserRepository userRepository;
	public void addUser(String path,User user){
		userRepository=applicationContext.getBean(UserRepository.class);
		File file2=new File(path);
		if(!file2.exists()) {
			file2.mkdir();
		}
		Random random=new Random();
		Long name=random.nextLong();
		System.out.println(name);
		if(user.getFile()!=null) {
			String imagePath=path+File.separator+user.getFile().getOriginalFilename();
			try {
				Files.copy(user.getFile().getInputStream(), Paths.get(imagePath));
				user.setImage(user.getFile().getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			user.setImage("default.png");
		}
		userRepository.save(user);
	}
	
	public void updateUser(User user) {
		userRepository=applicationContext.getBean(UserRepository.class);
		userRepository.save(user);
	}
	
	public User loginUser(String email,String password) {
		userRepository=applicationContext.getBean(UserRepository.class);
		User user = userRepository.findByEmailAndPassword(email, password);
		if(user!=null) {
			return user;
		}
		return null;
	}
	
	public InputStream serveImage(String imagePath) throws FileNotFoundException {
		InputStream resourceInputStream=new FileInputStream(imagePath);
		return resourceInputStream;
	}
	
	public void deleteUser(int id) {
		userRepository=applicationContext.getBean(UserRepository.class);
		userRepository.deleteById(id);
	}
}
