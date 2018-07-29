package edu.mum.cbs.service.impl;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cbs.builder.FlatTransactionBuilder;
import edu.mum.cbs.dao.AccountDao;
import edu.mum.cbs.dao.CustomerDao;
import edu.mum.cbs.dao.TransactionDao;
import edu.mum.cbs.domain.Account;
import edu.mum.cbs.domain.Customer;
import edu.mum.cbs.domain.Transaction;
import edu.mum.cbs.servie.IAccountService;
import edu.mum.model.temp.FlatTransaction;

@Transactional
@Service
public class AccountService implements IAccountService {

	@Autowired
	AccountDao accountRep;

	@Autowired
	CustomerDao customerRepository;

	@Autowired
	TransactionDao txnRep;


	/*
	 * @Autowired TransationRepository txtRep;
	 */

	/*
	 * @Autowired AdminRepository AdminRep;
	 */

	@Override
	public Transaction depositMoney(double amount, long toAccountId) {

		Account toAccount = accountRep.findOne(toAccountId);
		double initialBalance = toAccount.getBalance();
		toAccount.setBalance(initialBalance + amount);
		accountRep.save(toAccount);

		Transaction depositTxn = TransationFactory.createTransation(toAccount, "cr", amount);
		txnRep.save(depositTxn);


		return depositTxn;
	}

	@Override
	public Transaction withdrawMoney(double amount, long fromAccountId) {

		Account fromAccount = accountRep.findOne(fromAccountId);
		double initialBalance = fromAccount.getBalance();
		fromAccount.setBalance(initialBalance - amount);
		accountRep.save(fromAccount);

		Transaction withdrawTxn = TransationFactory.createTransation(fromAccount, "dr", amount);
		txnRep.save(withdrawTxn);

		return withdrawTxn;

		/*
		 * if (fromAccount.getBalance() >= 0) { accountRep.saveAndFlush(fromAccount);
		 * 
		 * Account bankAccount = accountRep.findOne(Admin.BANK_ACCOUNT_ID);
		 * 
		 * Transaction debitT = TransationFactory.createTransation(fromAccount,
		 * bankAccount, Admin.TRANSATION_TYPE_DEBIT, Admin.BUSINESS_TYPE_WITHDRAW,
		 * amount);
		 * 
		 * Transaction debitT2 = TransationFactory.createTransation(fromAccount,
		 * bankAccount, Admin.TRANSATION_TYPE_DEBIT, Admin.BUSINESS_TYPE_WITHDRAW,
		 * initialBalance - fromAccount.getBalance() - amount);
		 * 
		 * // t.test(debitT); txtRep.saveAndFlush(debitT); txtRep.saveAndFlush(debitT2);
		 * 
		 * return true; } else { return false; }
		 */
	}

	/*
	 * public void transferMoney(double amount, int fromAccountId, int
	 * toAccountNumber) {
	 * 
	 * Account beneficiaryAccount = accountRep.findAccountByNum(toAccountNumber);
	 * Account bankAccount = accountRep.findOne(Admin.BANK_ACCOUNT_ID); Account
	 * fromAccount = accountRep.findOne(fromAccountId);
	 * 
	 * List<Transaction> transactions =
	 * TransationFactory.createTransferTransations(fromAccount, beneficiaryAccount,
	 * bankAccount, amount);
	 * 
	 * txtRep.save(transactions);
	 * 
	 * fromAccount.doTransfer(beneficiaryAccount, amount);
	 * 
	 * accountRep.save(fromAccount); accountRep.save(beneficiaryAccount); }
	 */
	@Override
	public Account findAccount(long accountId) {
		return accountRep.findOne(accountId);
	}

	@Override
	public void saveAccount(Account account) {
		account.setAccountNumber(generateAccountNumber());
		accountRep.save(account);
	}

	@Override
	public Account findAccountByAccountNumber(long accountNumber) {
		return accountRep.findByAccountNumber(accountNumber);
	}

	private int generateAccountNumber() {
		return getRandomInt(10000, 99999);
	}

	private int getRandomInt(int lower, int upper) {
		if (lower > upper)
			return 0;
		if (lower == upper)
			return lower;
		int difference = upper - lower;
		int start = getRandomInt();

		// nonneg int in the range 0..difference - 1
		start = Math.abs(start) % (difference + 1);

		start += lower;
		return start;
	}

	private int getRandomInt() {
		Random random = new Random();
		return random.nextInt();
	}
}
