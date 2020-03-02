package com.onebill.minionebill.service;

import com.onebill.minionebill.util.Response;

public interface OrderItemService {
	public Response getOrdersOnProduct(int productId);
	public Response deleteItem(int itemId);
}
