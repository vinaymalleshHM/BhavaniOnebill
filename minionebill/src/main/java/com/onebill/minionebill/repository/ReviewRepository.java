package com.onebill.minionebill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onebill.minionebill.dto.ReviewBean;

public interface ReviewRepository extends JpaRepository<ReviewBean, Integer>{

	@Query(value = "select r from ReviewBean r where r.productId.productId=:productId ")
	public List<ReviewBean> findReview(int productId);
}
