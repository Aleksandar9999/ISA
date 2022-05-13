package com.isa.FishingBooker.dto;

import com.isa.FishingBooker.model.Status;

public class RevisionDTO {
	private int id;
	private double rate;
	private String comment;
	private int relatedId;
	private int creatorId;
	private String creatorInfo;
	private String ownerEmail;
	private Status status;
	private String className;

	public RevisionDTO() {
	}

	public String getCreatorInfo() {
		return creatorInfo;
	}

	public void setCreatorInfo(String creatorInfo) {
		this.creatorInfo = creatorInfo;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getClassName() {
		String[] classSplit = this.className.split(" ");
		try {
			return classSplit[1];
		} catch (Exception ex) {
			return classSplit[0];
		}
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

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
