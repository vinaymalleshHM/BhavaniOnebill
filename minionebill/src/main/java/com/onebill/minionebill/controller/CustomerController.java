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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.dto.CustomerBean;
import com.onebill.minionebill.service.CustomerService;
import com.onebill.minionebill.util.Response;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService service;

	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response addCustomer(@Valid @RequestBody CustomerBean bean) {
		return service.createCustomer(bean);
	}

	@PostMapping(value = "/add-with-address", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response addCustomerWithAddress(@Valid @RequestBody CustomerAddressBean customerBeans) {
		CustomerBean bean =customerBeans.getCustomerId();
		return service.createCustomerWithAddress(bean, customerBeans);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response updateCustomer(@Valid @RequestBody CustomerBean bean) {
		return service.updateCustomer(bean);
	}

	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response removeCustomer(@Valid  @PathVariable int id)  {
		return service.deleteCustomer(id);
	}
	@GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getCustomerById(@Valid  @PathVariable int id)  {
		return service.findCustomerById(id);
	}
	@GetMapping(value = "/get-by-email", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getCustomer(@Valid @RequestParam("email") String email) {
		return service.findCustomer(email);
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getCustomer() {
		return service.findActiveCustomer();
	}
}
