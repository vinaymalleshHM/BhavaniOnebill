package com.onebill.minionebill.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.onebill.minionebill.dao.ReviewDao;
import com.onebill.minionebill.dto.CustomerBean;
import com.onebill.minionebill.dto.ProductBean;
import com.onebill.minionebill.dto.ReviewBean;
import com.onebill.minionebill.util.Response;
import com.onebill.minionebill.util.ResponseUtil;
@SpringBootTest
class ReviewServiceTest {
	@MockBean
	ReviewDao dao;

	@Autowired
	ReviewService service;

	ReviewBean bean = new ReviewBean();

	@Before
	void createReviewObject() {
	CustomerBean customerBean=new CustomerBean();
	customerBean.setCustomerId(1);
	ProductBean productBean=new ProductBean();
	productBean.setProductId(2);
		bean.setReviewId(1);
		bean.setReview("test review");
		bean.setCustomerId(customerBean);
		bean.setProductId(productBean);

	}

	// createProduct
	@Test
	void createReviewTest() {
		when(dao.addReview(bean)).thenReturn(bean);
		Response actual = service.addReview(bean);
		Response expected = ResponseUtil.fillerSuccess("created successfully");
		expected.setData(bean);
		assertEquals(expected, actual);
	}

	@Test
	void createReviewTestElse() {
		Response actual = service.addReview(null);
		Response expected = ResponseUtil.fillerFailure("not created");
		assertEquals(expected, actual);
	}
	@Test
	void createReviewTestException() {
		when(dao.addReview(bean)).thenThrow(NullPointerException.class);
		Response actual = service.addReview(null);
		Response expected = ResponseUtil.fillerFailure("not created");
		assertEquals(expected, actual);
	}

	//find Active Review
		@Test
		void findActiveReview() {
			List<ReviewBean> beans = new ArrayList<ReviewBean>();
			beans.add(bean);
			when(dao.findByProduct(1)).thenReturn(beans);
			Response expected = ResponseUtil.fillerSuccess("found successfully");
			expected.setData(beans);
			assertEquals(expected, service.findByProduct(1));
		}

		@Test
		void getReviewTest() {
			List<ReviewBean> beans = new ArrayList<ReviewBean>();
			beans.add(bean);
			when(dao.findByProduct(1)).thenReturn(beans);
			Response expected = ResponseUtil.fillerSuccess("found successfully");
			expected.setData(beans);
			assertEquals(expected, service.findByProduct(1));
		}

		@Test
		void getReviewTestElse() {
			when(dao.findByProduct(1)).thenReturn(null);
			Response expected = ResponseUtil.fillerFailure("not found");
			assertEquals(expected, service.findByProduct(1));
		}

		@Test
		void getProductTestException() {
			when(dao.findByProduct(1)).thenThrow(NullPointerException.class);
			Response expected = ResponseUtil.fillerException(null);
			assertEquals(expected, service.findByProduct(1));
		}


}
