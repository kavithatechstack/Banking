package com.sbi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbi.dao.Account;
import com.sbi.dao.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	//@Query("select case when count(c)> 0 then true else false end from Car c where lower(c.model) like lower(:model)")
	boolean existsByName(String name);
	Optional<Customer>  findByName(String name);
	//@Query(value = "SELECT cust FROM Customer cust WHERE cust.account_id_fk = :accountNo")
	//@Query("select u.userName from User u inner join u.area ar where ar.idArea = :idArea")
	//List<Customer> findByAccountNo(@Param("accountNo") long accountNo);
	List<Customer> findByAccount( Account account);
	boolean deleteByName(String name);
	
	
}
