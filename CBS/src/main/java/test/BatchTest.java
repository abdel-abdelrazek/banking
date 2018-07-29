package test;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJvmExitCodeMapper;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
@Component
public class BatchTest {

	static ApplicationContext context;

	public static void main(String[] args) {
		// System.out.println("hello");
		String[] springConfig = { "/context/*.xml" };

		// Creating the application context object
		context = new ClassPathXmlApplicationContext(springConfig);
		 startJob();
	
	}

	//@Scheduled(cron = "0 12 11 * * ?")
	private static void startJob() {

		// Creating the job launcher
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		// Creating the job
		Job job = (Job) context.getBean("reconcileJob");
		SimpleJvmExitCodeMapper mapper = new SimpleJvmExitCodeMapper();

		// Executing the JOB
		JobExecution execution = null;

		try {
			execution = jobLauncher.run(job, new JobParameters());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Close context
		((AbstractApplicationContext) context).close();

	}

}
