package com.SecurityDemo.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room_booking_detail")
public class RoomBookingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int booking_id;

	@Column(name = "id")
	private int id;

	private String room_name;

	@Column(name = "user_mail")
	private String user_mail;

	@Column(name = "bookingDateFrom")
	private String bookingDateFrom;

	@Column(name = "bookingDateTo")
	private String bookingDateTo;
	private String status;

	public String getStatus() {
		return status;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getBookingDateFrom() {
		return bookingDateFrom;
	}

	public void setBookingDateFrom(String bookingDateFrom) {
		this.bookingDateFrom = bookingDateFrom;
	}

	public String getBookingDateTo() {
		return bookingDateTo;
	}

	public void setBookingDateTo(String bookingDateTo) {
		this.bookingDateTo = bookingDateTo;
	}

	@Override
	public String toString() {
		return "RoomBookingDetails [booking_id=" + booking_id + ", id=" + id + ", room_name=" + room_name
				+ ", user_mail=" + user_mail + ", bookingDateFrom=" + bookingDateFrom + ", bookingDateTo="
				+ bookingDateTo + ", status=" + status + "]";
	}

}