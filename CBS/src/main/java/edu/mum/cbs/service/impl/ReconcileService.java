package edu.mum.cbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cbs.dao.ReconcileDao;
import edu.mum.cbs.domain.Reconcile;
import edu.mum.cbs.servie.IReconcileSrevice;

@Transactional 
@Service
public class ReconcileService implements IReconcileSrevice {

	@Autowired
	ReconcileDao reconcileDao;

	@Override
	public void saveReconcile(Reconcile obj) {
		reconcileDao.save(obj);

	}

}
