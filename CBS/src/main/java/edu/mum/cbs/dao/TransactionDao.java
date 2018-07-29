package edu.mum.cbs.dao;


import java.util.List;

import edu.mum.cbs.domain.Transaction;


	public interface TransactionDao extends GenericDao<Transaction>  
	{
		public List<Transaction> findUnreconciled() ;
	}

