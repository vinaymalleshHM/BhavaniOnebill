package com.onebill.minionebill.service;

import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.dto.CustomerBean;
import com.onebill.minionebill.util.Response;

public interface CustomerService {
	public Response findCustomer(String email);
	public Response findActiveCustomer();
	public Response findCustomerById(int id);
	public Response deleteCustomer(int id);
	public Response updateCustomer(CustomerBean bean);
	public Response createCustomer(CustomerBean bean);
	public Response createCustomerWithAddress(CustomerBean bean, CustomerAddressBean addressBeans);
}
