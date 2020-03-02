package com.onebill.minionebill.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.minionebill.dto.ReviewBean;
import com.onebill.minionebill.repository.ReviewRepository;

@Service
public class ReviewDaoImpl implements ReviewDao{

	@Autowired
	ReviewRepository reviewRepository;
	@Override
	public ReviewBean addReview(ReviewBean bean) {
		try {
				return reviewRepository.save(bean);
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public List<ReviewBean> findByProduct(int productId) {
		try {
			List<ReviewBean> bean=reviewRepository.findReview(productId);
			return bean;
		} catch (Exception e) {
			throw e;
		}
	}

}
