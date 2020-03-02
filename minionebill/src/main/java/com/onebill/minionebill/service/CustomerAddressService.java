package com.onebill.minionebill.service;
import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.util.Response;

public interface CustomerAddressService {
	public Response createCustomerAddress(CustomerAddressBean bean);
	public Response updateCustomerAddress(CustomerAddressBean bean);
	public Response deleteCustomerAddress(int id);
	public Response findCustomerAddressById(int id);
	public Response findActiveCustomerAddress();
	
}
