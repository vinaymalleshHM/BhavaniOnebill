package com.onebill.minionebill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.minionebill.dao.ProductDao;
import com.onebill.minionebill.dto.ProductBean;
import com.onebill.minionebill.repository.ProductRepository;
import com.onebill.minionebill.util.Response;
import com.onebill.minionebill.util.ResponseUtil;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductDao productDao;
	@Override
	public Response createProduct(ProductBean bean) {
		Response response = null;
		try {
			if (bean!=null) {
				response = ResponseUtil.fillerSuccess("created successfully");
				response.setData(productDao.createProduct(bean));
			} else {
				response = ResponseUtil.fillerFailure("not created");
			}
		} catch (Exception e) {
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;
	}
	@Override
	public Response updateProduct(ProductBean bean) {
		Response response = null;
		try {
			if (bean != null && productDao.existsById(bean.getProductId())) {
				productDao.updateProduct(bean);
				response = ResponseUtil.fillerSuccess("updated successfully");
			} else {
				response = ResponseUtil.fillerFailure("Product Id not present");
			}
		} catch (Exception e) {
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;
	}
	@Override
	public Response deleteProduct(int id) {
		Response response = null;
		try {

			if ( productDao.existsById(id)) {
				productDao.deleteProduct(id);
				response = ResponseUtil.fillerSuccess("deleted successfully");
			} else {
				response = ResponseUtil.fillerFailure("product id not exist");
			}
		} catch (Exception e) {
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;
	}
	@Override
	public Response findAllActiveProduct() {
		Response response = null;
		try {
			List<ProductBean> bean=productDao.findAllActiveProduct();

			if(bean!=null) {
				response = ResponseUtil.fillerSuccess("found successfully");
				response.setData(bean);
			} else {
				response = ResponseUtil.fillerFailure("no active product");
			}
		} catch (Exception e) {
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;
	}
	
	@Override
	public Response findAllDeletedProduct() {
		Response response = null;
		try {
			List<ProductBean> bean=productDao.findAllDeletedProduct();

			if(bean.size()>0) {
				response = ResponseUtil.fillerSuccess("found successfully");
				response.setData(bean);
			} else {
				response = ResponseUtil.fillerFailure("no deleted product");
			}
		} catch (Exception e) {
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;
	}
	@Override
	public Response findProductById(int id) {
		Response response = null;
		try {
			if(productDao.existsById(id)) {
				response = ResponseUtil.fillerSuccess("found successfully");
				response.setData(productDao.findProductById(id));
			} else {
				response = ResponseUtil.fillerFailure("id not exists");
			}
		} catch (Exception e) {
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;
	}


}
