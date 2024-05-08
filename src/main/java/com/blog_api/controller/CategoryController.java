package com.blog_api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_api.entities.Category;
import com.blog_api.services.CategoryService;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

	CategoryService categoryService=new CategoryService();
	
	@GetMapping("/")
	public Iterable<Category> getAllCategories(){
		return categoryService.getAllCategories();
	}
	
	@PostMapping("/")
	public void addCategory(@RequestBody Category category) {
		categoryService.addCategory(category);
	}
	
	@PutMapping("/")
	public void updateCategory(@RequestBody Category category) {
		categoryService.updateCategory(category);
	}
	
	@DeleteMapping("/{category_id}")
	public void deleteCategory(@PathVariable("category_id") int id) {
		categoryService.deleteCategory(id);
	}
}