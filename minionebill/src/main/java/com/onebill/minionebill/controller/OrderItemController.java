package com.onebill.minionebill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onebill.minionebill.service.OrderItemService;
import com.onebill.minionebill.util.Response;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order/item")
public class OrderItemController {
	
	@Autowired
	OrderItemService service;
	
	@GetMapping(value = "/get-orders-on-product", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getOrdersOnProduct(int productId) {
		return service.getOrdersOnProduct(productId);
	}
	
	@DeleteMapping(value = "/delete-item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response deleteItem(@PathVariable int productId) {
		return service.getOrdersOnProduct(productId);
	}


}
