package com.fundamentos.springboot.fundamentos.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fundamentos.springboot.fundamentos.dto.UserDTO;
import com.fundamentos.springboot.fundamentos.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.email=?1")
	Optional<User> findByUserEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.name like ?1%")
	List<User> findAndSort(String name, Sort sort);
	
	List<User> findByName(String name);

	Optional<User> findByEmailAndName(String email, String name);
	
	List<User> findByNameLike(String name);
	
	List<User> findByNameOrEmail(String name, String email);
	
	
	List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);
	
	//List<User> findByNameLikeOrderByIdDesc(String name);

	List<User> findByNameContainingOrderByIdDesc(String name);
	
	@Query("SELECT new com.fundamentos.springboot.fundamentos.dto.UserDTO(u.id, u.name, u.birthDate)"
			+ " FROM User u "
			+ " WHERE u.birthDate=:parametroFecha "
			+ " AND u.email=:parametroEmail")
	Optional<UserDTO> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date, @Param("parametroEmail") String email);
	
}