package com.cmgr.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TrieNode objects will represent a node in the Trie data structure.
 * Here List<Contact> will represent list of contact that have fname/lname ending at this node in the Trie
 * Map<Character, TrieNode> childs will be map of all the nodes that are descendent of this node in Trie. 
 * @author nbhoyar
 *
 */
public class TrieNode {
	private Map<Character, TrieNode> childs = new HashMap<Character, TrieNode>();
	private List<Contact> contacts = new ArrayList<Contact>();
	public Map<Character, TrieNode> getChilds() {
		return childs;
	}
	public void setChilds(Map<Character, TrieNode> childs) {
		this.childs = childs;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
}
