package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
@Entity
public class TutorRevision extends Revision {
	@OneToOne
	private Tutor tutor;
	public TutorRevision() {}
	@Override
	public void setRelatedId(int id) {
		this.tutor=new Tutor(id);
	}
	@Override
	public int getRelatedId() {
		return this.tutor.getId();
	}
}
