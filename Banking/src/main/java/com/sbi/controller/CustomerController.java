package com.sbi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbi.dao.Customer;
import com.sbi.dto.CustomerDTO;
import com.sbi.service.CustomerOpsInterface;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:4200")
public class CustomerController {

	@Autowired
	private CustomerOpsInterface customerService;

	@PostMapping("/saveCustomer")
	public ResponseEntity<CustomerDTO> addCustomer(@Valid @RequestBody Customer customer) {

		CustomerDTO customerDTO = customerService.saveCustomer(customer);

		return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.ACCEPTED);
	}

	@GetMapping("/viewCustomers")
	public ResponseEntity<List<CustomerDTO>> viewCustomers() {
		List<CustomerDTO> custLstDTO = customerService.viewCustomers();
		return new ResponseEntity<List<CustomerDTO>>(custLstDTO, HttpStatus.ACCEPTED);

	}

	@GetMapping("/findByName/{name}")
	public ResponseEntity<CustomerDTO> findById(@Valid @PathVariable String name) {
		CustomerDTO custDTO = customerService.findByCustomer(name);
		return new ResponseEntity<CustomerDTO>(custDTO, HttpStatus.ACCEPTED);

	}

	@PutMapping("updateCustomer/{name}")
	public ResponseEntity<CustomerDTO> updateByName(@Valid  @RequestBody Customer customer ,@PathVariable String  name) {
		CustomerDTO custDTO = customerService.updateCustomer(customer,name);
		return new ResponseEntity<CustomerDTO>(custDTO, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("deleteCust/{name}")
	public ResponseEntity<String> deleteByName(@Valid @PathVariable String name)
	{
		String message= customerService.deleteCustomer(name);
		return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
	}

}
