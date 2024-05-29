package com.example.MailService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.MailService.Entity.Employee;
import com.example.MailService.Repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private JavaMailSender mailSender;

	public Employee postEmployee(Employee employee) {
		Employee savedEmployee = employeeRepository.save(employee);
		sendWelcomeEmail(savedEmployee);
		return savedEmployee;
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public void deleteEmployee(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Employee with ID " + id + " not found"));

		employeeRepository.deleteById(id);
		sendDeletionEmail(employee);
	}
	
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}
	
	public Employee updateEmployee(Long id, Employee employee) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if(optionalEmployee.isPresent()) {
			Employee existingEmployee = optionalEmployee.get();
			
			existingEmployee.setEmail(employee.getEmail());
			existingEmployee.setName(employee.getName());
			existingEmployee.setPhone(employee.getPhone());
			existingEmployee.setDepartment(employee.getDepartment());
			
			Employee updatedEmployee = employeeRepository.save(existingEmployee);
            sendUpdateEmail(updatedEmployee);

            return updatedEmployee;
			
		}
		return null;
	}

	private void sendWelcomeEmail(Employee employee) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(employee.getEmail());
		message.setSubject("Welcome to the Employee Database");
		message.setText(
				"Dear " + employee.getName() + ",\n\nYou have been successfully added to the employee database.");
		mailSender.send(message);

	}
	private void sendDeletionEmail(Employee employee) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(employee.getEmail());
        message.setSubject("Removed from the Employee Database");
        message.setText("Dear " + employee.getName() + ",\n\nYou have been removed from the employee database.");
        mailSender.send(message);
    }
	
	private void sendUpdateEmail(Employee employee) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(employee.getEmail());
        message.setSubject("Your Employee Details Have Been Updated");
        message.setText(
                "Dear " + employee.getName() + ",\n\nYour employee details have been successfully updated in our system.");
        mailSender.send(message);
    }

}
