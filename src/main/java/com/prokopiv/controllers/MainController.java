package com.prokopiv.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

	private static Logger logger = LogManager.getLogger(LoginController.class);

	
	@RequestMapping(value = "/search", method = RequestMethod.GET )
	public String search(){
		logger.info("/SEARCH PAGE. SHOW SEARCH FORM");
		return "search";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(ModelMap model)	{
		logger.info("SHOW REGISTER PAGE");
		return "register";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(ModelMap model, RedirectAttributes redirectAttribute)	{
		logger.info("INSERT USER IN TO THE DATA BASE. (add modell attributte. add success)");
		redirectAttribute.addFlashAttribute("success", "Succesful user registered");
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, ModelMap model){
		logger.info("/edit/{id}: " + id);
		return "edit";
	}
	
	
	@RequestMapping(value = "/users")
	public String users(Model model)	{

		logger.info("SHOW USERS JSP WITH ALL USERS");
		if(model.containsAttribute("success")){
			logger.info("has key: " + model.asMap().get("success"));
		} else {
			logger.info("dont have a key success");
		}
		return "users";
	}
		
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, RedirectAttributes redirectAttributes){
		
		logger.info("UPDATE USER IN DATA BASE. (add model attribute. add success)");
		redirectAttributes.addFlashAttribute("success", "Succesful user update");
		return "redirect:users";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id, ModelMap model, RedirectAttributes redirectAttribute){
		redirectAttribute.addFlashAttribute("success", "Succesful user delete");
		logger.info("DELETE USER. (add modell attribute . add success)" + id);
		return "redirect:/users";
	}
}
