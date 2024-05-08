package com.blog_api.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int post_id;
	private String post_title;
	@Lob
	private String post_description;
	private String image;
	@Transient
	private MultipartFile file;
	private String post_Date;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id",referencedColumnName = "user_id")
	@JsonManagedReference(value="user-posts")
	private User user;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id",referencedColumnName = "category_id")
	@JsonManagedReference
	private Category post_Category;
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	@JsonManagedReference(value = "post-comment")
	private List<Comment> comments;
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Likes> likes;
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getPost_description() {
		return post_description;
	}
	public void setPost_description(String post_description) {
		this.post_description = post_description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPost_Date() {
		return post_Date;
	}
	public void setPost_Date(String post_Date) {
		this.post_Date = post_Date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Category getPost_Category() {
		return post_Category;
	}
	public void setPost_Category(Category post_Category) {
		this.post_Category = post_Category;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public List<Likes> getLikes() {
		return likes;
	}
	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}
}
