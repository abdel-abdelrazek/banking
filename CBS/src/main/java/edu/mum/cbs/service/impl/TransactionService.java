package edu.mum.cbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cbs.dao.TransactionDao;
import edu.mum.cbs.domain.Transaction;
import edu.mum.cbs.servie.ITransactionService;

@Transactional 
@Service
public class TransactionService implements ITransactionService {

	@Autowired
	TransactionDao txnDao;

	@Override
	public List<Transaction> getTodayTransactions() {

		//TODO: should return only todays transactions
		return txnDao.findUnreconciled();
	}
	
	@Override
	public void save (Transaction transaction) {
		
		txnDao.save(transaction);
	}

}
