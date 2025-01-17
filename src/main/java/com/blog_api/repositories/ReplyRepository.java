package com.blog_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog_api.entities.Reply;
@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	
}
