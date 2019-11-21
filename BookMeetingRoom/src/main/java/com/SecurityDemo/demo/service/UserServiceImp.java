package com.SecurityDemo.demo.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SecurityDemo.demo.model.Role;
import com.SecurityDemo.demo.model.User;
import com.SecurityDemo.demo.repository.RoleRepository;
import com.SecurityDemo.demo.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("NON-VERIFIED");

		Role userRole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);

	}

	@Override
	public boolean isUserAlreadyPresent(User user) {

		boolean isUserAlreadyExists = false;
		User existingUser = userRepository.findByEmail(user.getEmail());

		if (existingUser != null) {
			isUserAlreadyExists = true;
		}
		return isUserAlreadyExists;
	}

	@Override
	public List<User> listAll() {

		return null;

	}

	@Override
	public User get(int id) {

		return userRepository.findById(id);
	}

	@Override
	public User SaveEditUser(User user) {

		return userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	@Override
	public void updateemail(String email, String nemail) {

		userRepository.updatemail(email, nemail);
	}

	@Override
	public User findByM(String name) {
		return userRepository.findByEmail(name);
	}

}
