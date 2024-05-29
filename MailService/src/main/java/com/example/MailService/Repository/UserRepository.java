package com.example.MailService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MailService.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
    User findByEmail(String email);

}
