package com.onebill.minionebill.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onebill.minionebill.dao.CustomerDao;
import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.dto.CustomerBean;
import com.onebill.minionebill.util.Response;
import com.onebill.minionebill.util.ResponseUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class CustomerServiceTest {

	@MockBean
	CustomerDao dao;

	@Autowired
	CustomerService service;
	
	CustomerBean bean = new CustomerBean();
	CustomerAddressBean addressBean = new CustomerAddressBean();

	@Before
	void createCustmerObject() {
		bean.setCustomerId(1);
		bean.setEmail("test@gmail.com");
		bean.setFirstName("test");
		bean.setLastName("g");
		bean.setPhoneNo("9876543210");
		addressBean.setAddress("test address");
		addressBean.setCity("testcity");
		addressBean.setState("karnataka");
		addressBean.setZipCode(12345);
	}

	// createCustomer
	@Test
	void createCustomerTest() {
		when(dao.createCustomer(bean)).thenReturn(bean);
		Response actual = service.createCustomer(bean);
		Response expected = ResponseUtil.fillerSuccess("created successfully");
		expected.setData(bean);
		assertEquals(expected, actual);
	}

	@Test
	void createCustomerTestElse() {
		when(dao.findByEmail(bean.getEmail())).thenReturn(bean);
		Response expected = ResponseUtil.fillerFailure("email is already exist created");
		assertEquals(expected, service.createCustomer(bean));
	}

	@Test
	void createCustomerTestExceptionByMail() {
		when(dao.findByEmail(bean.getEmail())).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.createCustomer(bean));
	}

	@Test
	void createCustomerTestException() {
		when(dao.createCustomer(bean)).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.createCustomer(bean));
	}

//createCustomerWithAddress
	@Test
	void createCustomerWithAddressTest() {
		when(dao.createCustomerWithAddress(bean, addressBean)).thenReturn(bean);
		when(dao.findCustomerById(bean.getCustomerId())).thenReturn(bean);
		Response expected = ResponseUtil.fillerSuccess("created successfully");
		expected.setData(bean);
		assertEquals(expected, service.createCustomerWithAddress(bean, addressBean));
	}

	@Test
	void createCustomerWithAddressTestElse() {
		when(dao.findCustomerById(bean.getCustomerId())).thenReturn(bean);
		Response expected = ResponseUtil.fillerFailure("not  created");
		assertEquals(expected, service.createCustomerWithAddress(null, addressBean));
	}

	@Test
	void createCustomerWithAddressTestElseWithEmail() {
		when(dao.findByEmail(bean.getEmail())).thenReturn(bean);
		Response expected = ResponseUtil.fillerFailure("not  created");
		assertEquals(expected, service.createCustomerWithAddress(bean, addressBean));
	}

	@Test
	void createCustomerWithAddressTestElseWithBoth() {
		when(dao.findByEmail(bean.getEmail())).thenReturn(bean);
		Response expected = ResponseUtil.fillerFailure("not  created");
		assertEquals(expected, service.createCustomerWithAddress(null, addressBean));
	}
	@Test
	void createCustomerWithAddressTestExceptionByEmail() {
		when(dao.findByEmail(bean.getEmail())).thenThrow(NoSuchElementException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.createCustomerWithAddress(bean, addressBean));
	}
	@Test
	void createCustomerWithAddressTestExceptionByDao() {
		when(dao.createCustomerWithAddress(bean,addressBean)).thenThrow(NoSuchElementException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.createCustomerWithAddress(bean, addressBean));
	}
	
