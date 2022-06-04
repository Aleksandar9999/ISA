package com.isa.FishingBooker.dto;

public class ReservationNumDTO {
    private Integer id;
    private int reservationNumWeek;
    private int reservationNumMonth;
    private int reservationNumYear;

    public ReservationNumDTO(){}

   

    public ReservationNumDTO(Integer id, int reservationNumWeek, int reservationNumMonth, int reservationNumYear) {
		this.id = id;
		this.reservationNumWeek = reservationNumWeek;
		this.reservationNumMonth = reservationNumMonth;
		this.reservationNumYear = reservationNumYear;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public int getReservationNumWeek() {
        return reservationNumWeek;
    }

    public void setReservationNumWeek(int reservationNumWeek) {
        this.reservationNumWeek = reservationNumWeek;
    }

    public int getReservationNumMonth() {
        return reservationNumMonth;
    }

    public void setReservationNumMonth(int reservationNumMonth) {
        this.reservationNumMonth = reservationNumMonth;
    }

    public int getReservationNumYear() {
        return reservationNumYear;
    }

    public void setReservationNumYear(int reservationNumYear) {
        this.reservationNumYear = reservationNumYear;
    }
}