package com.redacode.springdemo.dao;

import java.util.List;

import com.redacode.springdemo.entity.Customer;

public interface CustomerDAO {

	List<Customer> getCustomers();
}
