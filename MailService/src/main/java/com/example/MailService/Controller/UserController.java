package com.example.MailService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MailService.Entity.Employee;

import com.example.MailService.Entity.User;
import com.example.MailService.Service.EmployeeService;
import com.example.MailService.Service.UserService;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {
	@Autowired
    private UserService userService;
	
	@Autowired
	private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
    
    @PostMapping("/employee")
    public ResponseEntity<Employee> postEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.postEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }
    
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
    	return employeeService.getAllEmployees();
    }
    
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
    	
    	try {
    		employeeService.deleteEmployee(id);
    		return new ResponseEntity<>("Employee with ID " + id + " deleted successfully ", HttpStatus.OK);
    	} catch(EntityNotFoundException e) {
    		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    	}
  
    }
    
    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeId(@PathVariable Long id) {
    	Employee employee = employeeService.getEmployeeById(id);
    	if(employee == null) return ResponseEntity.notFound().build();
    	return ResponseEntity.ok(employee);
    } 
    
    @PatchMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id,@RequestBody Employee employee) {
    	Employee updatedEmployee = employeeService.updateEmployee(id, employee);
    	
    	if(updatedEmployee == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	return ResponseEntity.ok(updatedEmployee);
    	
    }
    

}
