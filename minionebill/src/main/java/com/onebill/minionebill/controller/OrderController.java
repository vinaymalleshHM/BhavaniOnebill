package com.onebill.minionebill.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onebill.minionebill.dto.OrderBean;
import com.onebill.minionebill.service.OrderService;
import com.onebill.minionebill.util.Response;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService service;
	
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response addOrder(@Valid @RequestBody OrderBean itemBeans) {
		return service.createProduct( itemBeans);
	}

	@GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getOrder(@Valid @RequestParam("customerId") int id) {
		return service.findByOrderForCustomer(id);
	}
	
	@GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getOrderById(@Valid @PathVariable int id) {
		return service.findByOrderById(id);
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getOrders() {
		return service.findByOrders();
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response deleteOrderById(@Valid @PathVariable int id) {
		return service.deleteByOrderById(id);
	}
	
}
