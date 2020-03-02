package com.onebill.minionebill.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Table(name="review_info")
@Entity
@JsonIgnoreProperties(allowSetters = true,allowGetters = false,value = "customerId")
public class ReviewBean implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="review_id",length = 10)
	private int reviewId;
	
	@Size(max=255)
	@Column(name = "review",length = 255)
	private String review;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_id")
	private ProductBean productId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private CustomerBean customerId;
	
	@CreationTimestamp
	@Column(name="date_of_review")
	private LocalDate dateOfReview;
}
