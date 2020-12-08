package com.lucas.crudangular.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.crudangular.domain.model.Product;
import com.lucas.crudangular.domain.repository.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping
	public List<Product> listar(){
		return productRepository.findAll();
	}
	
	@GetMapping("{productId}")
	public ResponseEntity<Product> buscar(@PathVariable Long productId) {
		Optional <Product> product = productRepository.findById(productId);
		
		if(product.isPresent()) {
			return ResponseEntity.ok(product.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product adicionar(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<Product> atualizar(@PathVariable Long productId, @RequestBody Product product){
		
		if(!productRepository.existsById(productId)) {
			return ResponseEntity.notFound().build();
		}
		
		product.setId(productId);
		product = productRepository.save(product);
		return ResponseEntity.ok(product);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> remover(@PathVariable Long productId){
		
		if(!productRepository.existsById(productId)) {
			return ResponseEntity.notFound().build();
		}
		
		productRepository.deleteById(productId);
		return ResponseEntity.noContent().build();
	}	
}
