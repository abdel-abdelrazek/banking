package edu.mum.cbs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reconcile implements Serializable{

	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "debit_sum")
	private double debitSum;
	
	@Column(name = "credit_sum")
	private double creditSum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public double getDebitSum() {
		return debitSum;
	}

	public void setDebitSum(double debitSum) {
		this.debitSum = debitSum;
	}

	public double getCreditSum() {
		return creditSum;
	}

	public void setCreditSum(double creditSum) {
		this.creditSum = creditSum;
	}
	
	
	
	
}
