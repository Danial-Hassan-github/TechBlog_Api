package com.blog_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_api.entities.Likes;
import com.blog_api.services.LikesService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/likes")
public class LikeController {

	@Autowired
	LikesService likesService;
	
	@GetMapping("/userLikes/{user_id}")
	public List<Likes> getLikes(@PathVariable("user_id") int user_id) {
		return likesService.getLikesOfUser(user_id);
	}
	@PostMapping("/likePost/{post_id}/user/{user_id}")
	public void likePost(
			@PathVariable("post_id") int post_id,
			@PathVariable("user_id") int user_id) {
		likesService.likePost(post_id,user_id);
	}
	
	@PostMapping("/unlikePost/user/{user_id}")
	public void unlikePost(
			@PathVariable("user_id") int user_id) {
		likesService.unlikePost(user_id);
	}
}
