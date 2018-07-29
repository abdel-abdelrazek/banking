package edu.mum.cbs.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.cbs.dao.TransactionDao;
import edu.mum.cbs.domain.Transaction;

@Repository
public class TransactionDaoImpl extends GenericDaoImpl<Transaction> implements TransactionDao {
	@Override
	public List<Transaction> findUnreconciled() {

		Query query = entityManager.createQuery("select t from Transaction t  where t.reconciled =:reconciled");
		return query.setParameter("reconciled", false).getResultList();
	}

	public TransactionDaoImpl() {
		super.setDaoType(Transaction.class);
	}

}
