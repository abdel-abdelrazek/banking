package edu.mum.cbs.servie;

import edu.mum.cbs.domain.Customer;

public interface ICustomerService {

	public Iterable<Customer> getAllCustomers();

	public void addCustomer(Customer customer);

	public Customer findCustomer(int customerId);

}
