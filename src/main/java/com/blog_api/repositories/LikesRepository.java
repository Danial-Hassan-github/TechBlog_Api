package com.blog_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog_api.entities.Likes;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {
	
	List<Likes> findByUserId(int user_id);

	void deleteByUserId(int user_id);
}
