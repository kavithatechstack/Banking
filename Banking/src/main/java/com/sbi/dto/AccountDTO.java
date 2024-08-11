package com.sbi.dto;

import java.util.ArrayList;
import java.util.List;

import com.sbi.dao.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
	
	private long accountNumber;
	private double totalBalance;
	private String accType;
	private List<CustomerDTO> customerslst=new ArrayList<>();
	/*
	 * @Override public String toString() { return "AccountDTO [accountNumber=" +
	 * accountNumber + ", totalBalance=" + totalBalance + ", accType=" + accType +
	 * ", customerslst=" + customerslst + "]"; }
	 */
	

}
