package com.estoque.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estoque.entities.Category;
import com.estoque.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService CategoryService;

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> listCategories = CategoryService.findAll();

		return ResponseEntity.ok().body(listCategories);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category Category = CategoryService.findById(id);
		return ResponseEntity.ok().body(Category);
	}

}
