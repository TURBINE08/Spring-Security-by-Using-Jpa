package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.IUserService;

//@RestController
@Controller
public class UserController {
	@Autowired
	private IUserService service;

	// show registered page
	@GetMapping("/reg")
	public String userReg() {
		return "userRegister";
	}
	
	@PostMapping("/insert")
	public String saveUser(@ModelAttribute User user, Model model)
	{
		System.out.println("11111111111");

		Integer id = service.saveUser(user);
		String msg = "User '"+id+"'saved";
		model.addAttribute("message", msg);
		return "userRegister";
	}

}
