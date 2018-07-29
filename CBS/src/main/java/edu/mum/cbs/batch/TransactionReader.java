package edu.mum.cbs.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;

import edu.mum.cbs.domain.Transaction;
import edu.mum.cbs.servie.ITransactionService;

// step 1
public class TransactionReader implements ItemReader<List<Transaction>> {
	ITransactionService txnService;

	/**
	 * read transactions from database and pass them to the processor
	 */
	@Override
	public List<Transaction> read() {

		System.out.println("*Reading Transactions from db");
		List<Transaction> txns = txnService.getTodayTransactions();
		System.out.println("*** number of transactions: " + txns.size());

		if (txns.size() == 0)
			return null;

		return txns;

	}

	public void setTxnService(ITransactionService txnService) {
		this.txnService = txnService;
	}

}
