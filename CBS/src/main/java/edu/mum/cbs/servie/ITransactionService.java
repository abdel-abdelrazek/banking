package edu.mum.cbs.servie;

import java.util.List;

import edu.mum.cbs.domain.Transaction;

public interface ITransactionService {

	public List<Transaction> getTodayTransactions();

	void save(Transaction trnasaction);
}
