package com.bank.errors.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.errors.model.CustomerProfiles;

public interface CustomerProfileDaoRepository extends JpaRepository<CustomerProfiles,Integer>  {

	
	@Query(value= "select * from customer_profiles where accountname=:name ",nativeQuery = true)
	List<CustomerProfiles> getallAccounts(@Param("name") String  name1);
}
