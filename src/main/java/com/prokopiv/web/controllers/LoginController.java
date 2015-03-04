package com.prokopiv.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prokopiv.web.model.User;


@Controller
public class LoginController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(){
		return "redirect:/users";
	}

	@RequestMapping(value = "/login")
	public String login(@ModelAttribute(value = "user") User user, Model model){
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
		model.addAttribute("message", "You dont have permition to enter this page");
		return "403";
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String chck(Model model) throws Exception{
		try{
			throw new Exception(); 
		} catch(Exception e){
			return "404";
		}
	}
}
