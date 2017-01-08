package com.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jpa.entity.User;
import com.jpa.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String listUser(Model model) {
		
		List<User> users = userService.findAll();
		
		model.addAttribute("userList", users);
		
		return "userlist";
		
	}

}
