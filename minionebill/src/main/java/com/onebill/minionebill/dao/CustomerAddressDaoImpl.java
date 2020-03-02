package com.onebill.minionebill.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.repository.CustomerAddressRepository;

@Service
public class CustomerAddressDaoImpl implements CustomerAddressDao{
	@Autowired 
	CustomerAddressRepository customerRepository;
	
	public boolean existsById(int id) {
			return customerRepository.existsById(id);
	}
	@Override
	public CustomerAddressBean createCustomerAddress(CustomerAddressBean bean) {
		try {
			return(customerRepository.save(bean));
		} catch (Exception e) {
				throw e;
		}
	}
	@Override
	public void updateCustomerAddress(CustomerAddressBean bean) {
		try {

				CustomerAddressBean customerBean = customerRepository.findById(bean.getCAddressId()).get();
				customerBean.setAddress(bean.getAddress());
				customerBean.setCity(bean.getCity());
				customerBean.setState(bean.getState());
				customerRepository.save(customerBean);
			
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public void deleteCustomerAddress(int id) {
		try {
				customerRepository.deleteById(id);
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public List<CustomerAddressBean> findCustomerAddressById(int id) {
		try {
				List<CustomerAddressBean> bean=customerRepository.findAddressById(id);
				return bean;
		} catch (Exception e) {
			throw e;
		}
	}
	public List<CustomerAddressBean>  findActiveCustomerAddress() {
			try {
				List<CustomerAddressBean> bean = customerRepository.findAddress();
				return bean;
			} catch (Exception e) {
				throw e;
			}
	}
	

}
