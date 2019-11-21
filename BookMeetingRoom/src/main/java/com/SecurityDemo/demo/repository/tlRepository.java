package com.SecurityDemo.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SecurityDemo.demo.model.Tl;

@Repository
public interface tlRepository extends JpaRepository<Tl, Integer> {
	@Modifying
	@Query(value = "insert into tl(name,dept,id,email) values(:name,:dept,:id,:email)", nativeQuery = true)
	@Transactional
	public void saveTl(@Param("name") String name, @Param("dept") String dept, @Param("id") int id,
			@Param("email") String email);

	@Query(value = "select * from tl where dept=:dept", nativeQuery = true)

	public List<Tl> getTl(@Param("dept") String dept);

	@Query(value = "select name from tl where dept=:dept", nativeQuery = true)
	public String getTlName(@Param("dept") String dept);

}
