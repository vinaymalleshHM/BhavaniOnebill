package com.onebill.minionebill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.minionebill.dao.CustomerAddressDao;
import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.util.Response;
import com.onebill.minionebill.util.ResponseUtil;

import lombok.extern.java.Log;

@Service
@Log
public class CustomerAddressServiceImpl implements CustomerAddressService{
	
	@Autowired 
	CustomerAddressDao addressDao;
	

	@Override
	public Response createCustomerAddress(CustomerAddressBean bean) {
		Response response = null;
		try {
			if (bean != null) {
				response = ResponseUtil.fillerSuccess("created successfully");
				response.setData(addressDao.createCustomerAddress(bean));
			} else {
				response = ResponseUtil.fillerFailure("not created");
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
			response = ResponseUtil.fillerException( e.getMessage());
		}
		return response;
	}
	@Override
	public Response updateCustomerAddress(CustomerAddressBean bean) {
		Response response = null;
		try {

			if (bean != null && addressDao.existsById(bean.getCAddressId())) {
				addressDao.updateCustomerAddress(bean);
				response = ResponseUtil.fillerSuccess("updated successfully");
			} else {
				response = ResponseUtil.fillerFailure("not updated");
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
			response = ResponseUtil.fillerException(e.getMessage());
		}
		return response;
	}
	@Override
	public Response deleteCustomerAddress(int id) {
		Response response = null;
		try {

			if ( addressDao.existsById(id)) {
					addressDao.deleteCustomerAddress(id);
				response = ResponseUtil.fillerSuccess("deletd successfully");
			} else {
				response = ResponseUtil.fillerFailure("id does't exist deleted");
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
			response = ResponseUtil.fillerException(e.getMessage());
		}
		return response;
	}
	@Override
	public Response findCustomerAddressById(int id) {
		Response response = null;
		try {
	
				List<CustomerAddressBean> bean=addressDao.findCustomerAddressById(id);;
				if(bean.size()>0) {
				response = ResponseUtil.fillerSuccess("found successfully");
				response.setListOfAddress(bean);
			} else {
				response = ResponseUtil.fillerFailure("no address for id");
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
			response = ResponseUtil.fillerException(e.getMessage());
		}
		return response;
	}
	public Response findActiveCustomerAddress() {
			Response response = null;
			try {
				List<CustomerAddressBean> bean = addressDao.findActiveCustomerAddress();

				if (bean .size()>0) {
					response = ResponseUtil.fillerSuccess("found successfully");
					response.setListOfAddress(bean);
				} else {
					response = ResponseUtil.fillerFailure("not found");
				}
			} catch (Exception e) {
				log.severe(e.getMessage());
				response = ResponseUtil.fillerException(e.getMessage());
			}
			return response;
	}
	

}
