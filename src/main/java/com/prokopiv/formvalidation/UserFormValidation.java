package com.prokopiv.formvalidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.prokopiv.bean.User;

public class UserFormValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userRole","user.err.role");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userGender", "user.err.gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userEducation", "user.err.education");
		User user = (User) target;
		if (user.getUserLogin().length() < 4 || user.getUserLogin().length() > 40) {
			errors.rejectValue("userLogin", "user.err.login");
		}
		if (user.getUserPassword().length() < 6	|| user.getUserPassword().length() > 40) {
			errors.rejectValue("userPassword", "user.err.password");
		}
		if (user.getUserlastName().isEmpty()) {
			errors.rejectValue("userlastName", "user.err.lastName");
		} else if(user.getUserlastName().length() > 70){
			errors.rejectValue("userlastName", "user.err.lastName.long");
		}
		if (!isPhoneNumberValid(user.getUserPhone())) {
			errors.rejectValue("userPhone", "user.err.phone");
		}
		if (user.getUserAdress().length() > 254) {
			errors.rejectValue("userAdress", "user.err.adress");
		}
		if (user.getUserDescription().length() > 3000) {
			errors.rejectValue("userDescription", "user.err.description");
		}
		if(!user.getUserMail().isEmpty() && !isEmailValid(user.getUserMail())){
			errors.rejectValue("userMail", "user.err.mail");
		}
	}
	
	private boolean isPhoneNumberValid(String phoneNumber){  
		String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
		return isValid(phoneNumber, expression);
	}
	
	private boolean isEmailValid(String email) {
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		return isValid(email, expression);
	}
	
	private boolean isValid(String inputString, String expression){
		boolean isValid = false;  
		CharSequence valid = inputString;
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);  
		Matcher matcher = pattern.matcher(valid);  
		if(matcher.matches()){  
			isValid = true;  
		}  
		return isValid;  
	}
}