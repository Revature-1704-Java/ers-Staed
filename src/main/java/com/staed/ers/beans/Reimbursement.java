package com.staed.ers.beans;

import java.sql.Date;

public class Reimbursement {
	private int id;
	private int employeeId;
	private int handlerId;
	private Date submissionDate;
	private Date requestDate;
	private String description;
	private int amount;
	boolean approved;
	
	public Reimbursement(int id, int employeeId, int handlerId, Date submissionDate, Date requestDate,
			String description, int amount, boolean approved) {
		this.id = id;
		this.employeeId = employeeId;
		this.handlerId = handlerId;
		this.submissionDate = submissionDate;
		this.requestDate = requestDate;
		this.description = description;
		this.amount = amount;
		this.approved = approved;
	}
	
	@Override
	public String toString() {
		return "Reimbursement: {id:" + id + ", employeeId:" + employeeId + ", handlerId:" + handlerId
				+ ", submissionDate:" + submissionDate + ", requestDate:" + requestDate + ", description:" + description
				+ ", amount:" + amount + ", approved:" + approved + "}";
	}

	public int getId() {
		return id;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public int getHandlerId() {
		return handlerId;
	}
	
	public void setHandlerId(int handlerId) {
		this.handlerId = handlerId;
	}
	
	public Date getSubmissionDate() {
		return submissionDate;
	}
	
	public Date getRequestDate() {
		return requestDate;
	}
	
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
}
