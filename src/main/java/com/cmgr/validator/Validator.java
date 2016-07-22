package com.cmgr.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.cmgr.bean.Contact;
import com.cmgr.exception.ContactException;
/**
 * To validate user input
 * @author nbhoyar
 *
 */
public class Validator {
	static Pattern whiteSpaceMatcher = Pattern.compile(".*[\\s].*");
	/**
	 * Contact name validation
	 * 1. FIrst name should not be empty/null. Its trimmed before saving into trie. It should not have any white space characters
	 * like /t /r/f etc
	 * 2. Lastname can be empty. If its not empty then it should not have whitespace characters.
	 * @param c
	 * @throws ContactException
	 */
	public static void validateContact(Contact c) throws ContactException{
		List<Exception> list = new ArrayList<Exception>();
		list.addAll(validateFirstName(c.getFirstName()));
		list.addAll(validateLastName(c.getLastName()));
		if(list.isEmpty()){
			return;
		}
		throw new ContactException(list);
	}
	public static List<Exception> validateFirstName(String name){
		List<Exception> list = new ArrayList<Exception>();
		if(name == null || name.trim().length() == 0){
			list.add(new Exception("First name cannot be empty."));
		}
		if(whiteSpaceMatcher.matcher(name).matches()){
			list.add(new Exception("First name cannot contain white space characters."));
		}
		return list;
	}
	/**
	 * To validate the search query. Validations
	 * 1. It should not be empty/null. 
	 * 2. It should not have any empty/whitespace chars in it like /t /r/f /n etc.
	 * @param name
	 * @return
	 */
	public static List<Exception> validateLastName(String name){
		List<Exception> list = new ArrayList<Exception>();
		if(name != null && whiteSpaceMatcher.matcher(name).matches()){
			list.add(new Exception("Last name cannot contain white space characters."));
		}
		return list;
	}
	public static void validateSearchQuery(String query) throws ContactException{
		List<Exception> list = new ArrayList<Exception>();
		if(query == null || query.length() == 0){
			list.add(new Exception("Search query cannot be null or empty."));
		}
		if(StringUtils.isEmpty(query) || whiteSpaceMatcher.matcher(query).matches()){
			list.add(new Exception("whit space characters are not allowed in query string."));
		}
		if(!list.isEmpty()){
			throw new ContactException(list);
		}
		return ;
	}
}
