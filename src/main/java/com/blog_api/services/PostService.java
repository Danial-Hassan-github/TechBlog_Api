package com.blog_api.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog_api.entities.Category;
import com.blog_api.entities.Likes;
import com.blog_api.entities.Post;
import com.blog_api.entities.User;
import com.blog_api.payloads.PostResponse;
import com.blog_api.repositories.CategoryRepository;
import com.blog_api.repositories.LikesRepository;
import com.blog_api.repositories.PostRepository;
import com.blog_api.repositories.UserRepository;
@Service
public class PostService {

	public static ApplicationContext applicationContext;
	PostRepository postRepository;
	UserRepository userRepository;
	CategoryRepository categoryRepository;
	public void addPost(String path,Post post,int category_id,int user_id) {
		postRepository=applicationContext.getBean(PostRepository.class);
		userRepository=applicationContext.getBean(UserRepository.class);
		categoryRepository=applicationContext.getBean(CategoryRepository.class);
		User user=userRepository.findById(user_id).get();
		Category category=categoryRepository.findById(category_id).get();
		post.setPost_Category(category);
		post.setUser(user);
		post.setPost_Date(new Date().toLocaleString());
		File file2=new File(path);
		if(!file2.exists()) {
			file2.mkdir();
		}
		if(post.getFile()!=null) {
			String imagePath=path+File.separator+post.getFile().getOriginalFilename();
			try {
				post.setImage(post.getFile().getOriginalFilename());
				Files.copy(post.getFile().getInputStream(), Paths.get(imagePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		postRepository.save(post);
	}
	
	public InputStream serveImage(String imagePath) throws FileNotFoundException {
		InputStream resourceInputStream=new FileInputStream(imagePath);
		return resourceInputStream;
	}
	
	public PostResponse getAllPosts(int pageNumber,int pageSize,String sortBy,String sortDir) {
		postRepository=applicationContext.getBean(PostRepository.class);
		Sort direction=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			direction = Sort.by(sortBy).ascending();
		}else if(sortDir.equalsIgnoreCase("dsc")) {
			direction = Sort.by(sortBy).descending();
		}
		//System.out.println(postRepository);
		Pageable pageable=PageRequest.of(pageNumber, pageSize,direction);
		Page<Post> posts=postRepository.findAll(pageable);
		//List<Post> p=postRepository.postsAlongUser();
		List<Post> allPosts=posts.getContent();
		PostResponse postResponse=new PostResponse();
		postResponse.setPageNumber(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLastPage(posts.isLast());
		postResponse.setPosts(posts);
		return postResponse;
	}
	
	public void updatePost(Post post) {
		postRepository=applicationContext.getBean(PostRepository.class);
		postRepository.save(post);
	}
	
	public Post getPost(int id) {
		postRepository=applicationContext.getBean(PostRepository.class);
		Optional<Post> postOptional=postRepository.findById(id);
		Post post=postOptional.get();
		return post;
	}
	
	public void removePost(int id) {
		postRepository=applicationContext.getBean(PostRepository.class);
		postRepository.deleteById(id);
	}
	
	public Iterable<Post> getPostsByCategory(int id){
		postRepository=applicationContext.getBean(PostRepository.class);
		Iterable<Post> posts=postRepository.findByCategory_Id(id);
		return posts;
	}
	
	public Iterable<Post> getPostsByUser(int id){
		postRepository=applicationContext.getBean(PostRepository.class);
		Iterable<Post> posts=postRepository.findByUserId(id);
		return posts;
	}

	public Iterable<Post> getPostsByUserAndCategory(int category_id, int user_id) {
		postRepository=applicationContext.getBean(PostRepository.class);
		Iterable<Post> posts=postRepository.findByByUserAndCategory(category_id,user_id);
		return posts;
	}
	
//	public Iterable<Post> postsAlongUser(Pageable pageable){
//		postRepository=applicationContext.getBean(PostRepository.class);
//		Iterable<Post> dataIterable=postRepository.postsAlongUser(pageable);
//		return dataIterable;
//	}
}
