package com.example.demo.service;

import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Component
public interface IUserService 
{
	public Integer saveUser(User user);

}
