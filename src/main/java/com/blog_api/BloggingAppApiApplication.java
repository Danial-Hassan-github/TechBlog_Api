package com.blog_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.blog_api.services.CategoryService;
import com.blog_api.services.CommentService;
import com.blog_api.services.LikesService;
import com.blog_api.services.PostService;
import com.blog_api.services.ReplyService;
import com.blog_api.services.UserService;

@SpringBootApplication()
public class BloggingAppApiApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(BloggingAppApiApplication.class, args);
		CategoryService.applicationContext=applicationContext;
		CommentService.applicationContext=applicationContext;
		PostService.applicationContext=applicationContext;
		ReplyService.applicationContext=applicationContext;
		UserService.applicationContext=applicationContext;
		LikesService.applicationContext=applicationContext;
//		PostService postService=applicationContext.getBean(PostService.class);
//		Iterable<Post> iterator = postService.postsAlongUser();
//		for(Post i:iterator) {
//			System.out.println(i.getUser().getEmail());
//		}
//		LikesService likesService=new LikesService();
//		//likesService.likePost(2);
//		likesService.unlikePost(2);
	}
}
