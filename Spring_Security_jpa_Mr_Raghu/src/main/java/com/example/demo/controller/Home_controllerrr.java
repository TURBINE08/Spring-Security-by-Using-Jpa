package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.IUserService;

@RestController
@Controller
public class Home_controllerrr
{
	@Autowired
	private IUserService service;
	
	@GetMapping("/home")
	public String showhome() {
		return "HomePage";

	}
	
	@GetMapping("/welcome")
	public String showWelcome() {
		return "welcome to HomePage";
	}
	
	@GetMapping("/admin")
	public String showAdmin() {
		return "welcome to AdminPage";
	}
	
	@GetMapping("/emp")
	public String showEmployee() {
		return "welcome to EmployeePage";
	}
	@GetMapping("/std")
	public String showStd() {
		return "welcome to studentPage";
	}
	@GetMapping("/denied")
	public String showdnoed() {
		return "denide Page";
	}
	
	@PostMapping("/save")
	public String saveUser(@RequestBody User user)
	{
		System.err.println("11111111111");

		Integer id = service.saveUser(user);
		String msg = "User '"+id+"'saved";
//		model.addAttribute("message", msg);
		return msg;
	}
	
}
