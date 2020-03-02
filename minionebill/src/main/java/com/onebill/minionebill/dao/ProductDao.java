package com.onebill.minionebill.dao;

import java.util.List;

import com.onebill.minionebill.dto.ProductBean;
import com.onebill.minionebill.util.Response;

public interface ProductDao {
	public List<ProductBean> findAllActiveProduct();
	
	public List<ProductBean> findAllDeletedProduct();
	
	public ProductBean findProductById(int id);

	public void deleteProduct(int id );

	public void updateProduct(ProductBean bean);

	public ProductBean createProduct(ProductBean bean);
	public boolean existsById(int id);


	
}
