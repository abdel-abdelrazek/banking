package edu.mum.cbs.batch;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;

import edu.mum.cbs.domain.Reconcile;
import edu.mum.cbs.servie.IReconcileSrevice;;

public class ReconcileWriter implements ItemWriter<Reconcile> {

	private StepExecution stepExecution;
	IReconcileSrevice reconcileService;

	/**
	 * save the job result (depit sum and credit sum) to the database 
	 */
	@Override
	public void write(List<? extends Reconcile> items) throws Exception {

		
		System.out.println("*** saving results with size: " + items.size());
		
		for (Reconcile rec : items) {
			System.out.println(rec);
			reconcileService.saveReconcile(rec);

			// pass data to step 2
			if (rec != null) {
				System.out.println("pass rec to step 2");
				ExecutionContext stepContext = this.stepExecution.getJobExecution().getExecutionContext();
				stepContext.put("result", rec);
			}
		}

		System.out.println("******* results saved");
		
	}

	@BeforeStep
	public void saveStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}

	public void setReconcileService(IReconcileSrevice reconcileService) {
		this.reconcileService = reconcileService;
	}

}
