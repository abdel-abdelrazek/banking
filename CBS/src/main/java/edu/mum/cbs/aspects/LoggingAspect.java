package edu.mum.cbs.aspects;

import java.util.Locale;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import edu.mum.cbs.service.impl.EmailService;

@Aspect
@Component
@Order(1)
public class LoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// add @Before advice
	@Before("edu.mum.cbs.aspects.OpzaAopExpressions.forAppFlow()")
	public void before(JoinPoint theJoinPoint) {

		// display method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>> in @Before: calling method: " + theMethod);

		// display the arguments to the method

		// get the arguments
		Object[] args = theJoinPoint.getArgs();

		// loop thru and display args
		for (Object tempArg : args) {
			myLogger.info("=====>> argument: " + tempArg);
		}

	}

	// add @AfterReturning advice
	@AfterReturning(pointcut = "edu.mum.cbs.aspects.OpzaAopExpressions.forAppFlow()", returning = "theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

		// display method we are returning from
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>> in @AfterReturning: from method: " + theMethod);

		// display data returned
		myLogger.info("=====>> result: " + theResult);

	}

	@AfterThrowing(pointcut = "edu.mum.cbs.aspects.OpzaAopExpressions.forAppFlow()", throwing = "theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

		// log the exception
		System.out.println("\n=====>>> The exception is: " + theExc);

		sendEmail("Executing @AfterThrowing on method: " + method+ "\n The exception is: " + theExc);
	}

	private void sendEmail(String pDetails) {

		ApplicationContext context = new ClassPathXmlApplicationContext("/context/*.xml");
		EmailService emailService = (EmailService) context.getBean("emailService");

		try {
			emailService.sendExceptionMail("Abdelrahman Abdelrazek ", "aabdelrazek@mum.edu", pDetails, new Locale("en"));

		} catch (Exception e) {
			System.err.println("Failed To send Email: " + e.getMessage());
		}

	}

}
