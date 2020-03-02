package com.onebill.minionebill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onebill.minionebill.dto.ProductBean;

public interface ProductRepository extends JpaRepository<ProductBean, Integer>{
	@Query(value = "select p from ProductBean p where p.status='active'")
	public List<ProductBean> getAllActiveProduct();
	
	@Query(value = "select p from ProductBean p where p.status='blocked'")
	public List<ProductBean> getAllDeletedProduct();


}
