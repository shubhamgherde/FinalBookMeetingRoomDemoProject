package com.SecurityDemo.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "room_detail")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private int id;

	@NotNull(message = "Name is Compulsury")
	@Column(name = "room_name")
	private String room_name;

	@NotNull(message = "Location is Compulsury")
	@Column(name = "location")
	private String location;

	private String status;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "room_detail_facility", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "facility_id"))
	private Set<Facility> facility;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Facility> getFacility() {
		return facility;
	}

	public void setFacility(Set<Facility> facility) {
		this.facility = facility;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", room_name=" + room_name + ", location=" + location + ", facility=" + facility
				+ "]";
	}

}
