package com.onebill.minionebill.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.minionebill.dto.ProductBean;
import com.onebill.minionebill.repository.ProductRepository;

@Service
public class ProductDaoImpl implements ProductDao {
	@Autowired
	ProductRepository productRepository;
	@Override
	public boolean existsById(int id) {
		return productRepository.existsById(id);
	}
	@Override
	public ProductBean createProduct(ProductBean bean) {
		try {
				return productRepository.save(bean);
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public void updateProduct(ProductBean bean) {
		try {
				ProductBean productBean = productRepository.findById(bean.getProductId()).get();
				productBean.setAvgRating(bean.getAvgRating());
				productBean.setDescription(bean.getDescription());
				productBean.setName(bean.getName());
				productBean.setPrice(bean.getPrice());
				productRepository.save(bean);
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public void deleteProduct(int id) {
		try {
				ProductBean product = productRepository.findById(id).get();
				product.setStatus("blocked");
				productRepository.save(product);
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public List<ProductBean> findAllActiveProduct() {
		try {
			List<ProductBean> bean=productRepository.getAllActiveProduct();
			return bean;
		} catch (Exception e) {
			throw e;
		}
	}
	public ProductBean findProductById(int id) {
	try {
		ProductBean bean=productRepository.findById(id).get();
		return bean;
	} catch (Exception e) {
		throw e;
	}
}
	@Override
	public List<ProductBean> findAllDeletedProduct() {
		try {
			List<ProductBean> bean=productRepository.getAllActiveProduct();
			return bean;
		} catch (Exception e) {
			throw e;
		}
	}


}
