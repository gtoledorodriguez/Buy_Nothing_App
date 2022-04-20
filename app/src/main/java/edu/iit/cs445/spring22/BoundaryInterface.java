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
	List<Accounts> searchAccounts(String key, String start_date, String end_date);
	void preLoadAccountsList();
	
	//ASKS
	Asks createAsks(Asks il);
	Asks getAskDetail(String lid);
	boolean isChangingAskActiveStatus();
	void setChangingAskActiveStatus(boolean changingAskActiveStatus);
	boolean isInAsksList();
	void setInAsksList(boolean inAsksList);
	void replaceAsk(String aid, Asks il);//String lid, 
	Asks deactivateAskDetail(String aid);
	List<Asks> getAllAsks();
	List<Asks> searchAsksByUidAndActiveStatus(String lid, String b);
	List<Asks> searchAsksByUidAndActiveStatusAndZipCodes(String lid, boolean b);
	void deleteAsk(String lid);
}
