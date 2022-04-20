package edu.iit.cs445.spring22;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.text.Position.Bias;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class JunitTesting {
	/*
	 * TODO: ACCOUNTS
	 */
	@Test
	void createAddressNoParamsTestStreet() {
		Address add = new Address();
		add.setStreet("10 Main St");
		add.setzip("60613");
		assertEquals(add.getStreet(), "10 Main St");
	}
	
	@Test
	void createAddressNoParamsTestZip() {
		Address add = new Address();
		add.setStreet("10 Main St");
		add.setzip("60613");
		assertEquals(add.getZip(), "60613");
	}
	
	@Test
	void createAddressWithParamsTestStreet() {
		Address add = new Address("10 Main St","60613");
		assertEquals(add.getStreet(), "10 Main St");
	}
	
	@Test
	void createAddressWithParamsTestZip() {
		Address add = new Address("10 Main St","60613");
		assertEquals(add.getZip(), "60613");
	}
	
	@Test
	void createAccountsNoParamsTestNil() {
		Accounts a = new Accounts();
		
		assertEquals(a.isNil(), false);
	}
	@Test
	void createAccountsNoParamsTestUID() {
		Accounts a = new Accounts();
		a.setName("Harry Potter");
		a.setAddress(new Address("Hogwarts", "60651"));
		a.setisActive(false);
		a.setPhone("773-123-4567");
		a.setPicture("image.jpeg");
		assertEquals(a.getUid(), a.getUid());
	}
	@Test
	void createAccountsNoParamsTestName() {
		Accounts a = new Accounts();
		a.setName("Harry Potter");
		a.setAddress(new Address("Hogwarts", "60651"));
		a.setisActive(false);
		a.setPhone("773-123-4567");
		a.setPicture("image.jpeg");
		assertEquals(a.getName(), "Harry Potter");
	}
	@Test
	void createAccountsNoParamsTestAddress() {
		Accounts a = new Accounts();
		a.setName("Harry Potter");
		a.setAddress(new Address("Hogwarts", "60651"));
		a.setisActive(false);
		a.setPhone("773-123-4567");
		a.setPicture("image.jpeg");
		assertEquals(a.getAddress().getZip(), "60651");
	}
	
	@Test
	void createAccountsNoParamsTestisActiveFalse() {
		Accounts a = new Accounts();
		a.setName("Harry Potter");
		a.setAddress(new Address("Hogwarts", "60651"));
		a.setisActive(false);
		a.setPhone("773-123-4567");
		a.setPicture("image.jpeg");
		assertEquals(a.getIsActive(), false);
	}
	@Test
	void createAccountsNoParamsTestisActiveTrue() {
		Accounts a = new Accounts();
		a.setName("Harry Potter");
		a.setAddress(new Address("Hogwarts", "60651"));
		a.setisActive(false);
		a.setPhone("773-123-4567");
		a.setPicture("image.jpeg");
		a.activate();
		assertEquals(a.getIsActive(), true);
	}
	@Test
	void createAccountsNoParamsTestisActiveSetFalse() {
		Accounts a = new Accounts();
		a.setName("Harry Potter");
		a.setAddress(new Address("Hogwarts", "60651"));
		a.setisActive(false);
		a.setPhone("773-123-4567");
		a.setPicture("image.jpeg");
		a.activate();
		a.isNotActive();
		assertEquals(a.getIsActive(), false);
	}
	@Test
	void createAccountsNoParamsTestPhone() {
		Accounts a = new Accounts();
		a.setName("Harry Potter");
		a.setAddress(new Address("Hogwarts", "60651"));
		a.setisActive(false);
		a.setPhone("773-123-4567");
		a.setPicture("image.jpeg");
		a.activate();
		assertEquals(a.getPhone(), "773-123-4567");
	}
	@Test
	void createAccountsNoParamsTestPicture() {
		Accounts a = new Accounts();
		a.setName("Harry Potter");
		a.setAddress(new Address("Hogwarts", "60651"));
		a.setisActive(false);
		a.setPhone("773-123-4567");
		a.setPicture("image.jpeg");
		a.activate();
		assertEquals(a.getPicture(), "image.jpeg");
	}
	@Test
	void createAccountsNoParamsTestDate() {
		Accounts a = new Accounts();
		a.setName("Harry Potter");
		a.setAddress(new Address("Hogwarts", "60651"));
		a.setisActive(false);
		a.setPhone("773-123-4567");
		a.setPicture("image.jpeg");
		a.activate();
		assertEquals(a.getDate_created(), a.getDate_created());
	}
	@Test
	void createNullAccount() {
		Accounts a = new NullAccount();
		
		assertEquals(a.isNil(), true);
	}
	@Test
	void createAccountsParamsTestMatches() {
		Accounts a = new Accounts();
		a.setName("Harry Potter");
		a.setAddress(new Address("Hogwarts", "60651"));
		a.setisActive(false);
		a.setPhone("773-123-4567");
		a.setPicture("image.jpeg");
		a.activate();

		Accounts a2 = new Accounts(a);

		assertEquals(a2.matchesUid(a2.getUid()), true);
	}
	@Test
	void createAccountsParamsTestMatchesAndisActiveFalse() {
		Accounts a = new Accounts();
		a.setName("Harry Potter");
		a.setAddress(new Address("Hogwarts", "60651"));
		a.setisActive(false);
		a.setPhone("773-123-4567");
		a.setPicture("image.jpeg");
		Accounts a2 = new Accounts(a);
		assertEquals(a2.matchesUid(a2.getUid()), true);
	}
	/*
	 * TODO: ASKS
	 */
	
	@Test
	void testNullAsk() {
		Asks a = new NullAsk();
		Asks a2 = new Asks(a);
		assertEquals(a2.isNil(), false);
	}
	
	@Test
	void testSetterAsk() {
		Asks a = new NullAsk();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIsNil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.isNil(), true);
	}
	@Test
	void testGetterDescriptionAsk() {
		Asks a = new NullAsk();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIsNil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.getDescription(), "Test");
	}
	@Test
	void testGetterEndAsk() {
		Asks a = new NullAsk();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIsNil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.getEnd_date(), "4/20/2022");
	}
	@Test
	void testGetterZipAsk() {
		Asks a = new NullAsk();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIsNil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.getExtra_zip().length, 0);
	}
	@Test
	void testGetterActiveAsk() {
		Asks a = new NullAsk();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIsNil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.isIs_active(), false);
	}
	@Test
	void testGetterStartAsk() {
		Asks a = new NullAsk();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIsNil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.getStart_date(), "4/19/2022");
	}
	@Test
	void testGetterTypeAsk() {
		Asks a = new NullAsk();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIsNil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.getType(), "gift");
	}
	@Test
	void testGetterUidAsk() {
		Asks a = new NullAsk();
		a.getUid();
		assertEquals(a.getUid(), "");
	}
	@Test
	void testGetterAidAsk() {
		Asks a = new NullAsk();
		a.getAid();
		assertEquals(a.getAid(), a.getAid());
	}
	@Test
	void testGetterCreateAsk() {
		Asks a = new NullAsk();
		a.createDate();
		a.getDate_created();
		assertEquals(a.getDate_created(), a.getDate_created());
	}
	@Test
	void testMatchesAidAsk() {
		Asks a = new NullAsk();
		boolean b = a.matchesAid("");
		assertEquals(b, false);
	}

	/*
	 * TODO: GIVES
	 */
	
	@Test
	void testNullGives() {
		Gives a = new NullGive();
		Gives a2 = new Gives(a);
		assertEquals(a2.isIs_Nil(), false);
	}
	
	@Test
	void testSetterGives() {
		Gives a = new NullGive();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIs_Nil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.isIs_Nil(), true);
	}
	@Test
	void testGetterDescriptionGives() {
		Gives a = new NullGive();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIs_Nil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.getDescription(), "Test");
	}
	@Test
	void testGetterEndGives() {
		Gives a = new NullGive();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIs_Nil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.getEnd_date(), "4/20/2022");
	}
	@Test
	void testGetterZipGives() {
		Gives a = new NullGive();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIs_Nil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.getExtra_zip().length, 0);
	}
	@Test
	void testGetterActiveGives() {
		Gives a = new NullGive();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIs_Nil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.isIs_active(), false);
	}
	@Test
	void testGetterStartGives() {
		Gives a = new NullGive();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIs_Nil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.getStart_date(), "4/19/2022");
	}
	@Test
	void testGetterTypeGives() {
		Gives a = new NullGive();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIs_Nil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		assertEquals(a.getType(), "gift");
	}
	@Test
	void testGetterUidGives() {
		Gives a = new NullGive();
		a.getUid();
		assertEquals(a.getUid(), "");
	}
	@Test
	void testGetterGidGives() {
		Gives a = new NullGive();
		a.getGid();
		assertEquals(a.getGid(), a.getGid());
	}
	@Test
	void testGetterCreateGives() {
		Gives a = new NullGive();
		a.createDate();
		a.getDate_created();
		assertEquals(a.getDate_created(), a.getDate_created());
	}
	@Test
	void testMatchesGidGives() {
		Gives a = new NullGive();
		boolean b = a.matchesGid("");
		assertEquals(b, false);
	}
	
	/*
	 * TODO: BNMANAGER - Accounts
	 */
	BoundaryInterface bi = new BnManager();
	
	@Test
	void GETAllAccounts() {
		BoundaryInterface b = new BnManager();
		List<Accounts> al = b.getAllAccounts();
		assertEquals(al.size(),al.size());
	}
	@Test
	void GETAllAccountsNotEmpty() {
		BoundaryInterface b2 = new BnManager();
		//b.createAccounts(new Accounts());
		List<Accounts> al = b2.getAllAccounts();
		assertEquals(al.size(),al.size());
	}
	@Test
	void GETPreloadData() {
		BoundaryInterface b3 = new BnManager();
		b3.preLoadAccountsList();
		int size = b3.getAllAccounts().size();
		
		assertEquals(size,size);
	}
	
	@Test
	void GETAllAccountsNoParamsEmptyList() {
		if(bi.getAllAccounts().size() == 0) {
			String json = "{\n"
					+ "  \"uid\": \"\",\n"
					+ "  \"name\": \"Virgil Bistriceanu\",\n"
					+ "  \"address\": {\n"
					+ "    \"street\": \"10 West 31st ST\",\n"
					+ "    \"zip\": \"60616\"\n"
					+ "  },\n"
					+ "  \"phone\": \"312-456-7890\",\n"
					+ "  \"picture\": \"http://example.com/images/test-virgil.jpeg\",\n"
					+ "  \"is_active\": true,\n"
					+ "  \"date_created\": \"\"\n"
					+ "}";
			String json2 = "{\n"
					+ "  \"uid\": \"\",\n"
					+ "  \"name\": \"Jane Smith\",\n"
					+ "  \"address\": {\n"
					+ "    \"street\": \"123 2nd ST\",\n"
					+ "    \"zip\": \"60607\"\n"
					+ "  },\n"
					+ "  \"phone\": \"312-456-7890\",\n"
					+ "  \"picture\": \"http://example.com/images/jane-smith.jpeg\",\n"
					+ "  \"is_active\": false,\n"
					+ "  \"date_created\": \"\"\n"
					+ "}";
			String json3 = "{\n"
					+ "  \"uid\": \"\",\n"
					+ "  \"name\": \"CSR #1\",\n"
					+ "  \"address\": {\n"
					+ "    \"street\": \"101 W Main St.\",\n"
					+ "    \"zip\": \"60010\"\n"
					+ "  },\n"
					+ "  \"phone\": \"312-456-7890\",\n"
					+ "  \"picture\": \"http://example.com/images/test-virgil.jpeg\",\n"
					+ "  \"is_active\": true,\n"
					+ "  \"date_created\": \"\"\n"
					+ "}";
			String id;
			Gson gs = new Gson();
	        Accounts il = gs.fromJson(json, Accounts.class);
	        Accounts l = bi.createAccounts(il);
	        
	        Accounts il2 = gs.fromJson(json2, Accounts.class);
	        Accounts l2 = bi.createAccounts(il2);
	        
	        Accounts il3 = gs.fromJson(json3, Accounts.class);
	        Accounts l3 = bi.createAccounts(il3);
	        
	        assertEquals(bi.getAllAccounts().size(),3);
		}
	}
	
	@Test 
	void POSTAccount(){
		String json = "{\n"
				+ "  \"uid\": \"\",\n"
				+ "  \"name\": \"John Smith\",\n"
				+ "  \"address\": {\n"
				+ "    \"street\": \"123 Main ST\",\n"
				+ "    \"zip\": \"60616\"\n"
				+ "  },\n"
				+ "  \"phone\": \"312-456-7890\",\n"
				+ "  \"picture\": \"http://example.com/images/john-smith.jpeg\",\n"
				+ "  \"is_active\": false,\n"
				+ "  \"date_created\": \"\"\n"
				+ "}";
		Gson gs = new Gson();
		Accounts il = gs.fromJson(json, Accounts.class);
        bi.createAccounts(il);
        assertEquals(bi.getAllAccounts().size(),bi.getAllAccounts().size());
	}
	
	@Test 
	void PUTChangeAccountChangineActiveStatus(){
		String json = "{\n"
				+ "  \"uid\": \"\",\n"
				+ "  \"name\": \"John Smith\",\n"
				+ "  \"address\": {\n"
				+ "    \"street\": \"123 Main ST\",\n"
				+ "    \"zip\": \"60616\"\n"
				+ "  },\n"
				+ "  \"phone\": \"312-456-7890\",\n"
				+ "  \"picture\": \"http://example.com/images/john-smith.jpeg\",\n"
				+ "  \"is_active\": true,\n"
				+ "  \"date_created\": \"\"\n"
				+ "}";
		int size = bi.getAllAccounts().size()-1;
		String lid = bi.getAllAccounts().get(size).getUid();
		Gson gson = new Gson();
		Accounts il = gson.fromJson(json, Accounts.class);
		bi.replaceAccount(lid, il);
        assertEquals(bi.isChangingActiveStatus(),true);
	}
	
	@Test 
	void PUTChangeAccountNotChangineActiveStatus(){
		String json = "{\n"
				+ "  \"uid\": \"\",\n"
				+ "  \"name\": \"John Smith\",\n"
				+ "  \"address\": {\n"
				+ "    \"street\": \"123 Main ST\",\n"
				+ "    \"zip\": \"60616\"\n"
				+ "  },\n"
				+ "  \"phone\": \"312-456-7890\",\n"
				+ "  \"picture\": \"http://example.com/images/john-smith.jpeg\",\n"
				+ "  \"is_active\": false,\n"
				+ "  \"date_created\": \"\"\n"
				+ "}";
		int size = bi.getAllAccounts().size()-1;
		String lid = bi.getAllAccounts().get(size).getUid();
		Gson gson = new Gson();
		Accounts il = gson.fromJson(json, Accounts.class);
		bi.replaceAccount(lid, il);
        assertEquals(bi.isChangingActiveStatus(),false);
	}
	
	@Test 
	void GETSpecificAccountIsNil(){
		Accounts l = bi.getAccountDetail("");
        assertEquals(l.isNil(),true);
	}
	@Test 
	void GETSpecificAccountIsNotNil(){
		int size = bi.getAllAccounts().size()-1;
		//System.out.println("SIZE: "+size);
		String lid = bi.getAllAccounts().get(size).getUid();
		Accounts l = bi.getAccountDetail(lid);
        assertEquals(l.isNil(),false);
	}
	
	@Test 
	void GETActivateAccountDetailIsNotNil(){
		String json = "{\n"
				+ "  \"uid\": \"\",\n"
				+ "  \"name\": \"John Smith\",\n"
				+ "  \"address\": {\n"
				+ "    \"street\": \"123 Main ST\",\n"
				+ "    \"zip\": \"60616\"\n"
				+ "  },\n"
				+ "  \"phone\": \"312-456-7890\",\n"
				+ "  \"picture\": \"http://example.com/images/john-smith.jpeg\",\n"
				+ "  \"is_active\": false,\n"
				+ "  \"date_created\": \"\"\n"
				+ "}";
		Gson gson = new Gson();
		Accounts il = gson.fromJson(json, Accounts.class);
		bi.createAccounts(il);

		int size = bi.getAllAccounts().size()-1;

		String lid = bi.getAllAccounts().get(size).getUid();
		Accounts l = bi.activateAccountDetail(lid);
        assertEquals(l.isNil(),false);
	}
	@Test 
	void GETActivateAccountDetailIsNil(){
		//System.out.println("THERE");
		Accounts l = bi.activateAccountDetail("");
        assertEquals(l.isNil(),true);
	}
	@Test 
	void searchAccountsNoParams(){
		List<Accounts> acc = bi.searchAccounts(null, null, null);
        assertEquals(acc.size(),0);
	}
	@Test 
	void searchAccountsParamsZip(){
		List<Accounts> acc = bi.searchAccounts("70616", null, null);
        assertEquals(acc.size(),0);
	}
	@Test 
	void searchAccountsParamsZipStartEnd(){
		List<Accounts> acc = bi.searchAccounts("60616", "31-Dec-2000", "31-Dec-2001");
        assertEquals(acc.size(),0);
	}
	@Test 
	void searchAccountsParamsStartEnd(){
		List<Accounts> acc = bi.searchAccounts(null, "31-Dec-2000", "31-Dec-2022");
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAccountsParamsStreetStartEnd(){
		List<Accounts> acc = bi.searchAccounts("Main", "31-Dec-2000", "31-Dec-2022");
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAccountsParamsStreet(){
		List<Accounts> acc = bi.searchAccounts("Main", null, null);
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAccountsParamsNameStartEnd(){
		List<Accounts> acc = bi.searchAccounts("John", "31-Dec-2000", "31-Dec-2022");
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAccountsName(){
		List<Accounts> acc = bi.searchAccounts("John", null, null);
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAccountsParamsPhoneStartEnd(){
		List<Accounts> acc = bi.searchAccounts("312", "31-Dec-2000", "31-Dec-2022");
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAccountsPhone(){
		List<Accounts> acc = bi.searchAccounts("312", null, null);
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAccountsParamsPicStartEnd(){
		List<Accounts> acc = bi.searchAccounts("jpeg", "31-Dec-2000", "31-Dec-2022");
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAccountsPic(){
		List<Accounts> acc = bi.searchAccounts("jpeg", null, null);
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void DELETEAccount(){
		Accounts a = bi.createAccounts(new Accounts());
		int size = bi.getAllAccounts().size();
		String uid = a.getUid();
		bi.deleteAccount(uid);
		
		assertEquals(bi.getAllAccounts().size(),size-1);
	}
	
	/*
	 * TODO: BNMANAGER - Asks
	 */
	@Test 
	void GETAllAsks(){
		List<Asks> aList= bi.getAllAsks();
		assertEquals(aList.size(),aList.size());
	}
	
	@Test 
	void inAsksListAsks(){
		bi.setInAsksList(false);
		assertEquals(bi.isInAsksList(),false);
	}
	
	@Test 
	void inChangeAsksActiveAsks(){
		bi.setChangingAskActiveStatus(false);
		assertEquals(bi.isChangingAskActiveStatus(),false);
	}
	@Test 
	void specificAsks(){
		Asks a = bi.getAskDetail("");
		assertEquals(a.isNil(),true);
	}
	@Test 
	void deactivateAsks(){
		bi.createAsks(new Asks());
		Asks a = bi.deactivateAskDetail(bi.getAllAsks().get(0).getAid());
		assertEquals(a.isIs_active(),false);
	}
	
	@Test 
	void POSTAsks(){
		Asks a = bi.createAsks(new Asks());
		assertEquals(a.isIs_active(),true);
	}
	
	@Test 
	void DELETEAsks(){
		Asks a = bi.createAsks(new Asks());
		int size = bi.getAllAsks().size();
		String aid = a.getAid();
		bi.deleteAsk(aid);
		
		assertEquals(bi.getAllAsks().size(),size-1);
	}
	@Test 
	void ReplaceAsks(){
		Asks a2 = bi.createAsks(new Asks());

		Asks a = new NullAsk();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIsNil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		
		bi.replaceAsk(a2.getAid(), a);
		assertEquals(a2.getDescription(),"Test");
	}
	@Test 
	void SearchUidActiveTrueAsks(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"7\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
        Asks il = gs.fromJson(json, Asks.class);
		Asks a2 = bi.createAsks(il);
		bi.searchAsksByUidAndActiveStatus(a2.getUid(), "true");
		assertEquals(a2.getUid(),"7");
	}

	@Test 
	void SearchUidActiveFalseAsks(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"8\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
        Asks il = gs.fromJson(json, Asks.class);
		Asks a2 = bi.createAsks(il);
		bi.searchAsksByUidAndActiveStatus(a2.getUid(), "false");
		assertEquals(a2.getUid(),"8");
	}

	@Test 
	void SearchUidActiveNullAsks(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"9\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
        Asks il = gs.fromJson(json, Asks.class);
		Asks a2 = bi.createAsks(il);
		bi.searchAsksByUidAndActiveStatus(a2.getUid(), null);
		assertEquals(a2.getUid(),"9");
	}
	
	@Test 
	void ViewUidActiveTrueAsks(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"10\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
        Asks il = gs.fromJson(json, Asks.class);
		Asks a2 = bi.createAsks(il);
		bi.searchAsksByUidAndActiveStatusAndZipCodes(bi.getAllAccounts().get(0).getUid(), "true");
		assertEquals(a2.getUid(),"10");
	}

	@Test 
	void ViewUidActiveFalseAsks(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"11\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
        Asks il = gs.fromJson(json, Asks.class);
		Asks a2 = bi.createAsks(il);
		bi.searchAsksByUidAndActiveStatusAndZipCodes(bi.getAllAccounts().get(0).getUid(), "false");
		assertEquals(a2.getUid(),"11");
	}
	@Test 
	void ViewUidActiveNullAsks(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"11\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
        Asks il = gs.fromJson(json, Asks.class);
		Asks a2 = bi.createAsks(il);
		bi.searchAsksByUidAndActiveStatusAndZipCodes(bi.getAllAccounts().get(0).getUid(), "");
		assertEquals(a2.getUid(),"11");
	}
	
	@Test 
	void lengthOfExtraZip(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"11\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
        Asks il = gs.fromJson(json, Asks.class);
		Asks a2 = bi.createAsks(il);
		System.out.println("ZIP: ");
		System.out.println(a2.getExtra_zip().length!=0);
		assertEquals(a2.getUid(),"11");
	}
	
	@Test 
	void ViewCSRUidActiveTrueAsks(){
		bi.getAllAccounts();
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"23\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
        Asks il = gs.fromJson(json, Asks.class);
		Asks a2 = bi.createAsks(il);
		bi.searchAsksByUidAndActiveStatusAndZipCodes(bi.getAllAccounts().get(2).getUid(), "true");
		assertEquals(a2.getUid(),a2.getUid());
	}

	@Test 
	void ViewCSRUidActiveFalseAsks(){
		bi.getAllAccounts();
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"24\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
        Asks il = gs.fromJson(json, Asks.class);
		Asks a2 = bi.createAsks(il);
		bi.searchAsksByUidAndActiveStatusAndZipCodes(bi.getAllAccounts().get(2).getUid(), "false");
		assertEquals(a2.getUid(),a2.getUid());
	}
	@Test 
	void ViewCSRUidActiveNullAsks(){
		bi.getAllAccounts();
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"25\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
        Asks il = gs.fromJson(json, Asks.class);
		Asks a2 = bi.createAsks(il);
		bi.searchAsksByUidAndActiveStatusAndZipCodes(bi.getAllAccounts().get(2).getUid(), "");
		assertEquals(a2.getUid(),a2.getUid());
	}
	
	//TODO: Search Asks
	String askJson = "{\n"
			+ "	\"uid\": \""+bi.getAllAccounts().get(0).getUid() +"\",\n"
			+ "	\"aid\": \"\",\n"
			+ "  \"type\": \"gift\",\n"
			+ "  \"description\": \"I need a twin bed frame with a spring box.\",\n"
			+ "  \"start_date\": \"2022-03-14\",\n"
			+ "  \"end_date\": \"\",\n"
			+ "  \"extra_zip\": [\"60607\", \"60608\"],\n"
			+ "  \"is_active\": true,\n"
			+ "  \"date_created\": \"\"\n"
			+ "}";
	Gson gsAsk = new Gson();
	Asks ilAsk = gsAsk.fromJson(askJson, Asks.class);
	Asks lAsk = bi.createAsks(ilAsk);
	String giveJson = "{\n"
			+ "	\"uid\": \""+bi.getAllAccounts().get(0).getUid() +"\",\n"
			+ "	\"gid\": \"\",\n"
			+ "	\"type\": \"gift\",\n"
			+ "	\"description\": \"Are you interested in a \", \n"
			+ "	\"start_date\": \"2022-03-15\",\n"
			+ "	\"end_date\": \"\",\n"
			+ "	\"extra_zip\": [\"60607\", \"60608\"],\n"
			+ "	\"is_active\": true,\n"
			+ "	\"date_created\": \"\"\n"
			+ "}";
	Gson gsGive = new Gson();
	Gives ilGive = gsGive.fromJson(giveJson, Gives.class);
    Gives lGive = bi.createGives(ilGive);
    
  
	@Test 
	void searchAsksParamsZip(){
		List<Asks> acc = bi.searchAsks("70616", null, null);
        assertEquals(acc.size(),0);
	}
	@Test 
	void searchAsksParamsZipStartEnd(){
		List<Asks> acc = bi.searchAsks("60607", "31-Dec-2000", "31-Dec-2001");
        assertEquals(acc.size(),0);
	}
	@Test 
	void searchAsksParamsStartEnd(){
		
		List<Asks> acc = bi.searchAsks(null, "31-Dec-2000", "31-Dec-2022");
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAsksParamsTypeStartEnd(){
		List<Asks> acc = bi.searchAsks("gift", "31-Dec-2000", "31-Dec-2022");
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAsksParamsType(){
		List<Asks> acc = bi.searchAsks("gift", null, null);
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAsksParamsDesStartEnd(){
		List<Asks> acc = bi.searchAsks("twin", "31-Dec-2000", "31-Dec-2022");
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAsksDes(){
		List<Asks> acc = bi.searchAsks("twin", null, null);
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAsksParamsstartStartEnd(){
		List<Asks> acc = bi.searchAsks("2022", "31-Dec-2000", "31-Dec-2022");
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAsksstart(){
		List<Asks> acc = bi.searchAsks("2022", null, null);
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAsksParamsendStartEnd(){
		List<Asks> acc = bi.searchAsks("", "31-Dec-2000", "31-Dec-2022");
        assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchAsksend(){
		List<Asks> acc = bi.searchAsks("", null, null);
        assertEquals(acc.size(),acc.size());
	}
	
	
	/*
	 * TODO: BNMANAGER - GIVES
	 */
	@Test 
	void GETAllGives(){
		List<Gives> aList= bi.getAllGives();
		assertEquals(aList.size(),aList.size());
	}
	
	@Test 
	void inGivesListGives(){
		bi.setInGivesList(false);
		assertEquals(bi.isInGivesList(),false);
	}
	
	@Test 
	void inChangeAsksActiveGives(){
		bi.setChangingGiveActiveStatus(false);
		assertEquals(bi.isChangingGiveActiveStatus(),false);
	}
	@Test 
	void specificGives(){
		Gives a = bi.getGivesDetail("");
		assertEquals(a.isIs_Nil(),true);
	}
	@Test 
	void deactivateGives(){
		bi.createGives(new Gives());
		Gives a = bi.deactivateGivesDetail(bi.getAllGives().get(0).getGid());
		assertEquals(a.isIs_active(),false);
	}
	
	@Test 
	void POSTGives(){
		Gives a = bi.createGives(new Gives());
		assertEquals(a.isIs_active(),true);
	}
	
	@Test 
	void DELETEGives(){
		Gives a = bi.createGives(new Gives());
		int size = bi.getAllGives().size();
		String gid = a.getGid();
		bi.deleteGive(gid);
		
		assertEquals(bi.getAllGives().size(),size-1);
	}
	@Test 
	void ReplaceGives(){
		Gives a2 = bi.createGives(new Gives());

		Gives a = new NullGive();
		a.setDescription("Test");
		a.setEnd_date("4/20/2022");
		a.setExtra_zip(new String[0]);
		a.setIs_active(false);
		a.setIs_Nil(true);
		a.setStart_date("4/19/2022");
		a.setType("gift");
		
		bi.replaceGive(a2.getGid(), a);
		assertEquals(a2.getDescription(),"Test");
	}
	@Test 
	void SearchUidActiveTrueGive(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"7\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
		Gives il = gs.fromJson(json, Gives.class);
		Gives a2 = bi.createGives(il);
		bi.searchAsksByUidAndActiveStatus(a2.getUid(), "true");
		assertEquals(a2.getUid(),"7");
	}

	@Test 
	void SearchUidActiveFalseGives(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"8\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
		Gives il = gs.fromJson(json, Gives.class);
		Gives a2 = bi.createGives(il);
		bi.searchGivesByUidAndActiveStatus(a2.getUid(), "false");
		assertEquals(a2.getUid(),"8");
	}

	@Test 
	void SearchUidActiveNullGives(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"9\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
		Gives il = gs.fromJson(json, Gives.class);
		Gives a2 = bi.createGives(il);
		bi.searchGivesByUidAndActiveStatus(a2.getUid(), null);
		assertEquals(a2.getUid(),"9");
	}
	
	@Test 
	void ViewUidActiveTrueGives(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"10\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
		Gives il = gs.fromJson(json, Gives.class);
		Gives a2 = bi.createGives(il);
		bi.searchGivesByUidAndActiveStatusAndZipCodes(bi.getAllAccounts().get(0).getUid(), "true");
		assertEquals(a2.getUid(),"10");
	}

	@Test 
	void ViewUidActiveFalseGives(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"11\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
		Gives il = gs.fromJson(json, Gives.class);
		Gives a2 = bi.createGives(il);
		bi.searchGivesByUidAndActiveStatusAndZipCodes(bi.getAllAccounts().get(0).getUid(), "false");
		assertEquals(a2.getUid(),"11");
	}
	@Test 
	void ViewUidActiveNullGives(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"9\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
		Gives il = gs.fromJson(json, Gives.class);
		Gives a2 = bi.createGives(il);
		bi.searchGivesByUidAndActiveStatusAndZipCodes(bi.getAllAccounts().get(0).getUid(), "");
		assertEquals(a2.getUid(),"9");
	}
	
	@Test 
	void lengthOfExtraZipGives(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"11\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
		Gives il = gs.fromJson(json, Gives.class);
		Gives a2 = bi.createGives(il);
		System.out.println("ZIP: ");
		System.out.println(a2.getExtra_zip().length!=0);
		assertEquals(a2.getUid(),"11");
	}
	
	@Test 
	void ViewCSRUidActiveTrueGives(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"20\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
		Gives il = gs.fromJson(json, Gives.class);
		Gives a2 = bi.createGives(il);
		bi.searchGivesByUidAndActiveStatusAndZipCodes(bi.getAllAccounts().get(2).getUid(), "true");
		assertEquals(a2.getUid(),a2.getUid());
	}

	@Test 
	void ViewCSRUidActiveFalseGives(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"21\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
		Gives il = gs.fromJson(json, Gives.class);
		Gives a2 = bi.createGives(il);
		bi.searchGivesByUidAndActiveStatusAndZipCodes(bi.getAllAccounts().get(2).getUid(), "false");
		assertEquals(a2.getUid(),a2.getUid());
	}
	@Test 
	void ViewCSRUidActiveNullGives(){
		Gson gs = new Gson();
		String json = "{\n"
				+ "      	\"uid\": \"22\",\n"
				+ "        \"aid\": \"\",\n"
				+ "      	\"type\": \"gift\",\n"
				+ "        \"description\": \"I need a twin bed frame with a spring box.\",\n"
				+ "        \"start_date\": \"2022-03-14\",\n"
				+ "        \"end_date\": \"\",\n"
				+ "        \"extra_zip\": [\"60607\", \"60608\"],\n"
				+ "      	\"is_active\": true,\n"
				+ "        \"date_created\": \"\"\n"
				+ "      }";
		Gives il = gs.fromJson(json, Gives.class);
		Gives a2 = bi.createGives(il);
		bi.searchGivesByUidAndActiveStatusAndZipCodes(bi.getAllAccounts().get(2).getUid(), "");
		assertEquals(a2.getUid(),a2.getUid());
	}
	
	//TODO: Search Gives
	@Test 
	void searchGivesParamsZip(){
		List<Gives> acc = bi.searchGives("70616", null, null);
		assertEquals(acc.size(),0);
	}
	@Test 
	void searchGivesParamsZipStartEnd(){
		List<Gives> acc = bi.searchGives("60607", "31-Dec-2000", "31-Dec-2001");
		assertEquals(acc.size(),0);
	}
	@Test 
	void searchGivesParamsStartEnd(){
		List<Gives> acc = bi.searchGives(null, "31-Dec-2000", "31-Dec-2022");
		assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchGivesParamsTypeStartEnd(){
		List<Gives> acc = bi.searchGives("gift", "31-Dec-2000", "31-Dec-2022");
		assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchGivesParamsType(){
		List<Gives> acc = bi.searchGives("gift", null, null);
		assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchGivesParamsDesStartEnd(){
		List<Gives> acc = bi.searchGives("interested", "31-Dec-2000", "31-Dec-2022");
		assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchGivesDes(){
		List<Gives> acc = bi.searchGives("interested", null, null);
		assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchGivesParamsstartStartEnd(){
		List<Gives> acc = bi.searchGives("2022", "31-Dec-2000", "31-Dec-2022");
		assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchGivesstart(){
		List<Gives> acc = bi.searchGives("2022", null, null);
		assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchGivesParamsendStartEnd(){
		List<Gives> acc = bi.searchGives("", "31-Dec-2000", "31-Dec-2022");
		assertEquals(acc.size(),acc.size());
	}
	@Test 
	void searchGivesend(){
		List<Gives> acc = bi.searchGives("", null, null);
		assertEquals(acc.size(),acc.size());
	}

}
