package com.onebill.minionebill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.minionebill.dao.CustomerDao;
import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.dto.CustomerBean;
import com.onebill.minionebill.util.Response;
import com.onebill.minionebill.util.ResponseUtil;

import lombok.extern.java.Log;

@Service
@Log
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Override
	public Response createCustomer(CustomerBean bean) {
		Response response = null;
		try {
			if (customerDao.findByEmail(bean.getEmail()) == null) {
				response = ResponseUtil.fillerSuccess("created successfully");
				response.setData(customerDao.createCustomer(bean));
			} else {
				response = ResponseUtil.fillerFailure("email is already exist created");
			}

		} catch (Exception e) {
			log.severe(e.getMessage());
			response = ResponseUtil.fillerException(e.getMessage());
		}
		return response;
	}

	@Override
	public Response createCustomerWithAddress(CustomerBean bean, CustomerAddressBean addressBeans) {
		Response response = null;
		try {
			if (bean != null  && customerDao.findByEmail(bean.getEmail()) == null) {
				response = ResponseUtil.fillerSuccess("created successfully");
				response.setData(customerDao.createCustomerWithAddress(bean, addressBeans));

			} else {
				response = ResponseUtil.fillerFailure("not  created");
			}
		} catch (Exception e) {
			response = ResponseUtil.fillerException(e.getMessage());
			log.severe(e.getMessage());
		}
		return response;
	}

	@Override
	public Response updateCustomer(CustomerBean bean) {
		Response response = null;
		try {
			if (bean != null && customerDao.existsById(bean.getCustomerId())) {
				customerDao.updateCustomer(bean);
				response = ResponseUtil.fillerSuccess("updated successfully");
			} else {
				response = ResponseUtil.fillerFailure("Customer Id not present");
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
			response = ResponseUtil.fillerException(e.getMessage());
		}
		return response;
	}

	@Override
	public Response deleteCustomer(int id) {
		Response response = null;
		try {
			if (customerDao.existsById(id)) {
				customerDao.deleteCustomer(id);
				response = ResponseUtil.fillerSuccess("deleted successfully");
			} else {
				response = ResponseUtil.fillerFailure("Email Id not exist");
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;
	}

	@Override
	public Response findCustomer(String email) {
		Response response = null;
		try {
			CustomerBean bean = customerDao.findCustomer(email);
			if (bean != null) {
				response = ResponseUtil.fillerSuccess("found successfully");
				response.setData(bean);
			} else {
				response = ResponseUtil.fillerFailure("email not exist");
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;
	}

	@Override
	public Response findCustomerById(int id) {
		Response response = null;
		try {
			CustomerBean bean = customerDao.findCustomerById(id);
			if (bean != null) {
				response = ResponseUtil.fillerSuccess("found successfully");
				response.setData(bean);
			} else {
				response = ResponseUtil.fillerFailure("id not found");
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;
	}

	public Response findActiveCustomer() {
		Response response = null;
		try {
			List<CustomerBean> bean = customerDao.findActiveCustomer();

			if (bean != null) {
				response = ResponseUtil.fillerSuccess("found successfully");
				response.setCustomerBeans(bean);
			} else {
				response = ResponseUtil.fillerFailure("not found");
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;
	}
}
