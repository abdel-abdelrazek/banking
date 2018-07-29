package edu.mum.cbs.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import edu.mum.cbs.domain.Transaction;

@Aspect
public class OpzaAopExpressions {

	// setup pointcut declarations
		@Pointcut("execution(* edu.mum.cbs.controller..*(..))")
		public void forControllerPackage() {}
		
		// do the same for service and dao
		@Pointcut("execution(* edu.mum.cbs.service.impl.*.*(..))")
		public void forServicePackage() {}
		
		@Pointcut("execution(* edu.mum.cbs.service.impl.AccountService.withdrawMoney(..)) || execution(* edu.mum.cbs.service.impl.AccountService.depositMoney(..))")
		public void depositWithdrawPointcut() {}
		
		
		@Pointcut("execution(* edu.mum.cbs.dao.impl.*.*(..))")
		public void forDaoPackage() {}
		
		@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
		public void forAppFlow() {}

}
