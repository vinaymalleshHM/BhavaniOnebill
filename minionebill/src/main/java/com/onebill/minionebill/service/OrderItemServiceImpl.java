package com.onebill.minionebill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.minionebill.dao.OrderItemDao;
import com.onebill.minionebill.util.Response;
import com.onebill.minionebill.util.ResponseUtil;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	@Autowired
	OrderItemDao itemDao;
	
	@Override
	public Response getOrdersOnProduct(int productId) {
		Response response = null;
		try {
			int bean=itemDao.getOrdersOnProduct(productId);
			if(bean!=0) {
				response = ResponseUtil.fillerSuccess("found successfully");
				response.setData(bean);
			} else {
				response = ResponseUtil.fillerFailure("product not ordered");
			}
		} catch (Exception e) {
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;		
	}
	@Override
	public Response deleteItem(int itemId) {
		Response response = null;
		try {
			if(itemDao.existById(itemId)) {
				itemDao.deleteItem(itemId);
				response = ResponseUtil.fillerSuccess("deleted successfully");
			} else {
				response = ResponseUtil.fillerFailure(" item id not exist");
			}
		} catch (Exception e) {
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;	
	}
}
