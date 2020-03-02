package com.onebill.minionebill.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.dto.CustomerBean;
import com.onebill.minionebill.repository.CustomerAddressRepository;
import com.onebill.minionebill.repository.CustomerRepository;

@Service
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	CustomerRepository repo;
	@Autowired
	CustomerAddressRepository addressRepo;

	public CustomerBean findByEmail(String email) {
		return repo.findByEmail(email);
	}

	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public CustomerBean createCustomer(CustomerBean bean) {
		try {
			CustomerBean customerBean = repo.save(bean);
			customerBean.getAddressBeans().forEach(addressBean -> {
				addressBean.setCustomerId(customerBean);
				addressRepo.save(addressBean);
			});
			return customerBean;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public CustomerBean createCustomerWithAddress(CustomerBean bean, CustomerAddressBean addressBeans) {
		try {
			CustomerBean customerBean = repo.save(bean);
			addressBeans.setCustomerId(customerBean);
			addressRepo.save(addressBeans);
			return customerBean;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void updateCustomer(CustomerBean bean) {
		try {

			CustomerBean customerBean = repo.findById(bean.getCustomerId()).get();
			customerBean.setFirstName(bean.getFirstName());
			customerBean.setLastName(bean.getLastName());
			customerBean.setPhoneNo(bean.getPhoneNo());
			repo.save(customerBean);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteCustomer(int id) {
		try {

			CustomerBean customerBean = repo.findById(id).get();
			customerBean.setStatus("blocked");
			repo.save(customerBean);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public CustomerBean findCustomer(String email) {
		try {
			CustomerBean bean = repo.findByEmail(email);
			return bean;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public CustomerBean findCustomerById(int id) {
		try {
			CustomerBean bean = repo.findById(id).get();
			bean.setAddressBeans(addressRepo.findAddressById(id));
			return bean;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<CustomerBean> findActiveCustomer() {
		try {
			List<CustomerBean> bean = repo.findActiveCustomer();
			return bean;
		} catch (Exception e) {
			throw e;
		}
	}
}
