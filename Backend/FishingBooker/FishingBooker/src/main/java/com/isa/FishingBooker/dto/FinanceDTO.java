package com.isa.FishingBooker.dto;

public class FinanceDTO {
    private int id;
    private double earning;

    public FinanceDTO(){}


    public FinanceDTO(int id, double earning) {
		super();
		this.id = id;
		this.earning = earning;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getEarning() {
        return earning;
    }

    public void setEarning(double earning) {
        this.earning = earning;
    }
}