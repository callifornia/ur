package com.prokopiv.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prokopiv.bean.Search;
import com.prokopiv.bean.User;
import com.prokopiv.formvalidation.SearchFormValidator;
import com.prokopiv.formvalidation.UserFormValidation;
import com.prokopiv.service.UserService;

@Controller
public class MainController {

	private static Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	UserFormValidation userFormValidation;
	
	@Autowired
	SearchFormValidator searchValidator;
	
	@InitBinder(value = "user")
	private void initUserBinder(WebDataBinder dataBinder){
		logger.info(" ======================> date and userValidate");
		dataBinder.setValidator(userFormValidation);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		CustomDateEditor custom = new CustomDateEditor(dateFormat, true);
		dataBinder.registerCustomEditor(Date.class, custom);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET )
	public String search(Model model){
		model.addAttribute("search", searchValidator);
		logger.info("/SEARCH PAGE. SHOW SEARCH FORM");
		return "search";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute(value = "user") User user, Model model)	{
		insertList(model);
		return "register";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@Validated User user,BindingResult bindingResult, Model model, RedirectAttributes redirectAttribute)	{
		if(bindingResult.hasErrors()){
			insertList(model);
			model.addAttribute("user", user);
			return "register";
		} else {
			redirectAttribute.addFlashAttribute("success", "Succesful user registered");
			userService.insertUser(user);
			return "redirect:/users";
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute(value="user") @Validated User user, 
						BindingResult bindingResult, 
						Model model, 
						RedirectAttributes redirectAttribute){
		
		if(bindingResult.hasErrors()){
			logger.info("==============hasError()===============" + bindingResult.getFieldError());
			model.addAttribute("user", user);
			insertList(model);
			return "edit";
		} else {
			userService.updateUser(user);
			logger.info("UPDATE USER IN DATA BASE. (add model attribute. add success)");
			redirectAttribute.addFlashAttribute("success", "Succesful user update");
			return "redirect:users";
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model model){
		insertList(model);
		if(model.containsAttribute("user")){
			return "edit";
		} else{
			logger.info("/edit/{id}: " + id);
			User user = userService.getUserById(id);
			model.addAttribute("user", user);
			return "edit";
		}
	}
	
	
	@RequestMapping(value = "/users")
	public String users(Model model)	{
		logger.info("SHOW USERS JSP WITH ALL USERS");
		if(model.containsAttribute("success")){
			logger.info("has key: " + model.asMap().get("success"));
		} else {
			logger.info("dont have a key success");
		}
		model.addAttribute("user", userService.getUserList());
		return "users";
	}
		

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id, ModelMap model, RedirectAttributes redirectAttribute){
		redirectAttribute.addFlashAttribute("success", "Succesful user delete");
		logger.info("DELETE USER. (add modell attribute(add success) id() => )" + id);
		userService.deleteUser(id);
		return "redirect:/users";
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
		
		model.addAttribute("roles", userRole);
		model.addAttribute("genders", userGender);
		model.addAttribute("education", userEducation);
	}

}