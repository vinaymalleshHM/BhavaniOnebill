package com.onebill.minionebill.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.onebill.minionebill.dao.ProductDao;
import com.onebill.minionebill.dto.ProductBean;
import com.onebill.minionebill.util.Response;
import com.onebill.minionebill.util.ResponseUtil;

@SpringBootTest
class ProductServiceTest {
	@MockBean
	ProductDao dao;

	@Autowired
	ProductService service;

	ProductBean bean = new ProductBean();

	@Before
	void createProductObject() {
		bean.setProductId(1);
		bean.setAvgRating(4);
		bean.setDescription("test Description");
		bean.setName("test product");
		bean.setPrice(145);
		bean.setProductCode("123testcode");
	}

	// createProduct
	@Test
	void createProductTest() {
		when(dao.createProduct(bean)).thenReturn(bean);
		Response actual = service.createProduct(bean);
		Response expected = ResponseUtil.fillerSuccess("created successfully");
		expected.setData(bean);
		assertEquals(expected, actual);
	}

	@Test
	void createProductTestElse() {
		Response actual = service.createProduct(null);
		Response expected = ResponseUtil.fillerFailure("not created");
		assertEquals(expected, actual);
	}
	@Test
	void createProductTestException() {
		when(dao.createProduct(bean)).thenThrow(NoSuchElementException.class);
		Response actual = service.createProduct(bean);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, actual);
	}

//updated 
	@Test
	void updateProductTestById() {
		when(dao.existsById(bean.getProductId())).thenReturn(true);
		Response actual = service.updateProduct(bean);
		Response expected = ResponseUtil.fillerSuccess("updated successfully");
		assertEquals(expected, actual);
	}

	@Test
	void updateProductTestElseWithNull() {
		Response expected = ResponseUtil.fillerFailure("Product Id not present");
		assertEquals(expected, service.updateProduct(null));
	}

	@Test
	void updateProductTestElseWithId() {
		when(dao.existsById(bean.getProductId())).thenReturn(false);
		Response expected = ResponseUtil.fillerFailure("Product Id not present");
		assertEquals(expected, service.updateProduct(bean));
	}

	@Test
	void updateProductTestElseWithBoth() {
		when(dao.existsById(bean.getProductId())).thenReturn(false);
		Response expected = ResponseUtil.fillerFailure("Product Id not present");
		assertEquals(expected, service.updateProduct(null));
	}

	@Test
	void updateProductTestExceptionById() {
		when(dao.existsById(bean.getProductId())).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.updateProduct(bean));
	}

	@Test
	void updateProductTestExceptionByUpdate() {
		doThrow(NullPointerException.class).when(dao).updateProduct(bean);
		when(dao.existsById(bean.getProductId())).thenReturn(true);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.updateProduct(bean));
	}

	// deleteProduct
	@Test
	void deleteProductTest() {
		when(dao.existsById(bean.getProductId())).thenReturn(true);
		Response expected = ResponseUtil.fillerSuccess("deleted successfully");
		assertEquals(expected, service.deleteProduct(bean.getProductId()));
	}

	@Test
	void deleteProductTestElse() {
		when(dao.existsById(bean.getProductId())).thenReturn(false);
		Response expected = ResponseUtil.fillerFailure("product id not exist");
		assertEquals(expected, service.deleteProduct(bean.getProductId()));
	}

	@Test
	void deleteProductTestExceptionById() {
		when(dao.existsById(bean.getProductId())).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.deleteProduct(bean.getProductId()));
	}

	@Test
	void deleteProductTestException() {
		doThrow(NullPointerException.class).when(dao).deleteProduct(bean.getProductId());
		when(dao.existsById(bean.getProductId())).thenReturn(true);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.deleteProduct(bean.getProductId()));
	}

//find Active Product
	@Test
	void findActiveProduct() {
		List<ProductBean> beans = new ArrayList<ProductBean>();
		beans.add(bean);
		when(dao.findAllActiveProduct()).thenReturn(beans);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setData(beans);
		assertEquals(expected, service.findAllActiveProduct());
	}

	@Test
	void getProductTest() {
		List<ProductBean> beans = new ArrayList<ProductBean>();
		beans.add(bean);
		when(dao.findAllActiveProduct()).thenReturn(beans);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setData(beans);
		assertEquals(expected, service.findAllActiveProduct());
	}

	@Test
	void getProductTestElse() {
		when(dao.findAllActiveProduct()).thenReturn(null);
		Response expected = ResponseUtil.fillerFailure("no active product");
		assertEquals(expected, service.findAllActiveProduct());
	}

	@Test
	void getProductTestException() {
		when(dao.findAllActiveProduct()).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.findAllActiveProduct());
	}

	// find deleted Product
	@Test
	void findDeletedProduct() {
		List<ProductBean> beans = new ArrayList<ProductBean>();
		beans.add(bean);
		when(dao.findAllDeletedProduct()).thenReturn(beans);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setData(beans);
		assertEquals(expected, service.findAllDeletedProduct());
	}

	@Test
	void getDeletedTest() {
		List<ProductBean> beans = new ArrayList<ProductBean>();
		beans.add(bean);
		when(dao.findAllDeletedProduct()).thenReturn(beans);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setData(beans);
		assertEquals(expected, service.findAllDeletedProduct());
	}

	@Test
	void getDeletedTestElse() {
		List<ProductBean> beans = new ArrayList<ProductBean>();
		when(dao.findAllDeletedProduct()).thenReturn(beans);
		Response expected = ResponseUtil.fillerFailure("no deleted product");
		assertEquals(expected, service.findAllDeletedProduct());
	}

	@Test
	void getDeletedTestException() {
		when(dao.findAllDeletedProduct()).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.findAllDeletedProduct());
	}
//findProductById
	@Test
	void findProductByIdTest() {
		when(dao.existsById(bean.getProductId())).thenReturn(true);
		when(dao.findProductById(bean.getProductId())).thenReturn(bean);
		Response expected = ResponseUtil.fillerSuccess("found successfully");
		expected.setData(bean);
		assertEquals(expected, service.findProductById(bean.getProductId()));
	}

	@Test
	void findProductByIdTestElse() {
		when(dao.existsById(bean.getProductId())).thenReturn(false);
		Response expected = ResponseUtil.fillerFailure("id not exists");
		assertEquals(expected, service.findProductById(bean.getProductId()));
	}

	@Test
	void findProductByIdTestException() {
		when(dao.existsById(bean.getProductId())).thenThrow(NullPointerException.class);
		Response expected = ResponseUtil.fillerException(null);
		assertEquals(expected, service.findProductById(bean.getProductId()));
	}

}
