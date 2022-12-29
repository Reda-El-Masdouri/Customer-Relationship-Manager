package com.redacode.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.redacode.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query .. sort by the last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName",
				Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the results
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {

		// get current hibernate session
		Session curentSession = sessionFactory.getCurrentSession();
		
		// save theCustomer
		curentSession.saveOrUpdate(theCustomer);
	}


	@Override
	public Customer getCustomer(int theId) {
		// get current hibernate session
		Session curentSession = sessionFactory.getCurrentSession();
		return curentSession.get(Customer.class, theId);
	}


	@Override
	public void deleteCustomer(int theId) {
		// get current hibernate session
		Session curentSession = sessionFactory.getCurrentSession();	
		
		Query theQuery = curentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
		// second method
//		Customer theCustomer = curentSession.get(Customer.class, theId);
//		
//		curentSession.delete(theCustomer);
	}

}
