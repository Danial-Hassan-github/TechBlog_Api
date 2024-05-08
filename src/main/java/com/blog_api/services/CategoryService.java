package com.blog_api.services;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.blog_api.entities.Category;
import com.blog_api.repositories.CategoryRepository;

@Service
public class CategoryService {

	public static ApplicationContext applicationContext;
	CategoryRepository categoryRepository;
	
	public void addCategory(Category category) {
		categoryRepository=applicationContext.getBean(CategoryRepository.class);
		categoryRepository.save(category);
	}
	
	public void updateCategory(Category category) {
		categoryRepository=applicationContext.getBean(CategoryRepository.class);
		categoryRepository.save(category);
	}
	
	public void deleteCategory(int id) {
		categoryRepository=applicationContext.getBean(CategoryRepository.class);
		categoryRepository.deleteById(id);
	}
	
	public Iterable<Category> getAllCategories() {
		categoryRepository=applicationContext.getBean(CategoryRepository.class);
		return categoryRepository.findAll();
	}
}
