package com.onebill.minionebill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onebill.minionebill.dto.CustomerAddressBean;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddressBean, Integer> {

	@Query(value = "select c from CustomerAddressBean c where c.customerId.customerId=:id ")
	public List<CustomerAddressBean> findAddressById(int id );
	
	@Query(value = "select c from CustomerAddressBean c where c.customerId.status='active' group by customerId ")
	public List<CustomerAddressBean> findAddress();
	

	
}
