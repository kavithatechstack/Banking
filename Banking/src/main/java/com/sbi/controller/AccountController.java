package com.sbi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbi.dao.Account;
import com.sbi.dto.AccountDTO;
import com.sbi.dto.CustomerDTO;
import com.sbi.service.AccountOPSInterface;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountOPSInterface accountsService;

	@PostMapping("/createAccount")
	public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody Account account) {

		AccountDTO accountDTO = accountsService.createAccount(account);
		return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.CREATED);
	}

	@GetMapping("/findByAccountNumber/{accountNum}")
	public ResponseEntity<AccountDTO> getAccountDetails(@Valid  @PathVariable long accountNum) {

		AccountDTO accountDTO = accountsService.findByAccountNumber(accountNum);
		return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.FOUND);

	}

	@GetMapping("/findCustomersAccountNumber/{accountNum}")
	public ResponseEntity<List<CustomerDTO>> getCustomersByAccountNo(@Valid @PathVariable long accountNum) {

		List<CustomerDTO> custDto = accountsService.getCustomersByAccountId(accountNum);
		return new ResponseEntity(custDto, HttpStatus.FOUND);

	}
	
	@PutMapping("/updateCustomer/{accountNum}")
	public ResponseEntity<AccountDTO> updateAccount(@Valid @RequestBody Account account, @PathVariable long accountNum)
	{
		AccountDTO accountDTO=accountsService.updateAccount(account, accountNum);
		return new ResponseEntity<AccountDTO>(accountDTO,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteCustomer/{accountNum}")
	public ResponseEntity<String> deleteAccount(@Valid @RequestBody Account account, @PathVariable long accountNum)
	{
		String str=accountsService.deleteAccount(accountNum);
		return new ResponseEntity<String>(str,HttpStatus.ACCEPTED);
	}

}
