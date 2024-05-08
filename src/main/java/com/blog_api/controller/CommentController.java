package com.blog_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_api.entities.Comment;
import com.blog_api.services.CommentService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@PostMapping("/post/{post_id}/user/{user_id}")
	public void addComment(@JsonFormat Comment comment,
			@PathVariable int post_id,
			@PathVariable int user_id) {
//		if(result.hasErrors()) {
//			System.out.println("error there");
//		}
//		System.out.println(comment.getDescription());
		commentService.addComment(comment,post_id,user_id);
	}
	
	@GetMapping("/")
	public List<Comment> getAllComments(){
		return commentService.getAllComments();
	}
	
	@PutMapping("/")
	public void updateComment(@RequestBody Comment comment) {
		commentService.updateComment(comment);
	}
	
	@DeleteMapping("/{comment_id}")
	public void deleteComment(@PathVariable("comment_id") int id) {
		commentService.deleteComment(id);
	}
	
}
