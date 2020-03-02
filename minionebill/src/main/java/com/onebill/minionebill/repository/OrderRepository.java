package com.onebill.minionebill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onebill.minionebill.dto.OrderBean;

public interface OrderRepository extends JpaRepository<OrderBean,Integer>{

	@Query(value="select o from OrderBean o where o.customerId.customerId=:id" )
	public List<OrderBean> getOrderById(int id);
}
