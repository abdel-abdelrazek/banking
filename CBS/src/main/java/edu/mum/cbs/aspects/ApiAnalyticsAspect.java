package edu.mum.cbs.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(-3)
public class ApiAnalyticsAspect {

		
		@Around("edu.mum.cbs.aspects.OpzaAopExpressions.forAppFlow()")	
		public Object aroundGetFortune(
				ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
			
			// print out method we are advising on
			String method = theProceedingJoinPoint.getSignature().toShortString();
			
			System.out.println("\n=====>>> Executing @Around on method: " + method);
			// get begin timestamp
			long begin = System.currentTimeMillis();
			
			// now, let's execute the method
			Object result = null;
			
			try {
				result = theProceedingJoinPoint.proceed();
			} catch (Exception e) {
				// log the exception
				System.out.println(e.getMessage());
				
				// rethrow exception
				throw e;
			}
			
			// get end timestamp
			long end = System.currentTimeMillis();
			
			// compute duration and display it
			long duration = end - begin;
			
			System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");
			return result;
		}
		
		

		
	
	

}
