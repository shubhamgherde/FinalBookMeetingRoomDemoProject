package com.SecurityDemo.demo.service;

import java.util.List;

import com.SecurityDemo.demo.model.User;

public interface UserService {

	public void saveUser(User user);

	public boolean isUserAlreadyPresent(User user);

	public List<User> listAll();

	public User get(int id);

	public User SaveEditUser(User user);

	public User findByEmail(String email);

	public void updateemail(String email, String nemail);

	public User findByM(String name);

}
