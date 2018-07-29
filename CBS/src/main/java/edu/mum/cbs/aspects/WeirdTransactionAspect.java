package edu.mum.cbs.aspects;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import edu.mum.cbs.builder.FlatTransactionBuilder;
import edu.mum.cbs.domain.Account;
import edu.mum.cbs.domain.Customer;
import edu.mum.cbs.domain.Transaction;
import edu.mum.model.temp.FlatTransaction;


/**
 * The main purpose for this class to notify the auditing for any weird
 * transactions in deposit or withdrawal. weird transaction is the transaction
 * that exceed specific amount and this is a configuration
 * 
 * @author mgharib
 *
 */
@Aspect
@Component
@Order(-1)
public class WeirdTransactionAspect {

	Logger log = LoggerFactory.getLogger(WeirdTransactionAspect.class);

	@Resource(name = "weirdTransactionTemplate")
	private RabbitTemplate template;

	@Value("${weird.transaction.threshold}")
	private String weirdTransactionThreshold;

	@Around("edu.mum.cbs.aspects.OpzaAopExpressions.depositWithdrawPointcut()  ")
	public void catchWeirdTransaction(ProceedingJoinPoint transaction) throws Exception {
		System.out.println("************** start catchWeirdTransaction ***************");
		try {
			Transaction proceed = (Transaction) transaction.proceed();
			doAdvice(proceed);
		} catch (Throwable e) {
			System.out.println("************** error while sending the transaction ");
		}

		System.out.println("************** end catchWeirdTransaction ***************");
	}



	/**
	 * the real advice implementation
	 * 
	 * @param transaction
	 *            {@link Transaction}
	 * @author mgharib
	 */
	private void doAdvice(Transaction transaction) {

		double threshold = Integer.parseInt(weirdTransactionThreshold);

		if (transaction.getTransactionAmount() < threshold)
			return;

		Account account = transaction.getAccount();
		Customer customer = account.getCustomer();
		FlatTransaction flatTransaction = new FlatTransactionBuilder()
				.withTransId(transaction.getId())
				.withCustId(customer.getId())
				.withTransactionType(transaction.getTransactionType())
				.withAccountNumber(account.getAccountNumber())
				.withCustEmail(customer.getEmail())
				.withTransAmount(transaction.getTransactionAmount())
				.build();

		template.convertAndSend(flatTransaction);

	}

}
