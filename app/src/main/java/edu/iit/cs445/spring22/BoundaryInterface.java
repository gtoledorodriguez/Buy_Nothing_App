package edu.iit.cs445.spring22;

import java.util.List;


public interface BoundaryInterface {
	
	//TODO: ACCOUNTS
    List<Accounts> getAllAccounts();
    Accounts createAccounts(Accounts il);
	void replaceAccount(String lid, Accounts il);
	Accounts getAccountDetail(String lid);
	boolean isChangingActiveStatus();
	Accounts activateAccountDetail(String lid);
	List<Accounts> searchAccounts(String key, String start_date, String end_date);
	void preLoadAccountsList();
	
	//TODO: ASKS
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
	List<Asks> searchAsksByUidAndActiveStatusAndZipCodes(String lid, String b);
	void deleteAsk(String lid);
	List<Asks> searchAsks(String key, String start_date, String end_date);
	
	//TODO: GIVES
	Gives createGives(Gives il);
	void replaceGive(String gid, Gives il);
	boolean isChangingGiveActiveStatus();
	void setChangingGiveActiveStatus(boolean changingGiveActiveStatus) ;
	boolean isInGivesList();
	void setInGivesList(boolean inGivesList);
	Gives getGivesDetail(String lid);
	Gives deactivateGivesDetail(String gid);
	List<Gives> getAllGives();
	List<Gives> searchGivesByUidAndActiveStatus(String lid, String is_active);
	List<Gives> searchGivesByUidAndActiveStatusAndZipCodes(String lid, String is_active);
	void deleteGive(String gid);
	void deleteAccount(String uid);
	List<Gives> searchGives(String key, String start_date, String end_date);
	
	//TODO: THANKS
	Thanks createThanks(Thanks il);
	void replaceThanks(String tid, Thanks il);
	boolean isInThanksList();
	void setInThanksList(boolean inThanksList);
	Thanks getThanksDetail(String lid);
	List<Thanks> getAllThanks();
	List<Thanks> searchThanks(String key, String start_date, String end_date);
	List<Thanks> searchThanksByUid(String lid);
	List<Thanks> searchThanksByThankTo(String lid);
}
