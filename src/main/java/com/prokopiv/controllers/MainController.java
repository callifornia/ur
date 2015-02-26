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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
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

import com.prokopiv.bean.Search;
import com.prokopiv.bean.User;
import com.prokopiv.formvalidation.Pagination;
import com.prokopiv.formvalidation.SearchFormValidator;
import com.prokopiv.formvalidation.UserFormValidation;
import com.prokopiv.service.UserService;

@Controller
public class MainController {

	@Autowired UserService userService;
	@Autowired Search search;
	@Autowired UserFormValidation userFormValidation;
	@Autowired SearchFormValidator searchValidator;
	
	@Autowired Pagination pagination;
	@Autowired @Qualifier("authMgr") private AuthenticationManager authMgr;
	@Autowired private UserDetailsService userDetailsSvc;
	
	private static Logger logger = LogManager.getLogger(LoginController.class);

	@InitBinder(value = "search")
	private void initSearchBinder(WebDataBinder dataBinder){
		dataBinder.setValidator(searchValidator);
	}
	
	@InitBinder(value = "user")
	private void initUserBinder(WebDataBinder dataBinder){
		dataBinder.setValidator(userFormValidation);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		CustomDateEditor custom = new CustomDateEditor(dateFormat, true);
		dataBinder.registerCustomEditor(Date.class, custom);
	}
	
	@RequestMapping(value = "/initializeTables", method = RequestMethod.GET)
	public String initializeTables(Model model){
		userService.initializationDataBase();
		return "redirect:/login";
	}
	
	
	@RequestMapping(value = "/user/{id}")
	public String user(@PathVariable(value = "id") String id,  Model model){
		model.addAttribute("user", userService.getUserById(id));
		return "user";
	}
	
	@RequestMapping(value = "/recovery/{id}", method = RequestMethod.GET)
	public String recovery(@PathVariable(value  = "id") String id, Model model, RedirectAttributes redirectAttribute){
		if (userService.recoveryUser(id)){
			redirectAttribute.addFlashAttribute("success", "Пользователь воскрешен");
		} else {
			redirectAttribute.addFlashAttribute("success", "Не получилось");
		}
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute(value = "user") User user, Model model)	{
		insertList(model);
		return "register";
	}
	
	@RequestMapping(value = "/registered", method = RequestMethod.GET)
	public String registerAnonimus(@ModelAttribute(value = "user") User user, Model model)	{
		insertList(model);
		return "registered";
	}
	
	@RequestMapping(value = "/inserted", method = RequestMethod.POST)
	public String insertAnonimus(@Validated User user, BindingResult bindingResult, Model model)	{
		if(bindingResult.hasErrors()){
//			insertList(model);
			model.addAttribute("user", user);
			return "login";
		} else {
			userService.insertUser(user);
			try {
				UserDetails userDetail = userDetailsSvc.loadUserByUsername(user.getUserLogin());
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetail, user.getUserPassword(), userDetail.getAuthorities());
				authMgr.authenticate(auth);
				if(auth.isAuthenticated()){
					SecurityContextHolder.getContext().setAuthentication(auth);
					return "redirect:/users";
				}
			} catch(Exception e){
				logger.info("beeeeeeeeeeeeeeeeeeee");
			}
			logger.info("redirect to login page");
			return "redirect:/login";
		}
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
			model.addAttribute("user", user);
			insertList(model);
			return "edit";
		} else {
			userService.updateUser(user);
			redirectAttribute.addFlashAttribute("success", "Succesful user update");
			return "redirect:/users";
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model model){
		insertList(model);
		if(model.containsAttribute("user")){
			return "edit";
		} else{
			User user = userService.getUserById(id);
			model.addAttribute("user", user);
			return "edit";
		}
	}
	
	@RequestMapping(value = "/searchRequest", method = RequestMethod.POST) 
	public String userSearch(Model model, @ModelAttribute(value = "search") Search searchRequest, RedirectAttributes redirectAttribute){
		search = searchRequest;
		return "redirect:/users/1";
	} 
	 
	@RequestMapping (value = "/users/{page}", method = RequestMethod.GET) 
	public String usersPage(@PathVariable (value = "page") Integer page, Model model){
		pagination.setCurrentPage(page);
		model.addAttribute("pagi", pagination);
		model.addAttribute("search", search);
		model.addAttribute("user", userService.getUserBySearch(search, pagination));
		return "users";
	} 
	
	@RequestMapping(value = "/users")
	public String users(Model model){
		pagination.setCurrentPage(1);
		model.addAttribute("pagi", pagination);
		model.addAttribute("search", search);
		model.addAttribute("user", userService.getUserList(pagination));
		return "users";
	}
		

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id, ModelMap model, RedirectAttributes redirectAttribute){
		redirectAttribute.addFlashAttribute("success", "Пользователь казнен");
		userService.deleteUser(id);
		return "redirect:/users";
	}
	
	private void insertList(Model model){
		List<String> userGender = new ArrayList<String>(); 
		userGender.add("Male");
		userGender.add("Female");		
		
		Map<String, String> search = new HashMap<String,String>();
		
		search.put("all", "all");
		search.put("phone", "Телефон");
		search.put("lastName", "ФИО");
		search.put("login", "Логин");
		
		Map<String,String> userRole = new HashMap<String,String>();
		userRole.put("ROLE_ADMIN", "Admin");
		userRole.put("ROLE_REGULAR_USER", "User");
		
		List<String> userEducation = new ArrayList<String>();
		userEducation.add("Degree");
		userEducation.add("Master Degree");
		userEducation.add("Other");
		
		model.addAttribute("searchName", search);
		model.addAttribute("roles", userRole);
		model.addAttribute("genders", userGender);
		model.addAttribute("education", userEducation);
	}
}