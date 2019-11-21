package com.SecurityDemo.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.SecurityDemo.demo.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}