package com.onebill.minionebill.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onebill.minionebill.dto.OrderItemBean;

public interface OrderItemRepository extends JpaRepository<OrderItemBean, Integer>{

	@Query(value="select * from order_item_info where order_id in (select order_id from order_info where customer_id=(select customer_id from customer_info where email=:email))",nativeQuery = true)
	public List<OrderItemBean> findOrderByEmail(String email);
	

	@Query(value = "select count(o.productId) from OrderItemBean o where o.productId.productId=:productId group by o.productId.productId ,o.orderId.customerId.customerId")
	public List<Integer> getOrdersOnProduct(int productId);

}
