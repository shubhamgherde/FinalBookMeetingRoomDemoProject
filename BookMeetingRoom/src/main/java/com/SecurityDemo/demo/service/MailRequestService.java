package com.SecurityDemo.demo.service;

import java.util.List;

import com.SecurityDemo.demo.model.mail_request;

public interface MailRequestService {

	public void updateStatus(int id, String status2);

	public void savemailreq(mail_request req);

	public void updateStatusmail(int id, String status);

	public mail_request get(int id);

	public List<mail_request> listpendingPM(String status);

	public List<mail_request> listpendingUser(String department, String status);

	public List<mail_request> listpendingTL(String status);

}
