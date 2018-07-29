package edu.mum.cbs.service.impl;

import java.util.Date;

import edu.mum.cbs.domain.Account;
import edu.mum.cbs.domain.Transaction;

public class TransationFactory {

	public static Transaction createTransation(Account account, String transationType, Double amount) {

		// create transaction
		Transaction transaction = new Transaction();

		if (transationType == "dr") {
			transaction.setAnnotation("debit");

		} else if (transationType == "cr") {
			transaction.setAnnotation("credit");
		}

		transaction.setTransactionType(transationType);
		transaction.setTransactionAmount(amount);
		transaction.setReconciled(false);
		transaction.setAccount(account);		
		transaction.setTransactionDate(new Date());

		return transaction;

	}



}
