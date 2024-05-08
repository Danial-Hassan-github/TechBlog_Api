package com.blog_api.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.blog_api.entities.Likes;
import com.blog_api.entities.Post;
import com.blog_api.repositories.LikesRepository;
import com.blog_api.repositories.PostRepository;

@Service
public class LikesService {

	public static ApplicationContext applicationContext;
	PostRepository postRepository;
	LikesRepository likesRepository;
	
	public List<Likes> getLikesOfUser(int user_id) {
		likesRepository=applicationContext.getBean(LikesRepository.class);
		List<Likes> likes=likesRepository.findByUserId(user_id);
//		for (Likes like:likes) {
//			System.out.println(like.getUser_id()+1);
//		}
		return likes;
	}
	
	public void likePost(int post_id,int user_id) {
		likesRepository=applicationContext.getBean(LikesRepository.class);
		postRepository=applicationContext.getBean(PostRepository.class);
		Post post=postRepository.findById(post_id).get();
		Likes like=new Likes();
		like.setPost(post);
		like.setUserId(user_id);
		likesRepository.save(like);
//		post.setLikes(post.getLikes()+1);
//		postRepository.save(post);
	}
	
	public void unlikePost(int user_id) {
		likesRepository=applicationContext.getBean(LikesRepository.class);
		postRepository=applicationContext.getBean(PostRepository.class);
//		Likes like=new Likes();
//		like.setPostId(post_id);
//		like.setUserId(user_id);
		likesRepository.deleteByUserId(user_id);
//		Post post=postRepository.findById(post_id).get();
//		post.setLikes(post.getLikes()-1);
//		postRepository.save(post);
	}
}
