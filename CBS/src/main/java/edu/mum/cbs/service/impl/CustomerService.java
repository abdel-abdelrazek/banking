package edu.mum.cbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cbs.dao.CustomerDao;
import edu.mum.cbs.domain.Customer;
import edu.mum.cbs.servie.ICustomerService;



@Transactional 
@Service
public class CustomerService implements ICustomerService{

	@Autowired
	CustomerDao customerRepository;
//ICustomerRepository customerRepository;

	@Override
	public Iterable<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void addCustomer(Customer customer) {		
		customerRepository.save(customer);
	}
	
	@Override
	public Customer findCustomer(int customerId) {
		return customerRepository.findOne(customerId);
	}
}
