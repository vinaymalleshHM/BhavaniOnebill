package com.onebill.minionebill.dao;

import java.util.List;

import com.onebill.minionebill.dto.OrderBean;

public interface OrderDao {
	public OrderBean createPrduct(OrderBean itemBeans);
	public List<OrderBean> findByOrderForCustomer(int id);
	public List<OrderBean> findByOrders();
	public OrderBean findByOrderById(int id);
	public boolean existById(int id);
	public void  deleteByOrderById(int id) ;

	
}
