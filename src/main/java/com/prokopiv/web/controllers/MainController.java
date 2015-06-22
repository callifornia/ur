package com.prokopiv.web.controllers;

import java.security.Principal;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

import com.prokopiv.web.model.Search;
import com.prokopiv.web.model.User;
import com.prokopiv.web.service.UserService;
import com.prokopiv.web.validation.Pagination;
import com.prokopiv.web.validation.SearchFormValidator;
import com.prokopiv.web.validation.UserFormValidation;

@Controller
public class MainController {

	@Autowired UserService userService;
	@Autowired Search search;
	@Autowired UserFormValidation userFormValidation;
	@Autowired SearchFormValidator searchValidator;
	
	@Autowired Pagination pagination;
	@Autowired @Qualifier("authMgr") private AuthenticationManager authMgr;
	@Autowired private UserDetailsService userDetailsSvc;
	@Autowired CustomDateEditor custom;
	
	
	//setValidator for Search attribute
	@InitBinder(value = "search")
	private void initSearchBinder(WebDataBinder dataBinder){
		dataBinder.setValidator(searchValidator);
	}
	
	//setValidator for user attribute
	@InitBinder(value = "user")
	private void initUserBinder(WebDataBinder dataBinder){
		dataBinder.setValidator(userFormValidation);
		dataBinder.registerCustomEditor(Date.class, custom);
	}
	
	private static final Logger logger = LogManager.getLogger(MainController.class);
	
	// mapping inititalization tables and inserted data request
	@RequestMapping(value = "/initializeTables", method = RequestMethod.GET)
	public String initializeTables(Model model, RedirectAttributes redirectAttribute){
		logger.info("Initialization tables");
		if(userService.initializationDataBase()){
			redirectAttribute.addFlashAttribute("success", "Table successful created");
		} else {
			redirectAttribute.addFlashAttribute("error", "Can't create the tables");
		}
		return "redirect:/login";
	}
	
	// get user for user id
	@RequestMapping(value = "/user/{id}")
	public String user(@PathVariable(value = "id") String id,  Model model){
		userService.setUserAndSearchAttributes(model, userService.getUserById(id), search);
		return "user";
	}
	
	//recovery user after delete
	@RequestMapping(value = "/recovery/{id}", method = RequestMethod.GET)
	public String recovery(@PathVariable(value  = "id") String id, Model model, RedirectAttributes redirectAttribute){
		if(userService.recoveryUser(id)){
			redirectAttribute.addFlashAttribute("success", "User recovered");
		} else{
			redirectAttribute.addFlashAttribute("error", "Can't recover user");
		};
		return "redirect:/users";
	}
	
	//rendering register.jsp who has admin role
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute(value = "user") User user, Model model)	{
		userService.setFormList(model);
		model.addAttribute("search", search);
		return "register";
	}
	
	// inserted new user from login page
	@RequestMapping(value = "/inserted", method = RequestMethod.POST)
	public String insertAnonimus(@Validated User user, BindingResult bindingResult, Model model)	{
		if(bindingResult.hasErrors()){
			model.addAttribute("user", user);
			return "login";
		} else {
			userService.insertUser(user);
			// redirect user to the users.jsp after successful registration
			try {
				UserDetails userDetail = userDetailsSvc.loadUserByUsername(user.getUserLogin());
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetail, user.getUserPassword(), userDetail.getAuthorities());
				authMgr.authenticate(auth);
				if(auth.isAuthenticated()){
					SecurityContextHolder.getContext().setAuthentication(auth);
					return "redirect:/users";
				}
			} catch(Exception e){
				e.printStackTrace();
				model.addAttribute("error", "Can't connect to the data base");
			}
			return "login";
		}
	}
	
	//insert user in to db by admin
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@Validated User user,BindingResult bindingResult, Model model, RedirectAttributes redirectAttribute)	{
		if(bindingResult.hasErrors()){
			userService.setFormList(model);
			userService.setUserAndSearchAttributes(model, user, search);
			return "register";
		} else {
			if(userService.insertUser(user)){
				redirectAttribute.addFlashAttribute("success", "User successful registered");
			} else {
				redirectAttribute.addFlashAttribute("error", "Can't register user");
			}
			return "redirect:/users";
		}
	}
	
	// update user
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute(value="user") @Validated User user,
						BindingResult bindingResult, 
						Model model, 
						RedirectAttributes redirectAttribute){
		
		if(bindingResult.hasErrors()){
			userService.setUserAndSearchAttributes(model, user, search);
			userService.setFormList(model);
			return "edit";
		} else {
			//check if user successful updated
			if(userService.updateUser(user)){
				redirectAttribute.addFlashAttribute("success", "User successful update");
			} else {
				redirectAttribute.addFlashAttribute("error", "Can't update user");
			}
			return "redirect:/users";
		}
	}
	// rendering edit page
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model model){
		userService.setFormList(model);
		model.addAttribute("search", search);
		if(model.containsAttribute("user")){
			return "edit";
		} else{
			User user = userService.getUserById(id);
			model.addAttribute("user", user);
			return "edit";
		}
	}
	
	// search request
	@RequestMapping(value = "/searchRequest", method = RequestMethod.POST) 
	public String userSearch(Model model, @ModelAttribute(value = "search") Search searchRequest, RedirectAttributes redirectAttribute){
		search = searchRequest;
		return "redirect:/users/1";
	} 
	
	// rendering users.jsp with a users list, also set pagination for navigation
	@RequestMapping (value = "/users/{page}", method = RequestMethod.GET) 
	public String usersPage(@PathVariable (value = "page") Integer page, Model model){
		pagination.setCurrentPage(page);
		model.addAttribute("pagi", pagination);
		userService.setUserAndSearchAttributes(model, userService.getUserBySearch(search, pagination), search);
		return "users";
	} 
	
	// main page (users.jsp) after successful user login or registered
	@RequestMapping(value = "/users")
	public String users(Model model, Principal principal){
		pagination.setCurrentPage(1);
		model.addAttribute("pagi", pagination);
		userService.setUserAndSearchAttributes(model, userService.getUserList(pagination), search);
		return "users";
	}
	// delete user by id (set enable to false in db)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id, ModelMap model, RedirectAttributes redirectAttribute){
		//check if user successful deleted
		if(userService.deleteUser(id)){
			redirectAttribute.addFlashAttribute("success", "User successful banned");
		} else {
			redirectAttribute.addFlashAttribute("error", "Can't banned user");
		}
		return "redirect:/users";
	}	
}