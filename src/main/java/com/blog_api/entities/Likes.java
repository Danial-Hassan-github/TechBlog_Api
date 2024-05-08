package com.blog_api.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
//@IdClass(Likes.class)
public class Likes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likeId;
	private int userId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	@JsonBackReference
	private Post post;
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
}
