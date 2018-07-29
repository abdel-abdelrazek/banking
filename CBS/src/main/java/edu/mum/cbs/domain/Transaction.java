package edu.mum.cbs.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  
	private Integer id;
	
	@Column(name = "transaction_date_time", nullable = false)
	private Date transactionDate;
	
	@Column(name = "transaction_type", nullable = false)
	private String transactionType;
	
	@Column(name = "annotation", nullable = true)
	private String annotation;
	
	@Column(name = "transaction_amount")
	private double transactionAmount ;
	
	@Column(name = "reconciled")
	private boolean reconciled;
	
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
	private Account account; 
	
	
	
	
	
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}



	
	public String getTransactionType() {
		return transactionType;
	}



	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}



	
	public String getAnnotation() {
		return annotation;
	}




	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}





	


	


	public boolean isReconciled() {
		return reconciled;
	}

	public void setReconciled(boolean reconciled) {
		this.reconciled = reconciled;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
