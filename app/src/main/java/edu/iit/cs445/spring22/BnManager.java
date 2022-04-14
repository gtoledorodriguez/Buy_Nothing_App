package edu.iit.cs445.spring22;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


public class BnManager implements BoundaryInterface {
	private static List<Accounts> accounts = new ArrayList<Accounts>();
	
//	public BnManager() {
//		
//	}

	public List<Accounts> getAllAccounts() {
		return accounts;
	}
	
	public Accounts createAccounts(Accounts il) {
        Accounts l= new Accounts(il);
        accounts.add(l);
        return(l);
    }

}
