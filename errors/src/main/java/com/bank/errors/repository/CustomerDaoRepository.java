package com.bank.errors.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.errors.model.Customer;

public interface CustomerDaoRepository extends JpaRepository<Customer,Integer>{

}
