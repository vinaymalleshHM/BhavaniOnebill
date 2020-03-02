package com.onebill.minionebill.service;

import com.onebill.minionebill.dto.ReviewBean;
import com.onebill.minionebill.util.Response;

public interface ReviewService {
	public Response addReview(ReviewBean bean);
	public Response findByProduct(int productId);
}
