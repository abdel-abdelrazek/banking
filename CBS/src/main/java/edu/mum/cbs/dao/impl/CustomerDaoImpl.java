package edu.mum.cbs.dao.impl;


import org.springframework.stereotype.Repository;

import edu.mum.cbs.dao.CustomerDao;
import edu.mum.cbs.domain.Customer;


	@Repository
	public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao
	{
		
		public CustomerDaoImpl() {
			super.setDaoType(Customer.class );
			}


  
	}

