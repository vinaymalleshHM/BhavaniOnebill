package com.onebill.minionebill.dao;

import java.util.List;

import com.onebill.minionebill.dto.CustomerAddressBean;

public interface CustomerAddressDao {
	public CustomerAddressBean createCustomerAddress(CustomerAddressBean bean);
	public void updateCustomerAddress(CustomerAddressBean bean);
	public void deleteCustomerAddress(int id);
	public List<CustomerAddressBean> findCustomerAddressById(int id);
	public List<CustomerAddressBean>  findActiveCustomerAddress();
	public boolean existsById(int id);
}
