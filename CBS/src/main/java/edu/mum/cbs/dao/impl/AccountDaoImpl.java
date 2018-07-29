package edu.mum.cbs.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.cbs.dao.AccountDao;
import edu.mum.cbs.domain.Account;

@Repository
public class AccountDaoImpl extends GenericDaoImpl<Account> implements AccountDao {

	public AccountDaoImpl() {
		super.setDaoType(Account.class);
	}

	@Override
	public Account findByAccountNumber(long accountNumber) {

		Query query = entityManager.createQuery("select a from Account a  where a.accountNumber =:accountNumber");
		return (Account) query.setParameter("accountNumber", accountNumber).getSingleResult();
	}

}
