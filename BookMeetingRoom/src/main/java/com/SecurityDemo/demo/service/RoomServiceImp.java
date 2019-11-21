package com.SecurityDemo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecurityDemo.demo.model.Room;
import com.SecurityDemo.demo.repository.FacilityRepository;
import com.SecurityDemo.demo.repository.RoomRepository;

@Service
public class RoomServiceImp implements RoomService {
	@Autowired
	RoomRepository roomRepo;

	@Autowired
	FacilityRepository facilityRepo;

	@Override
	public void saveRoom(Room room) {
		room.setStatus("AVAILABLE");
		roomRepo.save(room);
	}

	@Override
	public boolean isUserAlreadyPresent(Room room) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Room> listAll() {

		return roomRepo.findAll();
	}

	@Override
	public Room get(int id) {

		return roomRepo.findById(id);
	}

	@Override
	public Room SaveEditRoom(Room room) {

		return roomRepo.save(room);
	}

	@Override
	public List<Room> selectAvailable() {

		return roomRepo.selectAvailable();
	}

	@Override
	public void updateStatusPending(int id, String status) {

		roomRepo.updateStatus(id, status);

	}

	@Override
	public List<Room> availableRoomByDate(String date1, String date2, String status) {
		System.out.println("in available");
		System.out.println(status);
		
		return roomRepo.availableRoomByDate(date1,date2, status);
	}

}
