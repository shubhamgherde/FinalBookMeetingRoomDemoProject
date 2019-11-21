package com.SecurityDemo.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SecurityDemo.demo.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	public Room findById(int id);

	@Query(value = "select * from room_detail where status='AVAILABLE'", nativeQuery = true)
	public List<Room> selectAvailable();

	@Query(value = "select * from room_detail where status='PENDING'", nativeQuery = true)
	public List<Room> selectPending();

	@Query(value = "select * from room_detail where status='CONFIRM'", nativeQuery = true)
	public List<Room> selectConfirm();

	@Modifying
	@Transactional
	@Query(value = "UPDATE room_detail c SET c.status = :status WHERE c.room_id = :id", nativeQuery = true)
	int updateStatus(@Param("id") int id, @Param("status") String status);

	@Query(value = "SELECT * FROM room_detail WHERE room_id NOT IN(SELECT id FROM room_booking_detail WHERE (booking_date_from <= :date1 AND booking_date_to >= :date1 AND status= :status) OR (booking_date_from >= :date1 AND booking_date_from <= :date2 AND status= :status))", nativeQuery = true)

	public List<Room> availableRoomByDate(@Param("date1") String date1, @Param("date2") String date2,
			@Param("status") String status);

}
