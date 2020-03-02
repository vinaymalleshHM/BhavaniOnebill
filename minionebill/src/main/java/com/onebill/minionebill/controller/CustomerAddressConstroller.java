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

import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.service.CustomerAddressService;
import com.onebill.minionebill.util.Response;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/address")
public class CustomerAddressConstroller {
	@Autowired
	CustomerAddressService service;
	
	@PostMapping(value = "/add-customer", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response addCustomer(@Valid @RequestBody CustomerAddressBean bean) {
		return service.createCustomerAddress(bean);
	}
	
	@PutMapping(value = "/update-customer", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response updateCustomer(@Valid @RequestBody CustomerAddressBean bean) {
		return service.updateCustomerAddress(bean);
	}

	@DeleteMapping(value = "/delete-customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response removeCustomer(@Valid @PathVariable int id) {
		return service.deleteCustomerAddress(id);
	}

	@GetMapping(value = "/get-customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getCustomer( @PathVariable int id) {
		return service.findCustomerAddressById(id);
	}
	
	@GetMapping(value = "/get-all-customer", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getCustomerAddress() {
		return service.findActiveCustomerAddress();
	}

}
