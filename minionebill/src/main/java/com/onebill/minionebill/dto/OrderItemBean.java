package com.onebill.minionebill.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Table(name="order_item_info")
@Entity
@Validated
@JsonIgnoreProperties(allowSetters = true,allowGetters = false,value = "orderId")

public class OrderItemBean implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_item_id",length = 255)
	private int orderItemId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="order_id")
	private OrderBean orderId;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_id")
	private ProductBean productId;
	
	@Max(300)
	@Column(name="quantity",length = 10)
	private int quantity;

	@Digits(fraction = 2,integer = 6)
	@Column(name="price",length = 20)
	private double price;
	
}