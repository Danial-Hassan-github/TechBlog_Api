package com.blog_api.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comment_id;
	private String description;
	private String comment_Date;
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference(value="user-comments")
	private User user;
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference(value = "post-comment")
	private Post post;
	@OneToMany(mappedBy = "comment",cascade = CascadeType.ALL)
	@JsonManagedReference(value= "comment-replies")
	private List<Reply> replies;
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComment_Date() {
		return comment_Date;
	}
	public void setComment_Date(String comment_Date) {
		this.comment_Date = comment_Date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	
}
