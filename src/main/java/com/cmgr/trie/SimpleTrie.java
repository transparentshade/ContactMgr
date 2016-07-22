package com.cmgr.trie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cmgr.bean.Contact;
import com.cmgr.bean.SearchRank;
import com.cmgr.bean.TrieNode;

/**
 * Implementation of Trie Interface
 * @author nbhoyar
 *
 */
public class SimpleTrie implements Trie{
	private TrieNode root = new TrieNode();
	/**
	 * To search contact in trie
	 * Comoplexity : O(length of string)
	 */
	@Override
	public Map<SearchRank, List<Contact>> searchContact(String initial) {
		TrieNode t = this.root;
		int start = 0;
		Map<SearchRank, List<Contact>> res = new HashMap<SearchRank, List<Contact>>();
		while(start < initial.length() && t != null && t.getChilds().containsKey(initial.charAt(start))){
			t = t.getChilds().get(initial.charAt(start));
			start ++;
		}
		if(start == initial.length() && t != null){
			if(t.getContacts() != null)
				res.put(SearchRank.PERFECT, t.getContacts());
			//now add all the nodes contact child of t
			List<Contact> list = res.get(SearchRank.PARTIAL);
			if(list == null) list = new ArrayList<Contact>();
			collectChildsContact(t, list);
			res.put(SearchRank.PARTIAL, list);
		}
		return res;
	}

	/**
	 * To add contact in trie
	 * Complexity : O(length of key)
	 */
	@Override
	public void addContact(String key, Contact c) {
		TrieNode t = this.root;
		int start = 0;
		//travel trie till you end up on the empty node or key gets exhausted.
		while(start < key.length() && t!= null && t.getChilds().containsKey(key.charAt(start))){
			t = t.getChilds().get(key.charAt(start));
			start ++;
		}
		if(start < key.length()){
			while(start < key.length()){
				TrieNode newT = new TrieNode();
				t.getChilds().put(key.charAt(start), newT);
				t = newT;
				start++;
			}
		}
		//now add contact to the last trie node
		t.getContacts().add(c);
	}
	private void collectChildsContact(TrieNode t, List<Contact> res){
		if(t == null) return ;
		for(TrieNode child : t.getChilds().values()){
			res.addAll(child.getContacts());
			collectChildsContact(child, res);
		}
	}
}
