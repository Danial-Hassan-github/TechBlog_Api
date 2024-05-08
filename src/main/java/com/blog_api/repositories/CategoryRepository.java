package com.blog_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog_api.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
