package com.user.exmaple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.exmaple.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
