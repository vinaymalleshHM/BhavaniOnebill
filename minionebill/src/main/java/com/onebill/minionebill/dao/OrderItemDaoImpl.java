package com.onebill.minionebill.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.minionebill.repository.OrderItemRepository;

@Service
public class OrderItemDaoImpl implements OrderItemDao {
	@Autowired
	OrderItemRepository itemRepository;

	@Override
	public boolean existById(int id) {
		return existById(id);
	}

	@Override
	public int getOrdersOnProduct(int productId) {
		try {
			return itemRepository.getOrdersOnProduct(productId).size();
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public void deleteItem(int id) {
			try {
				 itemRepository.deleteById(id);
			} catch (Exception e) {
				throw e;
			}
	}

}
