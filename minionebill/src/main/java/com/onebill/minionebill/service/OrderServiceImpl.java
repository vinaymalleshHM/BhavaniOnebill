package com.onebill.minionebill.service;

import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.minionebill.dao.OrderDao;
import com.onebill.minionebill.dto.OrderBean;
import com.onebill.minionebill.util.Response;
import com.onebill.minionebill.util.ResponseUtil;
import java.util.logging.Level;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDao orderDao;
	
	private static final Logger loger = Logger.getLogger("onebill");

	@Override
	public Response createProduct(OrderBean itemBeans) {
		Response response = null;
		try {
			if (itemBeans.getOrderItemId().size() >0) {
				response = ResponseUtil.fillerSuccess("created successfully");
				response.setData(orderDao.createPrduct(itemBeans));
			} else {
				response = ResponseUtil.fillerFailure("order must contain atleast 1 item created");
			}
		} catch (Exception e) {
			loger.log(Level.SEVERE,e.getMessage());
			response = ResponseUtil.fillerException(e.getMessage());
		}
		return response;
	}

	@Override
	public Response findByOrderForCustomer( int id) {
		Response response = null;
		try {
			List<OrderBean> bean = orderDao.findByOrderForCustomer(id);
			if (bean.size()!=0) {
				response = ResponseUtil.fillerSuccess("found successfully");
				response.setData(bean);
			} else {
				response = ResponseUtil.fillerFailure("not found");
			}
		} catch (Exception e) {
			loger.log(Level.SEVERE,e.getMessage());
			response = ResponseUtil.fillerException(e.getMessage());
		}
		return response;
	}
	
	@Override
	public Response findByOrderById(int id) {
		Response response = null;
		try {
			
			if (orderDao.existById(id)) {
				OrderBean bean = orderDao.findByOrderById(id);
				response = ResponseUtil.fillerSuccess("found successfully");
				response.setData(bean);
			} else {
				response = ResponseUtil.fillerFailure("id not found");
			}
		} catch (Exception e) {
			loger.log(Level.SEVERE,e.getMessage());
			response = ResponseUtil.fillerException(e.getMessage());
		}
		return response;
	}
	@Override
	public Response  deleteByOrderById(int id) {
		Response response = null;
		try {
			if (orderDao.existById(id)) {
				 orderDao.deleteByOrderById(id);
				response = ResponseUtil.fillerSuccess("deleted successfully");
			} else {
				response = ResponseUtil.fillerFailure("id not found");
			}
		} catch (Exception e) {
			loger.log(Level.SEVERE,e.getMessage());
			response = ResponseUtil.fillerException(e.getMessage());
		}
		return response;
	}
	@Override
	public Response findByOrders() {
		Response response = null;
		try {
			List<OrderBean> beans=orderDao.findByOrders();
			if (beans.size()>0) {
				response = ResponseUtil.fillerSuccess("found successfully");
				response.setData(beans);
			} else {
				response = ResponseUtil.fillerFailure("no order exist");
			}
		} catch (Exception e) {
			loger.log(Level.SEVERE,e.getMessage());
			response = ResponseUtil.fillerException(e.getMessage());
		}
		return response;
	}

}
