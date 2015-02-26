package com.prokopiv.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prokopiv.bean.User;


@Controller
public class LoginController {
	
	
	
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(){
		return "redirect:/login";
	}

	@RequestMapping(value = "/login")
	public String login(@ModelAttribute(value = "user") User user, Model model){
//		insertList(model);
		return "login";
	}

	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public String loginError(ModelMap model){
		return "redirect:/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model){
		return "redirect:login";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(ModelMap model){
		model.addAttribute("message", "You dont have permition to enterr this page");
		return "403";
	}
	private void insertList(Model model){
		List<String> userGender = new ArrayList<String>(); 
		userGender.add("Male");
		userGender.add("Female");
		
		Map<String,String> userRole = new HashMap<String,String>();
		userRole.put("ROLE_ADMIN", "Admin");
		userRole.put("ROLE_REGULAR_USER", "User");
		
		List<String> userEducation = new ArrayList<String>();
		userEducation.add("Degree");
		userEducation.add("Master Degree");
		userEducation.add("Other");
		
		model.addAttribute("roles", userRole);
		model.addAttribute("genders", userGender);
		model.addAttribute("education", userEducation);
	}
	
	
}
