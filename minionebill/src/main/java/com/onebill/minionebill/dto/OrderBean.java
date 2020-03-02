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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Table(name="order_info")
@Entity
@Validated
@JsonIgnoreProperties(allowSetters = true,allowGetters = false,value = "customerId")
public class OrderBean implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_id",length = 255)
	private int orderId;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private CustomerBean customerId;
	
	@CreationTimestamp
	@Column(name="date_of_order")
	private LocalDate dateOfOrder;

	@NotNull
	@Size(min=5,max = 255)
	@Column(name="shipping_address",length = 255)
	private String shippingAddress;

	@Size(max=10)
	@Column(name="status",length = 10)
	private String status;


	@Pattern(message = "invalid city",
			regexp = "[a-zA-z]*")
	@Size(min=1,max=20)
	@Column(name="city",length = 20)
	private String city;
	
	@Max(99999)
	@Column(name="zip_code",length = 10)
	private int zipCode;

	@Pattern(message = "invalid state",
			regexp = "[a-zA-z]*")
	@Size(min=1,max=20)
	@Column(name="state",length = 20)
	private String state;

	@OneToMany(cascade = {CascadeType.ALL})
	private List<OrderItemBean> orderItemId;

}
