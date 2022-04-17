package edu.iit.cs445.spring22;

import java.util.List;


public interface BoundaryInterface {
	
	//ACCOUNTS
    List<Accounts> getAllAccounts();
    Accounts createAccounts(Accounts il);
	void replaceAccount(String lid, Accounts il);
	Accounts getAccountDetail(String lid);
	boolean isChangingActiveStatus();
	Accounts activateAccountDetail(String lid);
	
	//ASKS
	Asks createAsks(Asks il);
}
