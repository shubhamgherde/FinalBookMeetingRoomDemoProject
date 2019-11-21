package com.SecurityDemo.demo.service;

import java.util.List;

import com.SecurityDemo.demo.model.RoomBookingDetails;

public interface RoomBookingService {

	public void saveBookingRoom(RoomBookingDetails bookingDetail);

	public List<RoomBookingDetails> listAll();

	public List<RoomBookingDetails> pendingStatus(String user);

	public List<RoomBookingDetails> allStatus(String user);

	public List<RoomBookingDetails> confirmStatus(String user);

	public List<RoomBookingDetails> cancelStatus(String user);

	public void updateStatus(int booking_id, String status2);

	public List<RoomBookingDetails> allConfirmRequest();

	public List<RoomBookingDetails> allPendingRequest();

	public List<RoomBookingDetails> allCancelRequest();

	public void updateemail(String email, String nemail);

}
