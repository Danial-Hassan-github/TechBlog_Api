package com.blog_api.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.blog_api.entities.Comment;
import com.blog_api.entities.Post;
import com.blog_api.entities.User;
import com.blog_api.repositories.CommentRepository;
import com.blog_api.repositories.PostRepository;
import com.blog_api.repositories.UserRepository;

@Service
public class CommentService {

	public static ApplicationContext applicationContext;
	CommentRepository commentRepository;
	PostRepository postRepository;
	UserRepository userRepository;
	public void addComment(Comment comment,int post_id,int user_id) {
		commentRepository=applicationContext.getBean(CommentRepository.class);
		postRepository=applicationContext.getBean(PostRepository.class);
		userRepository=applicationContext.getBean(UserRepository.class);
		User user=userRepository.findById(user_id).get();
		Post post=postRepository.findById(post_id).get();
		comment.setPost(post);
		comment.setUser(user);
		comment.setComment_Date(new Date().toString());
		commentRepository.save(comment);
	}
	
	public List<Comment> getAllComments() {
		commentRepository=applicationContext.getBean(CommentRepository.class);
		List<Comment> comments = commentRepository.findAll();
		return comments;
	}
	
	public void updateComment(Comment comment) {
		commentRepository=applicationContext.getBean(CommentRepository.class);
		commentRepository.save(comment);
	}
	
	public void deleteComment(int id) {
		commentRepository=applicationContext.getBean(CommentRepository.class);
		commentRepository.deleteById(id);
	}
}
