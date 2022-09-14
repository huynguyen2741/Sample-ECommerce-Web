package com.ecommerce.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.model.Product;
import com.ecommerce.model.ProductCategory;

import com.ecommerce.repo.ProductCategoryRepo;

@ExtendWith(MockitoExtension.class)
class ProductCategoryServiceTest {

	@Mock	
	private ProductCategoryRepo repo;
	
	private ProductCategoryService service;
	private ProductCategory p;
	
	List<Product> products = new ArrayList<>();
	
	@BeforeEach
	void setUp(){
		service = new ProductCategoryService(repo);
		p = new ProductCategory(1,"laptop",products);
	}

	@Test
	void testGetAllCategory() {
		service.getAllCategory();
		verify(repo).findAll();
	}

	@Test
	void testGetCategoryById() {
		ProductCategory b = new ProductCategory(2,"Mouse",products);
 
		when(repo.findById(b.getCategoryId())).thenReturn(Optional.of(b));

		assertThat(service.getCategoryById(b.getCategoryId())).isEqualTo(b);
	}

	@Test
	void testAddCategory() {
		when(repo.save(p)).thenReturn(p);
		assertThat(service.addCategory(p)).isEqualTo(p);
	}

	@Test
	void testUpdateCategory() {
		String category = "mouse";
		p.setCategory(category);
		when(repo.save(p)).thenReturn(p);
		when(repo.findById(p.getCategoryId())).thenReturn(Optional.of(p));
		assertThat(service.updateCategory(p)).isEqualTo(p);
		verify(repo).findById(p.getCategoryId()); 
	}

	@Test
	void testDeleteCategory() {
		long id = 1;
		service.deleteCategory(id);
		verify(repo).deleteById(id);
	}

}
