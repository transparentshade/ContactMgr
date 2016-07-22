package com.cmgr.bean;

/**
 * Contact objects will represent a contact in our dabaase.
 * @author nbhoyar
 *
 */
public class Contact {
	private String firstName;
	private String lastName;
	public Contact(String fname, String lname) {
		this.firstName = fname.trim();
		this.lastName = lname.trim();
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName() + "\n";
	}
	/*@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Contact) {
			Contact c = (Contact) obj;
			if(c.getFirstName().equals(this.getFirstName()) && c.getLastName().equals(this.getLastName())){
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		int hashCode = this.getFirstName().hashCode()+31*this.getLastName().hashCode();
		return hashCode;
	}*/
}
