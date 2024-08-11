package com.sbi.dao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
//@Builder
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	@NotBlank
	@Size(min=3,max=20,message="Lentgh of name should be between 3-20 chars")
	private String name;
	@NotNull
	
	private long phoneNum;
	@NotBlank(message="address can not be blank")
	private String address;
	@NotBlank(message="gender can not be blank")
	private String gender;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id_fk")
	private Account account;



	/*
	 * public Customer(long id, String name, long phoneNum, String address, String
	 * gender) { super(); this.id = id; this.name = name; this.phoneNum = phoneNum;
	 * this.address = address; this.gender = gender; }
	 */

}
