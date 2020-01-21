package com.bridgelabz.fundooNotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundooNotes.model.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	UserEntity findByUser(String userId);
}
