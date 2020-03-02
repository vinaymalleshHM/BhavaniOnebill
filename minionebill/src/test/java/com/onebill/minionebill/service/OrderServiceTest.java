package com.onebill.minionebill.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.onebill.minionebill.dao.OrderDao;
import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.dto.CustomerBean;
import com.onebill.minionebill.dto.OrderBean;
import com.onebill.minionebill.dto.OrderItemBean;
import com.onebill.minionebill.util.Response;
import com.onebill.minionebill.util.ResponseUtil;

@SpringBootTest
class OrderServiceTest {
	@MockBean
	OrderDao dao;

	@Autowired
	OrderService service;

	OrderBean bean = new OrderBean();
	CustomerAddressBean addressBean = new CustomerAddressBean();

	@Before
	void createOrderObject() {
		CustomerBean customerBean = new CustomerBean();
		customerBean.setCustomerId(1);
		OrderItemBean itemBean = new OrderItemBean();
		itemBean.setOrderItemId(1);
		List<OrderItemBean> itemBeans = new ArrayList<OrderItemBean>();
		itemBeans.add(itemBean);
		bean.setOrderId(1);
		bean.setCity("testCity");
		bean.setCustomerId(customerBean);
		bean.setShippingAddress("test address");
		;
		bean.setState("karnataka");
		bean.setOrderItemId(itemBeans);
	}

	// createOrder
	@Test
	void createOrderTest() {
		OrderItemBean itemBean = new OrderItemBean();
		itemBean.setOrderItemId(1);
		List<OrderItemBean> itemBeans = new ArrayList<OrderItemBean>();
		itemBeans.add(itemBean);
		bean.setOrderItemId(itemBeans);
		Response actual = service.createProduct(bean);
		Response expected = ResponseUtil.fillerSuccess("created successfully");
		expected.setData(null);
		assertEquals(expected, actual);
	}

	@Test
	void createOrderTestElse() {
		List<OrderItemBean> itemBeans = new ArrayList<OrderItemBean>();
		bean.setOrderItemId(itemBeans);
		Response expected = ResponseUtil.fillerFailure("order must contain atleast 1 item created");
		assertEquals(expected, service.createProduct(bean));
	}

	@Test
	void createOrderTestException() {
		when(dao.createPrduct(bean)).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.createProduct(bean));
	}

//deleteOrder
	@Test
	void deleteOrderTest() {
		when(dao.existById(bean.getOrderId())).thenReturn(true);
		Response expected = ResponseUtil.fillerSuccess("deleted successfully");
		assertEquals(expected, service.deleteByOrderById(bean.getOrderId()));
	}

	@Test
	void deleteOrderTestElse() {
		when(dao.existById(bean.getOrderId())).thenReturn(false);
		Response expected = ResponseUtil.fillerFailure("id not found");
		assertEquals(expected, service.deleteByOrderById(bean.getOrderId()));
	}

	@Test
	void deleteOrderTestExceptionById() {
		when(dao.existById(bean.getOrderId())).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.deleteByOrderById(bean.getOrderId()));
	}

	@Test
	void deleteOrderTestException() {
		doThrow(NullPointerException.class).when(dao).deleteByOrderById(bean.getOrderId());
		when(dao.existById(bean.getOrderId())).thenReturn(true);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.deleteByOrderById(bean.getOrderId()));
	}

	// findActiveOrderdescription
	@Test
	void findByOrders() {
		List<OrderBean> beans = new ArrayList<OrderBean>();
		beans.add(bean);
		when(dao.findByOrders()).thenReturn(beans);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setData(beans);
		assertEquals(expected, service.findByOrders());
	}

	@Test
	void getOrderTest() {
		List<OrderBean> beans = new ArrayList<OrderBean>();
		beans.add(bean);
		when(dao.findByOrders()).thenReturn(beans);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setData(beans);
		assertEquals(expected, service.findByOrders());
	}

	@Test
	void getOrderTestElse() {
		List<OrderBean> beans = new ArrayList<OrderBean>();
		when(dao.findByOrders()).thenReturn(beans);
		Response expected = ResponseUtil.fillerFailure("no order exist");
		assertEquals(expected, service.findByOrders());
	}

	@Test
	void getOrderTestException() {
		when(dao.findByOrders()).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.findByOrders());
	}

	// findOrderById
	@Test
	void findOrderByIdTest() {
		when(dao.existById(bean.getOrderId())).thenReturn(true);
		when(dao.findByOrderById(bean.getOrderId())).thenReturn(bean);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setData(bean);
		assertEquals(expected, service.findByOrderById(bean.getOrderId()));
	}

	@Test
	void findOrderByIdTestElse() {
		when(dao.existById(bean.getOrderId())).thenReturn(false);
		Response expected = ResponseUtil.fillerFailure("id not found");
		assertEquals(expected, service.findByOrderById(bean.getOrderId()));
	}

	@Test
	void findOrderByIdTestException() {
		when(dao.existById(bean.getOrderId())).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.findByOrderById(bean.getOrderId()));
	}

//findByOrderForCustomer
	@Test
	void findByOrderForCustomer() {
		List<OrderBean> beans = new ArrayList<OrderBean>();
		beans.add(bean);
		when(dao.findByOrderForCustomer(1)).thenReturn(beans);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setData(beans);
		assertEquals(expected, service.findByOrderForCustomer(1));
	}

	@Test
	void findByOrderForCustomerTest() {
		List<OrderBean> beans = new ArrayList<OrderBean>();
		beans.add(bean);
		when(dao.findByOrderForCustomer(1)).thenReturn(beans);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setData(beans);
		assertEquals(expected, service.findByOrderForCustomer(1));
	}

	@Test
	void findByOrderForCustomerTestElse() {
		List<OrderBean> beans = new ArrayList<OrderBean>();
		when(dao.findByOrderForCustomer(1)).thenReturn(beans);
		Response expected = ResponseUtil.fillerFailure("no order exist");
		assertEquals(expected, service.findByOrders());
	}

	@Test
	void findByOrderForCustomerTestException() {
		when(dao.findByOrderForCustomer(1)).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.findByOrderForCustomer(1));
	}

}
