package com.SecurityDemo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecurityDemo.demo.model.RoomBookingDetails;
import com.SecurityDemo.demo.repository.RoomBookingDetailRepo;

@Service
public class RoomBookingServiceImp implements RoomBookingService {

	@Autowired
	RoomBookingDetailRepo roomBookingDetailRepo;

	@Override
	public void saveBookingRoom(RoomBookingDetails bookingDetail) {

		bookingDetail.setStatus("PENDING");
		roomBookingDetailRepo.save(bookingDetail);

	}

	@Override
	public List<RoomBookingDetails> listAll() {

		return roomBookingDetailRepo.findAll();
	}

	@Override
	public List<RoomBookingDetails> pendingStatus(String user) {

		return roomBookingDetailRepo.pendingStatus(user);
	}

	@Override
	public List<RoomBookingDetails> confirmStatus(String user) {

		return roomBookingDetailRepo.confirmStatus(user);
	}

	@Override
	public List<RoomBookingDetails> cancelStatus(String user) {

		return roomBookingDetailRepo.cancelStatus(user);
	}

	@Override
	public void updateStatus(int booking_id, String status2) {

		roomBookingDetailRepo.updateStatus(booking_id, status2);

	}

	@Override
	public List<RoomBookingDetails> allPendingRequest() {

		return roomBookingDetailRepo.allPendingRequest();
	}

	@Override
	public List<RoomBookingDetails> allConfirmRequest() {

		return roomBookingDetailRepo.allConfirmRequest();
	}

	@Override
	public List<RoomBookingDetails> allCancelRequest() {

		return roomBookingDetailRepo.allCancelRequest();
	}

	@Override
	public List<RoomBookingDetails> allStatus(String user) {
		//
		return roomBookingDetailRepo.allRequest(user);
	}

	@Override
	public void updateemail(String email, String nemail) {

		roomBookingDetailRepo.updatemail(email, nemail);

	}

}
