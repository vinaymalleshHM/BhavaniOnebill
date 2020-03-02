package com.onebill.minionebill.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onebill.minionebill.dto.ProductBean;
import com.onebill.minionebill.service.ProductService;
import com.onebill.minionebill.util.Response;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService service;
	
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response addProduct(@Valid @RequestBody ProductBean bean) {
		return service.createProduct(bean);
	}
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response updateProduct(@Valid @RequestBody ProductBean bean) {
		return service.updateProduct(bean);
	}

	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response removeProduct(@Valid @PathVariable int id) {
		return service.deleteProduct(id);
	}

	@GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getProduct() {
		return service.findAllActiveProduct();
	}
	@GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getProductById(@PathVariable int id) {
		return service.findProductById( id);
	}
}
