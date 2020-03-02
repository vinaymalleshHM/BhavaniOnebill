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

import com.onebill.minionebill.dao.CustomerAddressDao;
import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.dto.CustomerBean;
import com.onebill.minionebill.util.Response;
import com.onebill.minionebill.util.ResponseUtil;

@SpringBootTest
class CustomerAddressServiceTest {

	@MockBean
	CustomerAddressDao dao;

	@Autowired
	CustomerAddressService service;

	CustomerBean bean = new CustomerBean();
	CustomerAddressBean addressBean = new CustomerAddressBean();

	@Before
	void createCustomerAddressObject() {
		bean.setCustomerId(1);
		bean.setEmail("test@gmail.com");
		bean.setFirstName("test");
		bean.setLastName("g");
		bean.setPhoneNo("9876543210");
		addressBean.setCAddressId(1);
		addressBean.setAddress("test address");
		addressBean.setCity("testcity");
		addressBean.setState("karnataka");
		addressBean.setZipCode(12345);
	}
//createCustomerAddress
	@Test
	void createCustomerAddressTest() {
		when(dao.createCustomerAddress(addressBean)).thenReturn(addressBean);
		Response actual = service.createCustomerAddress(addressBean);
		Response expected = ResponseUtil.fillerSuccess("created successfully");
		expected.setData(addressBean);
		assertEquals(expected, actual);
	}
	@Test
	void createCustomerTestException() {
		when(dao.createCustomerAddress(addressBean)).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.createCustomerAddress(addressBean));
	}
	
	@Test
	void createCustomerAddressTestElse() {
		Response actual = service.createCustomerAddress(null);
		Response expected = ResponseUtil.fillerFailure("not created");
		assertEquals(expected, actual);
	}
	//updateCustomerAddress
	@Test
	void updateCustomerAddressTest() {
		when(dao.createCustomerAddress(addressBean)).thenReturn(addressBean);
		when(dao.existsById(addressBean.getCAddressId())).thenReturn(true);
		Response actual = service.updateCustomerAddress(addressBean);
		Response expected = ResponseUtil.fillerSuccess("updated successfully");
		assertEquals(expected, actual);
	}
	@Test
	void updateCustomerAddressTestElseByNull() {
		Response actual = service.updateCustomerAddress(null);
		Response expected = ResponseUtil.fillerFailure("not updated");
		assertEquals(expected, actual);
	}
	@Test
	void updateCustomerAddressTestElseById() {
		when(dao.existsById(addressBean.getCAddressId())).thenReturn(false);
		Response actual = service.updateCustomerAddress(addressBean);
		Response expected = ResponseUtil.fillerFailure("not updated");
		assertEquals(expected, actual);
	}
	@Test
	void updateCustomerAddressTestByBoth() {
		when(dao.existsById(addressBean.getCAddressId())).thenReturn(false);
		Response actual = service.updateCustomerAddress(null);
		Response expected = ResponseUtil.fillerFailure("not updated");
		assertEquals(expected, actual);
	}
	@Test
	void updateCustomerTestException() {
		doThrow(NullPointerException.class).when(dao).updateCustomerAddress(addressBean);
		when(dao.existsById(addressBean.getCAddressId())).thenReturn(true);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.updateCustomerAddress(addressBean));
	}
	@Test
	void updateCustomerTestExceptionById() {
		when(dao.existsById(addressBean.getCAddressId())).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.updateCustomerAddress(addressBean));
	}
	//deleteCustomerAddress
	@Test
	void deleteCustomerAddressTest() {
		when(dao.existsById(addressBean.getCAddressId())).thenReturn(true);
		Response actual = service.deleteCustomerAddress(addressBean.getCAddressId());
		Response expected =  ResponseUtil.fillerSuccess("deletd successfully");
		assertEquals(expected, actual);
	}
	@Test
	void deleteCustomerAddressTestElse() {
		when(dao.existsById(addressBean.getCAddressId())).thenReturn(false);
		Response actual = service.deleteCustomerAddress(addressBean.getCAddressId());
		Response expected =  ResponseUtil.fillerFailure("id does't exist deleted");
		assertEquals(expected, actual);
	}
	@Test
	void deleteCustomerTestException() {
		doThrow(NullPointerException.class).when(dao).deleteCustomerAddress(addressBean.getCAddressId());
		when(dao.existsById(addressBean.getCAddressId())).thenReturn(true);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.deleteCustomerAddress(addressBean.getCAddressId()));
	}
	@Test
	void deleteCustomerTestExceptionById() {
		when(dao.existsById(addressBean.getCAddressId())).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.deleteCustomerAddress(addressBean.getCAddressId()));
	}
	
	//findCustomerAddressById
	@Test
	void findCustomerAddressTestById() {
		List<CustomerAddressBean> addressBeans=new ArrayList<CustomerAddressBean>();
		addressBeans.add(addressBean);
		when(dao.findCustomerAddressById(addressBean.getCAddressId())).thenReturn(addressBeans);
		Response actual = service.findCustomerAddressById(addressBean.getCAddressId());
		Response expected =  ResponseUtil.fillerSuccess("found successfully");
		expected.setListOfAddress(addressBeans);
		assertEquals(expected, actual);
	}
	
	@Test
	void findCustomerAddressTestByIdElse() {
		List<CustomerAddressBean> addressBeans=new ArrayList<CustomerAddressBean>();
		when(dao.findCustomerAddressById(addressBean.getCAddressId())).thenReturn(addressBeans);
		Response actual = service.findCustomerAddressById(addressBean.getCAddressId());
		Response expected =  ResponseUtil.fillerFailure("no address for id");
		assertEquals(expected, actual);
	}
	@Test
	void findCustomerAddressTestByIdException() {
		when(dao.findCustomerAddressById(addressBean.getCAddressId())).thenThrow(NullPointerException.class);
		Response actual = service.findCustomerAddressById(addressBean.getCAddressId());
		Response expected =  ResponseUtil.fillerException(null);
		assertEquals(expected, actual);
	}
	//findActiveCustomerAddress
	@Test
	void findActiveCustomerAddressTest() {
		List<CustomerAddressBean> addressBeans=new ArrayList<CustomerAddressBean>();
		addressBeans.add(addressBean);
		when(dao.findActiveCustomerAddress()).thenReturn(addressBeans);
		Response actual = service.findActiveCustomerAddress();
		Response expected =  ResponseUtil.fillerSuccess("found successfully");
		expected.setListOfAddress(addressBeans);
		assertEquals(expected, actual);
	}
	
	@Test
	void findActiveCustomerAddressTestElse() {
		List<CustomerAddressBean> addressBeans=new ArrayList<CustomerAddressBean>();
		when(dao.findActiveCustomerAddress()).thenReturn(addressBeans);
		Response actual = service.findActiveCustomerAddress();
		Response expected =  ResponseUtil.fillerFailure("not found");
		assertEquals(expected, actual);
	}
	@Test
	void findActiveCustomerAddressTestException() {
		when(dao.findActiveCustomerAddress()).thenThrow(NullPointerException.class);
		Response actual = service.findActiveCustomerAddress();
		Response expected =  ResponseUtil.fillerException(null);
		assertEquals(expected, actual);
	}
}
