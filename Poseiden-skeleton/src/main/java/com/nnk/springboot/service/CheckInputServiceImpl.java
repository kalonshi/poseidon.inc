package com.nnk.springboot.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class CheckInputServiceImpl implements CheckInputService {

	// Requirement for Password registration:At least a size of 8 and max 20 contain
	// digit + lowercase char + uppercase char + punctuation + symbol
	private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

	private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

	private static final String NUMBER_PATTERN = "^(?=.*[0-9]).{1,10}$";

	private static final Pattern patternNumber = Pattern.compile(NUMBER_PATTERN);

	@Override
	public boolean checkPassword(String password) {

		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	/*
	 * @Override public boolean checkInputIsNumber(String inputNumber) { Matcher
	 * matcherpattern = patternNumber.matcher(inputNumber); return
	 * matcherpattern.matches(); }
	 */

}
