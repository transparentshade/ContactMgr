package com.cmgr.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * CustomException class which will help to deliver the all the errors present
 * in user request or failure occurred while serving user.
 * @author nbhoyar
 *
 */
public class ContactException extends Exception{
	List<Exception> relatedErrors = new ArrayList<Exception>();
	public ContactException(List<Exception> list) {
		this.relatedErrors = list;
	}
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for(Exception e : relatedErrors){
			res.append(e.getMessage()+"\n");
		}
		return res.toString();
	}
}
