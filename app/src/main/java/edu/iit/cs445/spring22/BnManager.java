package edu.iit.cs445.spring22;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;



public class BnManager implements BoundaryInterface {
	private static List<Accounts> accounts = new ArrayList<Accounts>();
	private boolean inAccountsList = false;
	private boolean changingActiveStatus = false;

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
    	System.out.println("Replace Account: " +l.isNil());
    	
    	
    	l.setName(il.getName());
    	l.setAddress(il.getAddress());
    	l.setPhone(il.getPhone());
    	l.setPicture(il.getPicture());
    	
    	if(il.getIsActive()) {
    		this.setChangingActiveStatus(true);
    	}else {
    		this.setChangingActiveStatus(false);
    	}
    	
    	System.out.println("inAccountList: " + this.isInAccountsList());
    	if(!isInAccountsList()) {
    		accounts.add(l);
    		System.out.println("Replace Account: " +accounts.get(accounts.size()-1).isNil());
    	}
    }
	
	private Accounts findByUid(String lid) {
    	System.out.println(lid);
    	Iterator<Accounts> li = accounts.listIterator();
        while(li.hasNext()) {
            Accounts l = li.next();
            if(l.matchesUid(lid)) {
            	System.out.println("MATCHES");
            	this.setInAccountsList(true);
            	return(l);
            }
        }
        System.out.println("Null Account");
        this.setInAccountsList(false);
        return (new NullAccount());
    }
	
	public Accounts getAccountDetail(String lid) {
        return(findByUid(lid));
    }

	public boolean isInAccountsList() {
		return inAccountsList;
	}

	public void setInAccountsList(boolean inAccountsList) {
		this.inAccountsList = inAccountsList;
	}

	public boolean isChangingActiveStatus() {
		return changingActiveStatus;
	}

	public void setChangingActiveStatus(boolean changingActiveStatus) {
		this.changingActiveStatus = changingActiveStatus;
	}

}
