package com.progweb.programacaoweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progweb.programacaoweb.model.User;

public interface UserDAO extends JpaRepository<User, Long> { }
