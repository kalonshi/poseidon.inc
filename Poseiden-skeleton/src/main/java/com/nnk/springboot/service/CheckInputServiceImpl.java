package com.nnk.springboot.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
@Service
public class CheckInputServiceImpl implements CheckInputService {
	// digit + lowercase char + uppercase char + punctuation + symbol
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	
    private static final String NUMBER_PATTERN =
            "^(?=.*[0-9]).{1,10}$";

    private static final Pattern patternNumber = Pattern.compile(NUMBER_PATTERN);
    
    @Override
	public boolean checkInputIsNumber(Object object) {
		// TODO Auto-generated method stub
    	String inputString=object.toString();
    	Matcher matcherpattern = patternNumber.matcher(inputString);
        return matcherpattern.matches();
	}

	@Override
	public boolean checkPassword(String password) {
		
		Matcher matcher = pattern.matcher(password);
        return matcher.matches();
	}

	@Override
	public boolean checkInputIsNumber(String inputNumber) {
		Matcher matcherpattern = patternNumber.matcher(inputNumber);
        return matcherpattern.matches();
	}

	
}
