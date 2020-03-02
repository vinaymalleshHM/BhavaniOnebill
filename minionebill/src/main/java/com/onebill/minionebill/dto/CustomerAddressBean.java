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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "customer_address_info")
@JsonIgnoreProperties(allowSetters = true,allowGetters = false,value = "customerId")

public class CustomerAddressBean implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_address_id", length = 10)
	private int cAddressId;

	@JoinColumn(name = "customer_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private CustomerBean customerId;

	@Size(min = 5, max = 255)
	@Column(name = "address", length = 255)
	private String address;

	@Pattern(message = "indatavalid city", regexp = "[a-zA-z]*")
	@Size(min = 1, max = 20)
	@Column(name = "city", length = 20)
	private String city;

	@Pattern(message = "invalid state", regexp = "[a-zA-z]*")
	@Size(min = 1, max = 20)
	@Column(name = "state", length = 20)
	private String state;

	@Max(value=99999,message = "zip code must be 5 digit")
	@Column(name = "zip_code", length = 15)
	private int zipCode;

}
