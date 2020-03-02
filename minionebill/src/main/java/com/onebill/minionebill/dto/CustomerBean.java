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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Table(name = "customer_info")
@Entity
@Validated
public class CustomerBean implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id", length = 10)
	private int customerId;

	@NotNull
	@Pattern(message = "first name is invalid", regexp = "[a-zA-Z]*")
	@Size(min = 3, max = 20)
	@Column(name = "firstName", length = 20)
	private String firstName;

	@NotNull
	@Pattern(message =  "last name is invalid", regexp = "[a-zA-Z]*")
	@Size(min = 1, max = 20)
	@Column(name = "last_name", length = 20)
	private String lastName;

	@NotNull
	@Email(message = "email is not correct format")
	@Column(name = "email", length = 255, unique = true)
	private String email;

	@NotNull
	@Pattern(message = "invalid phone number", regexp = "[0-9]{10}")
	@Column(name = "phone_no", length = 15)
	private String phoneNo;

	@Size(max = 10)
	@Column(name = "status", length = 15)
	private String status;

	@CreationTimestamp
	@Column(name = "date_of_added")
	private LocalDate dateOfAdded;

	@OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	private List<CustomerAddressBean> addressBeans;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<OrderBean> orderBeans;
}
