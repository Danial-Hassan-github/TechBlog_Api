package com.blog_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blog_api.entities.Post;
@Repository
@Qualifier("postRepository")
public interface PostRepository extends JpaRepository<Post, Integer> {

	@Query(value = "select*from post where category_id=?",nativeQuery = true)
	Iterable<Post> findByCategory_Id(int id);
	
	@Query(value = "select*from post where user_id=?",nativeQuery = true)
	Iterable<Post> findByUserId(int id);

	@Query(value = "select*from post where category_id=? and user_id=?",nativeQuery = true)
	Iterable<Post> findByByUserAndCategory(int category_id, int user_id);

	@Query(value = "select * from blogging_app.post inner join blogging_app.user where post.user_id=user.user_id",
			nativeQuery = true)
	List<Post> postsAlongUser();
}
