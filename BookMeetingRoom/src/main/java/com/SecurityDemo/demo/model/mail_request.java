package com.SecurityDemo.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class mail_request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mailrequest_id;

	@Column(name = "user_mail")
	private String user_mail;

	@Column(name = "new_user_mail")
	private String new_user_mail;

	private String status = "pending";

	private String role;

	private String department;

	public int getMailrequest_id() {
		return mailrequest_id;
	}

	public void setMailrequest_id(int mailrequest_id) {
		this.mailrequest_id = mailrequest_id;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getNew_user_mail() {
		return new_user_mail;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setNew_user_mail(String new_user_mail) {
		this.new_user_mail = new_user_mail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "mail_request [mailrequest_id=" + mailrequest_id + ", user_mail=" + user_mail + ", new_user_mail="
				+ new_user_mail + ", status=" + status + "]";
	}

}