package com.onebill.minionebill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.minionebill.dao.ReviewDao;
import com.onebill.minionebill.dto.ReviewBean;
import com.onebill.minionebill.repository.ReviewRepository;
import com.onebill.minionebill.util.Response;
import com.onebill.minionebill.util.ResponseUtil;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ReviewDao reviewDao;
	
	@Override
	public Response addReview(ReviewBean bean) {
		Response response = null;
		try {
			if (bean!=null  ) {
				response = ResponseUtil.fillerSuccess("created successfully");
				response.setData(reviewDao.addReview(bean));
			} else {
				response = ResponseUtil.fillerFailure("not created");
			}
		} catch (Exception e) {
			response = ResponseUtil.fillerException("Exception " + e.getMessage());
		}
		return response;
	}
	@Override
	public Response findByProduct(int productId) {
		Response response = null;
		try {
			List<ReviewBean> bean=reviewDao.findByProduct(productId);
			if(bean!=null) {
				response = ResponseUtil.fillerSuccess("found successfully");
				response.setData(bean);
			} else {
				response = ResponseUtil.fillerFailure("not found");
			}
		} catch (Exception e) {
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;
	}

}
