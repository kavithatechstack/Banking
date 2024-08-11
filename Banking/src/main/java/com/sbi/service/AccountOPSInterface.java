package com.sbi.service;

import java.util.List;

import com.sbi.dao.Account;
import com.sbi.dao.Customer;
import com.sbi.dto.AccountDTO;
import com.sbi.dto.CustomerDTO;

public interface AccountOPSInterface {
	
	public AccountDTO createAccount(Account account);
	public AccountDTO updateAccount(Account account,Long accountNumber);
	public String deleteAccount(long accountNumber);
	public AccountDTO findByAccountNumber(long accountNumber);
	
	
	public List<CustomerDTO> getCustomersByAccountId(long accountNum);
	public List<CustomerDTO> getCustomersByAccount(Account account);
	}

