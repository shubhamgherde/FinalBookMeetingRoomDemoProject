package com.SecurityDemo.demo.service;

import java.util.List;

import com.SecurityDemo.demo.model.Room;

public interface RoomService {

	public void saveRoom(Room room);

	public boolean isUserAlreadyPresent(Room room);

	public List<Room> listAll();

	public Room get(int id);

	public Room SaveEditRoom(Room room);

	public List<Room> selectAvailable();

	public void updateStatusPending(int id, String status);

	public List<Room> availableRoomByDate(String date1, String date2, String Status);
}
