package edu.mum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import edu.mum.model.temp.FlatTransaction;
import edu.mum.rest.RestHttpHeader;
import edu.mum.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	RestHttpHeader remoteApi;

	@Override
	public void sendTransaction(FlatTransaction transaction) {
		try {

			RestTemplate restTemplate = remoteApi.getRestTemplate();
			HttpEntity<FlatTransaction> httpEntity = new HttpEntity<FlatTransaction>(transaction,
					remoteApi.getHttpHeaders());
			restTemplate.postForObject("http://localhost:8080/bus/httpWeirdTransaction", httpEntity,
					FlatTransaction.class);
		} catch (RestClientException exce) {
			System.out.println("ERROR: " + exce.getMessage());
		}
		
	}

  
	
 
}
