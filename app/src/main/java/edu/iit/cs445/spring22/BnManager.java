package edu.iit.cs445.spring22;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;



public class BnManager implements BoundaryInterface {
	private static List<Accounts> accounts = new ArrayList<Accounts>();

	public List<Accounts> getAllAccounts() {
		return accounts;
	}
	
	public Accounts createAccounts(Accounts il) {
        Accounts l= new Accounts(il);
        accounts.add(l);
        return(l);
    }
	
	public void replaceAccount(String lid, Accounts il) {
		//Will not active account
    	Accounts l = findByUid(lid);
    	l.setName(il.getName());
    	l.setAddress(il.getAddress());
    	l.setPhone(il.getPhone());
    	l.setPicture(il.getPicture());
    }
	
	private Accounts findByUid(String lid) {
    	System.out.println(lid);
    	Iterator<Accounts> li = accounts.listIterator();
        while(li.hasNext()) {
            Accounts l = li.next();
            if(l.matchesUid(lid)) return(l);
        }
        return (new Accounts());
    }
	
	public Accounts getAccountDetail(String lid) {
        return(findByUid(lid));
    }

}
