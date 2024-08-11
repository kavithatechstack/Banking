package com.sbi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sbi.dao.Account;
import com.sbi.dao.Customer;
import com.sbi.dto.CustomerDTO;
import com.sbi.exception.ResourceAlreadyExsistException;
import com.sbi.exception.ResourceNotFoundException;
import com.sbi.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service

public class CustomerService implements CustomerOpsInterface{
    
	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CustomerDTO saveCustomer(Customer cust) throws ResourceAlreadyExsistException  {
		// TODO Auto-generated method stub
		String name=cust.getName();
		if(custRepo.existsByName(name))
		{
			throw new ResourceAlreadyExsistException(name +"  name is already exsist");
		}
		else
		{
	     Customer customer=custRepo.save(cust);
	     CustomerDTO customerDTO= modelMapper.map(customer,CustomerDTO.class);
	     return customerDTO;
		}
	}

	@Override
	public CustomerDTO findByCustomer(String custrName) {
		// TODO Auto-generated method stub
		
		
		
		Customer cust=custRepo.findByName(custrName)
		.orElseThrow(ResourceNotFoundException::new);
		System.out.println(cust);
		CustomerDTO customerDTO= modelMapper.map(cust,CustomerDTO.class);
		return customerDTO;
	}

	@Override
	public CustomerDTO updateCustomer(Customer cust,String name) {
		// TODO Auto-generated method stub
		//String name=cust.getName();
		Customer customer;
		CustomerDTO custDTO;
		if(!custRepo.existsByName(name))
		{
			throw new ResourceNotFoundException(name +"  not found to update");
		}
		else
		{
			customer=custRepo.findByName(name).get();
			customer.setName(cust.getName());
			customer.setAddress(cust.getAddress());
			customer.setGender(cust.getGender());
			customer.setPhoneNum(cust.getPhoneNum());
			
			custDTO=modelMapper.map(custRepo.save(customer),CustomerDTO.class);
			return custDTO;
		}
		
	}

	@Override
	public String deleteCustomer(String custName) {
		// TODO Auto-generated method stub
		String message;
		if(!custRepo.existsByName(custName))
		{
			throw new ResourceNotFoundException(custName +"  not found to delete");
		}
		else
		{
			Customer cust=custRepo.findByName(custName).get();
			custRepo.delete(cust);
			message="Deleted Successfully";
		}
		return message;
	}

	@Override
	public List<CustomerDTO> viewCustomers() {
		// TODO Auto-generated method stub
		List<Customer> custLst=custRepo.findAll();
	     List<CustomerDTO> custDTOLst=custLst.stream()
	    		 			.map(cust->modelMapper.map(cust, CustomerDTO.class))
	    		 			.collect(Collectors.toList());
	     
	     return custDTOLst;
	}
	
	public boolean customerExsistByName(String name)
	{
		return custRepo.existsByName(name);
	}

	@Override
	public List<CustomerDTO> findCustomerByAccount(Account account) {
		// TODO Auto-generated method stub
		List<Customer> custinDb=custRepo.findByAccount(account);
		System.out.println(custinDb);
		 return custinDb.stream().map(cust->modelMapper.map(cust, CustomerDTO.class)).collect(Collectors.toList());
	}
	


}
