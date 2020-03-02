package com.onebill.minionebill.dao;

import java.util.List;

import com.onebill.minionebill.dto.ReviewBean;

public interface ReviewDao {
	public ReviewBean addReview(ReviewBean bean);
	public List<ReviewBean> findByProduct(int productId);
}
