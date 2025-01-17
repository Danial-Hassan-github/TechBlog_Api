package com.blog_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog_api.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByEmailAndPassword(String email,String Password);
}
