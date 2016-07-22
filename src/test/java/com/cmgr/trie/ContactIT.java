package com.cmgr.trie;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import com.cmgr.bean.Contact;
import com.cmgr.bean.SearchRank;
import com.cmgr.service.ContactFacade;
@RunWith(JUnit4ClassRunner.class)
public class ContactIT {
	@Test
	public void test() throws Exception{
		Contact c = new Contact("abc", "zyxz");
		Contact d = new Contact("ab", "pqr");
		
		SimpleTrie trie = new SimpleTrie();
		trie.addContact(c.getFirstName(), c);
		trie.addContact(d.getFirstName(), d);
		Map<SearchRank, List<Contact>> mp = trie.searchContact("ab");
		Assert.assertEquals(mp.get(SearchRank.PARTIAL).size(), 1);
		Assert.assertEquals(mp.get(SearchRank.PERFECT).size(), 1);
	}
	
	@Test
	public void stressTest() throws Exception{
		SimpleTrie trie = new SimpleTrie();
		for(int i=10; i<=100; i++){
			for(int j=0; j<10; j++)trie.addContact(""+i, new Contact(""+i, ""+i+j));
		}
		Map<SearchRank, List<Contact>> res = trie.searchContact("12");
//		System.out.printf("Found Perfect %s Partial %s", res.get(SearchRank.PERFECT), res.get(SearchRank.PARTIAL));
	}
	
	@Test
	public void testContactFacade(){
		ContactFacade cf = new ContactFacade();
		Contact c1 = new Contact("abc", "def");
		Contact c2 = new Contact("bc", "de");
		Contact c3 = new Contact("abcd", "defg");
		Contact c4 = new Contact("bcd", "efgh");
		Contact c5 = new Contact("def", "abc");
		Contact c6 = new Contact("ef", "abc");
		Contact c7 = new Contact("de", "bc");
		Contact c8 = new Contact("abc", "pz");
		Contact c9 = new Contact("pz", "def");
		cf.createContact(c1);
		cf.createContact(c2);
		cf.createContact(c3);
		cf.createContact(c4);
		cf.createContact(c5);
		cf.createContact(c6);
		cf.createContact(c7);
		cf.createContact(c8);
		cf.createContact(c9);
		Set<Contact> res = null;
		res = cf.searchContact("a");
		System.out.println("with 'a' " + res);
		Assert.assertTrue(validateContactSet(res, 5, 0, null));
		
		res = cf.searchContact("ab");
		Assert.assertTrue(validateContactSet(res, 5, 0, null));
		System.out.println("with 'ab' " + res);
		
		res = cf.searchContact("abc");
		Assert.assertTrue(validateContactSet(res, 5, 4, "abc"));
		System.out.println("with 'abc' " + res);
		
		res = cf.searchContact("defg");
		Assert.assertTrue(validateContactSet(res, 1, 1, "defg"));
		
		res = cf.searchContact("kdk");
		Assert.assertTrue(validateContactSet(res, 0, 0, null));
		
		res = cf.searchContact("\t");
		Assert.assertNull(res);
		
		Contact c = new Contact("nikhil ", "");
		cf.createContact(c);
		
		res = cf.searchContact("nikhil");
		Assert.assertTrue(validateContactSet(res, 1, 1, "nikhil"));
		
		c = new Contact("\t\t  ", "bhoyar");
		cf.createContact(c);
		
		Contact cd = new Contact("nikhi   d", "Bhoyar .");
		cf.createContact(cd);
		
	}
	
	@Test
	public void testLargeContact(){
	}
	
	private boolean validateContactSet(Set<Contact> s, int count, int perfectCount, String searchQuery){
		if(s!=null && s.size() == count) {
			if(searchQuery != null){
				int pCount = 0;
				for(Contact c : s){
					if(c.getFirstName().equals(searchQuery) || c.getLastName().equals(searchQuery)){
						pCount ++;
					}
				}
				return pCount == perfectCount;
			}
			return true;
		}
		return false;
	}
}
