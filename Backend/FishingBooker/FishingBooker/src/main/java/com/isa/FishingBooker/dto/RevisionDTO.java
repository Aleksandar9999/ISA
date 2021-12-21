package com.isa.FishingBooker.dto;

import com.isa.FishingBooker.model.Status;

public class RevisionDTO {
	private int id;
	private double rate;
	private String comment;
	private int relatedId;
	private Status status;
	private String className;
	public RevisionDTO() {
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getClassName() {
		String[] classSplit=this.className.split("\\.");
		return classSplit[classSplit.length-1];
	}

	public void setClassName(String relatedTo) {
		this.className = relatedTo;
	}

	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getRelatedId() {
		return relatedId;
	}
	public void setRelatedId(int relatedId) {
		this.relatedId = relatedId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
