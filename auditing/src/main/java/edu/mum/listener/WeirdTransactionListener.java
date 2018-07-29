package edu.mum.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.mum.model.temp.FlatTransaction;

public class WeirdTransactionListener implements MessageListener {
	private static final Logger logger = LoggerFactory.getLogger(WeirdTransactionListener.class);

	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;
		FlatTransaction transaction = null;
		try {
			transaction = (FlatTransaction) objectMessage.getObject();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(" *********************** Transaction received ( " + " Transaction ID: "
				+ transaction.getTransactionId() + ", Transaction Type: " + transaction.getTransactionType()
				+ ", Transaction Amount:" + transaction.getTransactionAmount() + ", Customer ID: "
				+ transaction.getCustomerId() + ", Customer Email:" + transaction.getCustEmail() + ")");

	}
}
