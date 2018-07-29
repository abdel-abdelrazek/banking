package edu.mum.cbs.batch;

import java.util.Locale;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.cbs.domain.Reconcile;
import edu.mum.cbs.service.impl.EmailService;

// job step 2 
public class SendEmail implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		// get passed results from step 1
		StepContext stepContext = chunkContext.getStepContext();
		StepExecution stepExecution = stepContext.getStepExecution();
		JobExecution jobExecution = stepExecution.getJobExecution();
		ExecutionContext jobContext = jobExecution.getExecutionContext();

		Reconcile result = (Reconcile) jobContext.get("result");

		System.out.println("$$$$$ data passed received: " + result);
		
		// send step 1 result by email
		if (result != null) {
			System.out.println("Sending Mail...");
			ApplicationContext context = new ClassPathXmlApplicationContext("/context/*.xml");
			EmailService emailService = (EmailService) context.getBean("emailService");
			emailService.sendOrderReceivedMail("Khalid Elawad", "khalid.ali@mum.edu", result, new Locale("en"));
		}
		
		return RepeatStatus.FINISHED;
	}

}
