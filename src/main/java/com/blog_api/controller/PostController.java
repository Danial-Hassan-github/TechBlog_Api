package com.blog_api.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog_api.entities.Post;
import com.blog_api.payloads.PostResponse;
import com.blog_api.services.PostService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/posts")
public class PostController {
	@Value("${project.postImage}")
	String path;
	
	@PostMapping("/category/{category_id}/user/{user_id}")
	public void addPost(@JsonFormat Post post,
			@PathVariable("category_id") Integer category_id,
			@PathVariable("user_id") Integer user_id,
			BindingResult bindingResult) {
		PostService postService=new PostService();
		if (bindingResult.hasErrors()) {
			postService.addPost(path,post,category_id,user_id);
		}else {
			postService.addPost(path,post,category_id,user_id);
		}
	}
	
	@GetMapping("/image/{imageName}")
	public void serveImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException {
		PostService postService=new PostService();
		InputStream resourceInputStream=postService.serveImage(path+File.separator+imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resourceInputStream, response.getOutputStream());
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/")
	public PostResponse getAllPosts(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = "image",required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){
		PostService postService=new PostService();
		return postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
	}
	
	@GetMapping("/category/{category_id}")
	public Iterable<Post> getPostsByCategory(@PathVariable("category_id") int category_id){
		PostService postService=new PostService();
		return postService.getPostsByCategory(category_id);
	}
	
	@GetMapping("/user/posts/{user_id}")
	public Iterable<Post> getPostsByUser(@PathVariable("user_id") int user_id){
		PostService postService=new PostService();
		return postService.getPostsByUser(user_id);
	}
	
	@GetMapping("/category/{category_id}/user/{user_id}")
	public Iterable<Post> getPostsByUserAndCategory(
			@PathVariable("category_id") int category_id,
			@PathVariable("user_id") int user_id){
		PostService postService=new PostService();
		return postService.getPostsByUserAndCategory(category_id,user_id);
	}
	
	@PutMapping("/")
	public void updatePost(@RequestBody Post post) {
		PostService postService=new PostService();
		postService.updatePost(post);
	}
	
	@DeleteMapping("/delete/{post_id}")
	public void deletePost(@PathVariable("post_id") int id) {
		PostService postService=new PostService();
		postService.removePost(id);
	}
}
