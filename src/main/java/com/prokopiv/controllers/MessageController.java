package com.prokopiv.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MessageController {
	
	private static final Logger logger = LogManager.getLogger(MessageController.class);

	
	@RequestMapping(value = "/message/send", method = RequestMethod.POST)
	public String sendMessage(Model model, RedirectAttributes redirectAttribute){
		logger.info("SEND MESSAGE TO THE ADMIN AND REDIRECT WITH ALL GOOD TO THE MESSAGE LIST");
		redirectAttribute.addFlashAttribute("message", "message will be send ok");
		return "redirect:/message";
	}

	@RequestMapping(value = "/message/new_message", method = RequestMethod.GET)
	public String createMessage(Model model){
		logger.info("CREATE MESSAGE JSP");
		return "new_messages";
	}
	
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String message(Model model, SecurityContextHolderAwareRequestWrapper request){
		if(model.containsAttribute("message")){
			logger.info("has message");
		} else {
			logger.info("dont has message");
		}
		if(!request.isUserInRole("ROLE_ADMIN")){
			logger.info("SHOW LIST USER MESSAGES");
			return "user_messages";
		}
		logger.info("SHOW LIST MESSAGES");
		return "admin_messages";
		
	}

}
