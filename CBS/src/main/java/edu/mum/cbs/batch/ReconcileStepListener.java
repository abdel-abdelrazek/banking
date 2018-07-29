package edu.mum.cbs.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class ReconcileStepListener implements JobExecutionListener {

	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		System.out.println("######## job started");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("######## job finished");
		// TODO Auto-generated method stub
		// jobExecution.get.shutdown();
	}
}