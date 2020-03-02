package com.onebill.minionebill.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.minionebill.dto.OrderBean;
import com.onebill.minionebill.repository.OrderItemRepository;
import com.onebill.minionebill.repository.OrderRepository;

@Service
public class OrderDaoImpl implements OrderDao {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemRepository itemRepository;
	@Override
	public OrderBean createPrduct( OrderBean orderBean) {
		try {
				OrderBean bean=orderRepository.save(orderBean);
				bean.getOrderItemId().forEach(itemBean->{
					itemBean.setOrderId(bean);
					itemRepository.save(itemBean);
				});
				return bean;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<OrderBean> findByOrderForCustomer(int id) {
		try {
			List<OrderBean> orderBeans=orderRepository.getOrderById(id);
			return orderBeans;
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public List<OrderBean> findByOrders(){
		try {
			List<OrderBean> orderBeans=orderRepository.findAll();
			return orderBeans;
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public OrderBean findByOrderById(int id) {
		try {
			OrderBean orderBeans=orderRepository.findById(id).get();
			return orderBeans;
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public boolean existById(int id) {
		try {
			return orderRepository.existsById(id);
		} catch (Exception e) {
			throw e;
		}
	}
	public void  deleteByOrderById(int id) {
	try {
		orderRepository.deleteById(id);
	} catch (Exception e) {
		throw e;
	}
}


}
