package edu.iit.cs445.spring22;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;



public class BnManager implements BoundaryInterface {
	private static List<Accounts> accounts = new ArrayList<Accounts>();
	private static List<Asks> asks = new ArrayList<Asks>();
	private boolean inAccountsList = false;
	private boolean changingActiveStatus = false;
	
	//TODO: ACCOUNTS
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
    	
    	if(il.getIsActive()) {
    		this.setChangingActiveStatus(true);
    	}else {
    		this.setChangingActiveStatus(false);
    	}
    	
    	if(!isInAccountsList()) {
    		accounts.add(l);
    	}
    }
	
	private Accounts findByUid(String lid) {
    	//System.out.println(lid);
    	Iterator<Accounts> li = accounts.listIterator();
        while(li.hasNext()) {
            Accounts l = li.next();
            if(l.matchesUid(lid)) {
            	l.setIsNil(false);
            	this.setInAccountsList(true);
            	return(l);
            }
        }
        this.setInAccountsList(false);
        return (new NullAccount());
    }
	
	public Accounts getAccountDetail(String lid) {
        return(findByUid(lid));
    }
	public Accounts activateAccountDetail(String lid) {
		Accounts l = findByUid(lid);
		l.activate();
        return l;
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

	//TODO: ASKS
	public Asks createAsks(Asks il) {
		Asks l= new Asks(il);
        asks.add(l);
        return(l);
	}

}
