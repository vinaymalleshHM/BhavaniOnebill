package com.onebill.minionebill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onebill.minionebill.dto.ReviewBean;
import com.onebill.minionebill.service.ReviewService;
import com.onebill.minionebill.util.Response;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*")
public class ReviewController {
	@Autowired
	ReviewService service;

	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response addReview(@RequestBody ReviewBean bean) {
		return service.addReview(bean);
	}
	@GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getReview(@RequestParam("productId") int productId) {
		return service.findByProduct(productId);
	}

}
