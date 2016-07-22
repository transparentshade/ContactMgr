package com.cmgr.trie;

import java.util.List;
import java.util.Map;

import com.cmgr.bean.Contact;
import com.cmgr.bean.SearchRank;
/**
 * Contract that any core Trie should satisfy.
 * Our App does'nt need delete operation on trie so skipping deletion operations
 * @author nbhoyar
 *
 */
public interface Trie {
	Map<SearchRank, List<Contact>> searchContact(String keyInitial);
	void addContact(String key, Contact c) throws Exception;
}