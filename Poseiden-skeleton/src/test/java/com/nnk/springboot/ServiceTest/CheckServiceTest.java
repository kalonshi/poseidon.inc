package com.nnk.springboot.ServiceTest;

import java.util.stream.Stream;

public class CheckServiceTest {
	 
	   
	    void test_password_regex_valid(String password) {
	       
	    }

	   
	    void test_password_regex_invalid(String password) {
	        
	    }

	    static Stream<String> validPasswordProvider() {
	        return Stream.of(
	                "AAAbbbccc@123",
	                "Hello world$123",
	                "A!@#&()–a1",               // test punctuation part 1
	                "A[{}]:;',?/*a1",           // test punctuation part 2
	                "A~$^+=<>a1",               // test symbols
	                "0123456789$abcdefgAB",     // test 20 chars
	                "123Aa$Aa"                  // test 8 chars
	        );
	    }

	    // At least
	    // one lowercase character,
	    // one uppercase character,
	    // one digit,
	    // one special character
	    // and length between 8 to 20.
	    static Stream<String> invalidPasswordProvider() {
	        return Stream.of(
	                "12345678",                 // invalid, only digit
	                "abcdefgh",                 // invalid, only lowercase
	                "ABCDEFGH",                 // invalid, only uppercase
	                "abc123$$$",                // invalid, at least one uppercase
	                "ABC123$$$",                // invalid, at least one lowercase
	                "ABC$$$$$$",                // invalid, at least one digit
	                "java REGEX 123",           // invalid, at least one special character
	                "java REGEX 123 %",         // invalid, % is not in the special character group []
	                "________",                 // invalid
	                "--------",                 // invalid
	                " ",                        // empty
	                "");                        // empty
	    }
}
