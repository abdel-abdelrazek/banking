package edu.mum.cbs.batch;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import edu.mum.cbs.domain.Transaction;
import edu.mum.cbs.servie.ITransactionService;
import edu.mum.cbs.domain.Reconcile;

public class ReconcileProcessor implements ItemProcessor<List<Transaction>, Reconcile> {
	ITransactionService txnService;

	/**
	 * calculate the sum of debit and credit for the passed list of transactions
	 */
	@Override
	public Reconcile process(List<Transaction> txns) throws Exception {

		System.out.println("*** processing transactions with size: " + txns.size());
		double depitSum = 0;
		double creditSum = 0;

		for (Transaction transaction : txns) {

			if (transaction.getTransactionType().equals("dr")) {
				depitSum += transaction.getTransactionAmount();
			} else if (transaction.getTransactionType().equals("cr")) {
				creditSum += transaction.getTransactionAmount();
			}

			// set the reconciled flag for processed transactions
			transaction.setReconciled(true);
			txnService.save(transaction);
		}

		System.out.println("***depit sum " + depitSum + ", credit sum: " + creditSum);

		// pass the processing result to the writer
		Reconcile rec = new Reconcile();
		rec.setCreditSum(creditSum);
		rec.setDebitSum(depitSum);

		return rec;
	}

	public void setTxnService(ITransactionService txnService) {
		this.txnService = txnService;
	}

}
