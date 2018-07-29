package edu.mum.cbs.dao.impl;


import org.springframework.stereotype.Repository;

import edu.mum.cbs.dao.ReconcileDao;
import edu.mum.cbs.domain.Reconcile;


	@Repository
	public class ReconcileDaoImpl extends GenericDaoImpl<Reconcile> implements ReconcileDao
	{
		
		public ReconcileDaoImpl() {
			super.setDaoType(Reconcile.class );
			}


  
	}

