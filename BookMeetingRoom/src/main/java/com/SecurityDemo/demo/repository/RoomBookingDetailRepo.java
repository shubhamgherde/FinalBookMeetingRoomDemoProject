package com.SecurityDemo.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SecurityDemo.demo.model.RoomBookingDetails;

public interface RoomBookingDetailRepo extends JpaRepository<RoomBookingDetails, Integer> {

	@Query(value = "select * from room_booking_detail where user_mail= :user && status='PENDING'", nativeQuery = true)
	public List<RoomBookingDetails> pendingStatus(@Param("user") String user);

	@Query(value = "select * from room_booking_detail where user_mail= :user && status='CONFIRM'", nativeQuery = true)
	public List<RoomBookingDetails> confirmStatus(@Param("user") String user);

	@Query(value = "select * from room_booking_detail where user_mail= :user && status='CANCEL'", nativeQuery = true)
	public List<RoomBookingDetails> cancelStatus(@Param("user") String user);

	@Modifying
	@Transactional
	@Query(value = "UPDATE room_booking_detail c SET c.status = :status2 WHERE c.booking_id = :booking_id", nativeQuery = true)
	int updateStatus(@Param("booking_id") int booking_id, @Param("status2") String status2);
	
	@Query(value = "select * from room_booking_detail where status='Confirm'", nativeQuery = true)
	public List<RoomBookingDetails> allConfirmRequest();
	
	@Query(value = "select * from room_booking_detail where status='PENDING'", nativeQuery = true)
	public List<RoomBookingDetails> allPendingRequest();
	
	@Query(value = "select * from room_booking_detail where status='CANCEL'", nativeQuery = true)
	public List<RoomBookingDetails> allCancelRequest();
	
	
	@Query(value = "select * from room_booking_detail where user_mail= :user", nativeQuery = true)
	public List<RoomBookingDetails> allRequest(@Param("user") String user);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE room_booking_detail c SET c.user_mail =:nemail WHERE c.user_mail = :email",nativeQuery = true)
	void updatemail(@Param("email") String email,@Param("nemail") String nemail);
	

}
