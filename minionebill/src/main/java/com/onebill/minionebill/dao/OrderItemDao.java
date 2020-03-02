package com.onebill.minionebill.dao;

public interface OrderItemDao {
	public int getOrdersOnProduct(int productId);
	public boolean existById(int productId);
	public void deleteItem(int productId);

}
