package com.onebill.minionebill.validation;

import java.util.regex.Pattern;

import com.onebill.minionebill.dto.CustomerAddressBean;
import com.onebill.minionebill.dto.CustomerBean;

public class Validation {

	private boolean isValidEmail(String email) {
		final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		return pat.matcher(email).matches();
	}

	private boolean isValidPhoneNo(String phoneNo) {

		if (phoneNo != null) {
			String phoneNumber = phoneNo.replace(" ", "");
			String phone = "[0-9]{10}";
			Pattern pat = Pattern.compile(phone);
			return pat.matcher(phoneNumber).matches();
		}
		return false;

	}

	private boolean isValidName(String name) {
		if (name != null && name.length() <= 20) {
			String newName = name.replace(" ", "");
			String phone = "[a-zA-Z]*";
			Pattern pat = Pattern.compile(phone);
			return pat.matcher(newName).matches();
		}
		return false;
	}

	public boolean customerValidation(CustomerBean customerBean) {
		return isValidEmail(customerBean.getEmail()) && isValidPhoneNo(customerBean.getEmail())
				&& isValidName(customerBean.getFirstName()) && isValidName(customerBean.getLastName());
	}
	
	public boolean customerAddressValidation(CustomerAddressBean addressBean) {
	
		
		return false;
		
	}

}
