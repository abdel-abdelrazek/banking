package edu.mum.cbs.servie;

import edu.mum.cbs.domain.Account;
import edu.mum.cbs.domain.Transaction;

public interface IAccountService {


	public void saveAccount(Account account);



	Transaction depositMoney(double amount, long toAccountId);

	Transaction withdrawMoney(double amount, long fromAccountId);

	Account findAccount(long accountId);

	Account findAccountByAccountNumber(long accountNumber);
}
