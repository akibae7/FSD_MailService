package com.example.MailService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.example.MailService.Entity.User;
import com.example.MailService.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;
	

    @Autowired
    private JavaMailSender mailSender;

    public User registerUser(User user) {
        User savedUser = userRepository.save(user);
        sendRegistrationEmail(savedUser);
        return savedUser;
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    private void sendRegistrationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Registration Confirmation");
        message.setText("Dear " + user.getUsername() + ",\n\nThank you for registering for our site!\n\nBest regards,\nMail Service");
        mailSender.send(message);
    }
    
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
