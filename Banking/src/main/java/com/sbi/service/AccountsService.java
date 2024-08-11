package com.sbi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbi.dao.Account;
import com.sbi.dao.Customer;
import com.sbi.dto.AccountDTO;
import com.sbi.dto.CustomerDTO;
import com.sbi.exception.ResourceNotFoundException;
import com.sbi.repository.AccountRepo;

@Service
public class AccountsService implements AccountOPSInterface {

	@Autowired
	AccountRepo accountRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	CustomerOpsInterface custService;
	
	int max=100,min=50;

	@Override
	public AccountDTO createAccount(Account account) {
		// TODO Auto-generated method stub
		account.setAccountNumber(min + (int)(Math.random() * ((max - min) + 1)));
		Account accountDB = accountRepo.save(account);
		
		//List<Customer> custLst = account.getCustomers();
		//List<CustomerDTO> custDTOLst = addOrUpdateCustomerForAccount(custLst,accountDB);
		AccountDTO accountDto = modelMapper.map(accountDB, AccountDTO.class);
		accountDto.setCustomerslst(getCustomersByAccount(accountDB));
		return accountDto;
	}

	@Override
	public AccountDTO updateAccount(Account account, Long accountNumber) {
		// TODO Auto-generated method stub
		
		  Account accountDb=accountRepo.findByAccountNumber(accountNumber).orElseThrow(ResourceNotFoundException::new);
		  
		  accountDb.setAccountNumber(accountNumber);
		  accountDb.setTotalBalance(account.getTotalBalance());
		  accountDb.setAccType(account.getAccType());
		  accountDb.setCustomers(account.getCustomers());
		  Account accountDB1 = accountRepo.save(accountDb);
		  AccountDTO accountDto = modelMapper.map(accountDB1, AccountDTO.class);
			accountDto.setCustomerslst(getCustomersByAccount(accountDB1));
			return accountDto;
	}

	public List<CustomerDTO> addOrUpdateCustomerForAccount(List<Customer> custLst,Account account) {
		List<CustomerDTO> custDTOLst = new ArrayList<>();
		for (Customer customer : custLst) {
			CustomerDTO custDTO;
			if (custService.customerExsistByName(customer.getName())) {
				custDTO = custService.findByCustomer(customer.getName());
				custDTOLst.add(custDTO);
			} else {
				customer.setAccount(account);
				custDTO = custService.saveCustomer(customer);
				custDTOLst.add(custDTO);
			}
		}
		return custDTOLst;
	}

	@Override
	public String deleteAccount(long accountNumber) {
		// TODO Auto-generated method stub
		String message;
		if(!accountRepo.existsByAccountNumber(accountNumber))
		{
			throw new ResourceNotFoundException(accountNumber +"  not found to delete");
		}
		else
		{
			Account cust=accountRepo.findByAccountNumber(accountNumber).get();
			accountRepo.delete(cust);
			message="Deleted Successfully";
		}
		return message;
	}

	@Override
	public AccountDTO findByAccountNumber(long accountNumber) {
		// TODO Auto-generated method stub
        Account account=accountRepo.findByAccountNumber(accountNumber).orElseThrow(ResourceNotFoundException::new);
        account.setCustomers(null);
        
        
      //  System.out.println(account);
		AccountDTO accountDto=modelMapper.map(account, AccountDTO.class);
		System.out.println(accountDto);
		List<CustomerDTO> custDTOLst=   getCustomersByAccount(account);
		accountDto.setCustomerslst(custDTOLst);
		return accountDto;
	}




	@Override
	public List<CustomerDTO> getCustomersByAccount(Account account) {
		// TODO Auto-generated method stub
		List<CustomerDTO> custDTOLst= custService.findCustomerByAccount(account);
		return custDTOLst;
	}

	@Override
	public List<CustomerDTO> getCustomersByAccountId(long accountNum) {
		// TODO Auto-generated method stub
		Account account=accountRepo.findByAccountNumber(accountNum).orElseThrow(ResourceNotFoundException::new);
        account.setCustomers(null);
		
		return getCustomersByAccount(account);
	}

}