//updateCustomer
	@Test
	void updateCustomerTestById() {
		when(dao.existsById(bean.getCustomerId())).thenReturn(true);
		Response actual = service.updateCustomer(bean);
		Response expected = ResponseUtil.fillerSuccess("updated successfully");
		assertEquals(expected, actual);
	}
	
	@Test
	void updateCustomerTestElseWithNull() {
		Response expected = ResponseUtil.fillerFailure("Customer Id not present");
		assertEquals(expected, service.updateCustomer(null));
	}

	@Test
	void updateCustomerTestElseWithId() {
		when(dao.existsById(bean.getCustomerId())).thenReturn(false);
		Response expected = ResponseUtil.fillerFailure("Customer Id not present");
		assertEquals(expected, service.updateCustomer(bean));
	}

	@Test
	void updateCustomerTestElseWithBoth() {
		when(dao.existsById(bean.getCustomerId())).thenReturn(false);
		Response expected = ResponseUtil.fillerFailure("Customer Id not present");
		assertEquals(expected, service.updateCustomer(null));
	}
	@Test
	void updateCustomerTestExceptionById() {
		when(dao.existsById(bean.getCustomerId())).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.updateCustomer(bean));
	}
	@Test
	void updateCustomerTestExceptionByUpdate() {
		doThrow(NullPointerException.class).when(dao).updateCustomer(bean);
		when(dao.existsById(bean.getCustomerId())).thenReturn(true);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.updateCustomer(bean));
	}
//deleteCustomer
	@Test
	void deleteCustomerTest() {
		when(dao.existsById(bean.getCustomerId())).thenReturn(true);
		Response expected = ResponseUtil.fillerSuccess("deleted successfully");
		assertEquals(expected, service.deleteCustomer(bean.getCustomerId()));
	}

	@Test
	void deleteCustomerTestElse() {
		when(dao.existsById(bean.getCustomerId())).thenReturn(false);
		Response expected = ResponseUtil.fillerFailure("Email Id not exist");
		assertEquals(expected, service.deleteCustomer(bean.getCustomerId()));
	}
	@Test
	void deleteCustomerTestExceptionById() {
		when(dao.existsById(bean.getCustomerId())).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.deleteCustomer(bean.getCustomerId()));
	}
	@Test
	void deleteCustomerTestException() {
		doThrow(NullPointerException.class).when(dao).deleteCustomer(bean.getCustomerId());
		when(dao.existsById(bean.getCustomerId())).thenReturn(true);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.deleteCustomer(bean.getCustomerId()));
	}
	
	// findCustomer
	@Test
	void findCustomerTest() {
		when(dao.findCustomer("test@gmail.com")).thenReturn(bean);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setData(bean);
		assertEquals(expected, service.findCustomer("test@gmail.com"));
	}

	@Test
	void findCustomerTestElse() {
		when(dao.findCustomer("test@gmail.com")).thenReturn(null);
		Response expected = ResponseUtil.fillerFailure("email not exist");
		assertEquals(expected, service.findCustomer("test@gmail.com"));
	}
	@Test
	void findCustomerTestExceptionByMail() {
		when(dao.findCustomer("test@gmail.com")).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.findCustomer("test@gmail.com"));
	}

//findCustomerById
	@Test
	void findCustomerByIdTest() {
		when(dao.findCustomerById(bean.getCustomerId())).thenReturn(bean);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setData(bean);
		assertEquals(expected, service.findCustomerById(bean.getCustomerId()));
	}

	@Test
	void findCustomerByIdTestElse() {
		when(dao.findCustomerById(bean.getCustomerId())).thenReturn(null);
		Response expected = ResponseUtil.fillerFailure("id not found");
		assertEquals(expected, service.findCustomerById(bean.getCustomerId()));
	}
	
	@Test
	void findCustomerByIdTestException() {
		when(dao.findCustomerById(bean.getCustomerId())).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.findCustomerById(bean.getCustomerId()));
	}

	// findActiveCustomerdescription
	@Test
	void findActiveCustomer() {
		List<CustomerBean> beans = new ArrayList<CustomerBean>();
		beans.add(bean);
		when(dao.findActiveCustomer()).thenReturn(beans);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setCustomerBeans(beans);
		assertEquals(expected, service.findActiveCustomer());
	}

	@Test
	void getCustomerTest() {
		List<CustomerBean> beans = new ArrayList<CustomerBean>();
		beans.add(bean);
		when(dao.findActiveCustomer()).thenReturn(beans);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setCustomerBeans(beans);
		assertEquals(expected, service.findActiveCustomer());
	}

	@Test
	void getCustomerTestElse() {
		when(dao.findActiveCustomer()).thenReturn(null);
		Response expected = ResponseUtil.fillerFailure("not found");
		assertEquals(expected, service.findActiveCustomer());
	}
	@Test
	void getCustomerTestException() {
		when(dao.findActiveCustomer()).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.findActiveCustomer());
	}

}
