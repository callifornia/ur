package com.prokopiv.formvalidation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.prokopiv.bean.Search;

@Component
public class SearchFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Search.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}
}
