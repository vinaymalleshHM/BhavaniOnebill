package com.onebill.minionebill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.dto.CustomerBean;
public interface CustomerRepository extends JpaRepository<CustomerBean, Integer>{

	@Query(value = "select c from CustomerBean c where c.email=:email")
	public CustomerBean findByEmail(String email);
	
	@Query(value = "select c from CustomerBean c where c.status='active'")
	public List<CustomerBean> findActiveCustomer();
	

	
	
}
