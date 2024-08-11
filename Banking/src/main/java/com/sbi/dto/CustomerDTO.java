package com.sbi.dto;

import java.util.List;

import com.sbi.dao.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	

	private String name;
	private long phoneNum;
	private String address;
	private String gender;

	/*
	 * @Override public String toString() { return "CustomerDTO [name=" + name +
	 * ", phoneNum=" + phoneNum + ", address=" + address + ", gender=" + gender +
	 * "]"; }
	 */
	
	
	

}
