package com.onebill.minionebill.service;

import com.onebill.minionebill.dto.ProductBean;
import com.onebill.minionebill.util.Response;

public interface ProductService {
	public Response findAllActiveProduct();

	public Response findProductById(int id);

	public Response findAllDeletedProduct();
	
	public Response deleteProduct(int id);

	public Response updateProduct(ProductBean bean);

	public Response createProduct(ProductBean bean);
}
