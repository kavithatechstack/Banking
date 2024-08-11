package com.sbi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbi.dao.Account;
import com.sbi.dao.Customer;


@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
	
	boolean existsByAccountNumber(long accountNumber);
	Optional<Account> findByAccountNumber(long accountNumber);
		  
	

}
