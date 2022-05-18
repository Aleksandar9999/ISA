package com.isa.FishingBooker.model.revision;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.isa.FishingBooker.model.Tutor;

@Entity
public class TutorRevision extends Revision {

	@OneToOne
	private Tutor tutor;

	public TutorRevision() {
	}

	@Override
	public String getOwnerEmail() {
		return tutor.getEmail();
	}

	@Override
	public void setRelatedId(int id) {
		this.tutor = new Tutor(id);
	}

	@Override
	public int getRelatedId() {
		return this.tutor.getId();
	}
}
