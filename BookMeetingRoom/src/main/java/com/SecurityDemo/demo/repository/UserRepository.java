package com.SecurityDemo.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SecurityDemo.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

	public User findById(int id);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE auth_user c SET c.email =:nemail WHERE c.email = :email", nativeQuery = true)
	void updatemail(@Param("email") String email, @Param("nemail") String nemail);

}
