package edu.mum.cbs.dao;


import edu.mum.cbs.domain.Account;


	public interface AccountDao extends GenericDao<Account>  
	{

		Account findByAccountNumber(long accountNumber);
  
	}

