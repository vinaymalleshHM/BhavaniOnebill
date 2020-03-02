package com.onebill.minionebill.dao;

import java.util.List;

import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.dto.CustomerBean;

public interface CustomerDao {
	public CustomerBean findCustomer(String email);
	public List<CustomerBean> findActiveCustomer();

	public void deleteCustomer(int id );
	public void updateCustomer(CustomerBean bean);
	public CustomerBean createCustomer(CustomerBean bean);
	public CustomerBean createCustomerWithAddress(CustomerBean bean, CustomerAddressBean addressBeans);
	public CustomerBean findByEmail(String email);
	public boolean existsById(int id);
	public CustomerBean findCustomerById(int id);
}
