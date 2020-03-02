package com.onebill.minionebill.util;

import java.io.Serializable;
import java.util.List;

import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.dto.CustomerBean;
import com.onebill.minionebill.dto.OrderBean;
import com.onebill.minionebill.dto.OrderItemBean;
import com.onebill.minionebill.dto.ProductBean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response implements Serializable {
	private int statusCode;
	private String message;
	private String description;
	private Object data;
	private Object error;
	private List<CustomerAddressBean> listOfAddress;

	private List<CustomerBean> customerBeans;
	private List<ProductBean> productBeans;
	private List<OrderItemBean> itemBeans;
	private List<OrderBean> orderBeans;
}
