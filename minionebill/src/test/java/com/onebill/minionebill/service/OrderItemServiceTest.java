package com.onebill.minionebill.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.onebill.minionebill.dao.OrderItemDao;
import com.onebill.minionebill.dto.OrderBean;
import com.onebill.minionebill.dto.OrderItemBean;
import com.onebill.minionebill.dto.ProductBean;
import com.onebill.minionebill.util.Response;
import com.onebill.minionebill.util.ResponseUtil;
@SpringBootTest
class OrderItemServiceTest {
	@MockBean
	OrderItemDao dao;

	@Autowired
	OrderItemService service;

	OrderItemBean bean = new OrderItemBean();

	@Before
	void createOrderItemObject() {
	OrderBean orderBean=new OrderBean();
	orderBean.setOrderId(1);
	ProductBean productBean=new ProductBean();
	productBean.setProductId(2);
		bean.setOrderItemId(1);
		bean.setOrderId(orderBean);
		bean.setProductId(productBean);
		bean.setPrice(123);
		bean.setQuantity(4);
	}

	// create OrderItem
	@Test
	void getOrdersOnOrderItemTest() {
		when(dao.getOrdersOnProduct(1)).thenReturn(5);
		Response actual = service.getOrdersOnProduct(1);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setData(5);
		assertEquals(expected, actual);
	}

	@Test
	void getOrdersOnOrderItemTestElse() {
		when(dao.getOrdersOnProduct(1)).thenReturn(0);
		Response actual = service.getOrdersOnProduct(1);
		Response expected = ResponseUtil.fillerFailure("product not ordered");
		assertEquals(expected, actual);
	}
	@Test
	void createReviewTestException() {
		when(dao.getOrdersOnProduct(1)).thenThrow(NullPointerException.class);
		Response actual = service.getOrdersOnProduct(1);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, actual);
	}
	

	// delete OrderItem
	@Test
	void deleteOrderItemTest() {
		when(dao.existById(bean.getOrderItemId())).thenReturn(true);
		Response expected = ResponseUtil.fillerSuccess("deleted successfully");
		assertEquals(expected, service.deleteItem(bean.getOrderItemId()));
	}

	@Test
	void deleteOrderItemTestElse() {
		when(dao.existById(bean.getOrderItemId())).thenReturn(false);
		Response expected = ResponseUtil.fillerFailure(" item id not exist");
		assertEquals(expected, service.deleteItem(bean.getOrderItemId()));
	}

	@Test
	void deleteOrderItemTestExceptionById() {
		when(dao.existById(bean.getOrderItemId())).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.deleteItem(bean.getOrderItemId()));
	}

	@Test
	void deleteOrderItemTestException() {
		doThrow(NullPointerException.class).when(dao).deleteItem(bean.getOrderItemId());
		when(dao.existById(bean.getOrderItemId())).thenReturn(true);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.deleteItem(bean.getOrderItemId()));
	}



	
}
