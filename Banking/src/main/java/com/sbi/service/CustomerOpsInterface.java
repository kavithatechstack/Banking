package com.sbi.service;

import java.util.List;

import com.sbi.dao.Account;
import com.sbi.dao.Customer;
import com.sbi.dto.CustomerDTO;

public interface CustomerOpsInterface {
	
	public CustomerDTO saveCustomer(Customer cust);
	public CustomerDTO findByCustomer(String custrName);
	public CustomerDTO updateCustomer(Customer cust,String name);
	public String deleteCustomer(String custName);
	public List<CustomerDTO> viewCustomers();
	public boolean customerExsistByName(String name);
	public List<CustomerDTO> findCustomerByAccount(Account accountNo);
	


}
