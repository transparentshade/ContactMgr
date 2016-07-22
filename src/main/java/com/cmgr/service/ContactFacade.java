package com.cmgr.service;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cmgr.bean.Contact;
import com.cmgr.bean.SearchRank;
import com.cmgr.exception.ContactException;
import com.cmgr.trie.SimpleTrie;
import com.cmgr.validator.Validator;
/**
 * ContactFacade is the entry point to the contact database. 
 * SimpleTrie byFirstName : will be used to store contacts reference based on there firstname. It will help to query based on 
 * first name of contact
 * SimpleTrie byLastName : will be used to store contacts references based on there lastname. To facilitate searching based on
 * last name.

 * @author nbhoyar
 *
 */
public class ContactFacade {
	private SimpleTrie byFirstName;
	private SimpleTrie byLastName;
	
	public ContactFacade() {
		this.byFirstName = new SimpleTrie();
		this.byLastName = new SimpleTrie();
	}
	/**
	 * To create the contact and store in trie
	 * @param c : contact to store in trie
	 */
	public void createContact(Contact c){
		try{
			Validator.validateContact(c);
		}
		catch(ContactException e ){
			System.err.println("Invalid contact! Cannot be added to the application. Related errors are given below,");
			System.err.println(e);
			return;
		}
		byFirstName.addContact(c.getFirstName(), c);
		byLastName.addContact(c.getLastName(), c);
		return;
	}
	/**
	 * To search the Trie based on search query.
	 * @param query : query provided by user
	 * @return set of contacts satisfying search query.
	 */
	public Set<Contact> searchContact(String query){
		try{
			Validator.validateSearchQuery(query);
		}
		catch(ContactException e){
			System.err.println("Invalid search query! Related errors are given bleow,");
			System.err.println(e);
			return null;
		}
		/**
		 * Search based on first name
		 */
		Map<SearchRank, List<Contact>> mp = byFirstName.searchContact(query);
		
		/**
		 * Search based on last name
		 */
		Map<SearchRank, List<Contact>> mp2 = byLastName.searchContact(query);

		//merge two result into one entity
		mergeContactMap(mp, mp2, SearchRank.PERFECT);
		mergeContactMap(mp, mp2, SearchRank.PARTIAL);
		/**
		 * Create set of contacts to remove duplicate contacts like 
		 * Contact name : nikhil nikhil
		 * In this case if we search based on firstname or lastname both trie will give us back the same contact. To 
		 * remove this kind of duplicate I used set here.
		 */
		Set<Contact> res = new LinkedHashSet<Contact>();
		if(mp.get(SearchRank.PERFECT) != null)
			res.addAll(mp.get(SearchRank.PERFECT));
		if(mp.get(SearchRank.PERFECT) != null)
			res.addAll(mp.get(SearchRank.PARTIAL));
		return res;
	}
	//Merge the two maps based on search rank
	private void mergeContactMap(Map<SearchRank, List<Contact>> mp, Map<SearchRank, List<Contact>> mp2, SearchRank sr){
		if(mp == null || mp2 == null || mp2.get(sr) == null) return;
		if(mp.get(sr) != null)mp.get(sr).addAll(mp2.get(sr));
		else{
			mp.put(sr, mp2.get(sr));
		}
	}
}
