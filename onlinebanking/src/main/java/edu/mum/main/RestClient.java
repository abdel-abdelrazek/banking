package edu.mum.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.mum.builder.FlatTransactionBuilder;
import edu.mum.model.temp.FlatTransaction;
import edu.mum.rest.RestHttpHeader;
import edu.mum.service.TransactionService;

@Component
public class RestClient {
	@Autowired
	RestHttpHeader remoteApi;

	@Autowired
	TransactionService orderService;

	public static void main(String[] args) {
		final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"context/applicationContext.xml");

		applicationContext.getBean(RestClient.class).mainInternal(applicationContext);
	}

	private void mainInternal(ApplicationContext applicationContext) {

		FlatTransaction flatTransaction = new FlatTransactionBuilder().withTransId(555).withCustId(44)
				.withTransactionType("cr").withAccountNumber(123456).withCustEmail("mgharib@mum.edu")
				.withTransAmount(9000).build();

		orderService.sendTransaction(flatTransaction);

	}
}
