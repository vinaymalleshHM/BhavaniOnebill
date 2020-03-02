package com.onebill.minionebill.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Table(name="product_info")
@Entity

public class ProductBean implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id")
	private int productId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="product_code")
	private String productCode;
	
	@Digits(fraction = 2,integer = 20)
	@Column(name="price")
	private double price;
	
	@Column(name="description")
	private String description;
	
	@Size(max=10)
	@Column(name="status")
	private String status;
	
	@CreationTimestamp
	@Column(name="date_of_added")
	private LocalDate dateOfAdded;

	@Digits(fraction = 2,integer = 5)
	@Column(name="avg_rating")
	private double avgRating;

	@OneToMany(cascade = {CascadeType.ALL})
	private List<ReviewBean> reviewId;

}
