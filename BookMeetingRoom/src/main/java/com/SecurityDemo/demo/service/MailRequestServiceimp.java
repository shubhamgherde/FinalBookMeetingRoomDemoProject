package com.SecurityDemo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecurityDemo.demo.model.mail_request;
import com.SecurityDemo.demo.repository.MailRequestRepository;

@Service
public class MailRequestServiceimp implements MailRequestService {

	@Autowired
	MailRequestRepository repo;

	@Override
	public void savemailreq(mail_request req) {
		repo.save(req);
	}

	@Override
	public void updateStatus(int id, String status2) {

	}

	@Override
	public void updateStatusmail(int id, String status) {
		repo.updatestatus(id, status);

	}

	@Override
	public mail_request get(int id) {
		return repo.findById(id).get();
	}

	@Override
	public List<mail_request> listpendingPM(String status) {

		return repo.selectPendingmailPM(status);
	}

	@Override
	public List<mail_request> listpendingUser(String department, String status) {

		return repo.selectPendingmailUser(department, status);
	}

	@Override
	public List<mail_request> listpendingTL(String status) {

		return repo.selectPendingmailTL(status);
	}

}
