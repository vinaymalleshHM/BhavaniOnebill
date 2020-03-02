package com.onebill.minionebill.service;


import com.onebill.minionebill.dto.OrderBean;
import com.onebill.minionebill.util.Response;

public interface OrderService {
	public Response createProduct(OrderBean orderBean);
	public Response findByOrderForCustomer(int id);
	public Response findByOrderById(int id);
	public Response  deleteByOrderById(int id);
	public Response findByOrders();
}
