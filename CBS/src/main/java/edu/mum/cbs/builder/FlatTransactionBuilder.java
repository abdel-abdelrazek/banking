package edu.mum.cbs.builder;

import edu.mum.model.temp.FlatTransaction;

/**
 * 
 * for building {@link FlatTransaction}
 * 
 * @author mgharib
 *
 */
public class FlatTransactionBuilder {

	private FlatTransaction flatTransaction;

	public FlatTransactionBuilder() {
		flatTransaction = new FlatTransaction();
	}

	public FlatTransactionBuilder withTransId(Integer transId) {
		flatTransaction.setTransactionId(transId);
		return this;
	}


	public FlatTransactionBuilder withTransAmount(double transactionAmount) {
		flatTransaction.setTransactionAmount(transactionAmount);
		return this;
	}

	public FlatTransactionBuilder withAccountNumber(int accountNumber) {
		flatTransaction.setAccountNumber(accountNumber);
		return this;
	}
	
	public FlatTransactionBuilder withTransactionType(String transactionType) {
		flatTransaction.setTransactionType(transactionType);
		return this;
	}

	public FlatTransactionBuilder withCustEmail(String custEmail) {
		flatTransaction.setCustEmail(custEmail);
		return this;
	}

	public FlatTransactionBuilder withCustId(long customerId) {
		flatTransaction.setCustomerId(customerId);
		return this;
	}

	public FlatTransaction build() {
		return flatTransaction;
	}

}
