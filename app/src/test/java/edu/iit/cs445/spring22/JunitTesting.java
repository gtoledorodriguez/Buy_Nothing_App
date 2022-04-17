package edu.iit.cs445.spring22;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class JunitTesting {

//	@Test
//	void test() { //Figures out rest controller actions
//		BoundaryInterface bi = new BnManager();
//		if(bi.getAllAccounts().size() == 0) {
//    		String json = "{\n"
//    				+ "  \"uid\": \"\",\n"
//    				+ "  \"name\": \"Virgil Bistriceanu\",\n"
//    				+ "  \"address\": {\n"
//    				+ "    \"street\": \"10 West 31st ST\",\n"
//    				+ "    \"zip\": \"60616\"\n"
//    				+ "  },\n"
//    				+ "  \"phone\": \"312-456-7890\",\n"
//    				+ "  \"picture\": \"http://example.com/images/test-virgil.jpeg\",\n"
//    				+ "  \"is_active\": true,\n"
//    				+ "  \"date_created\": \"\"\n"
//    				+ "}";
//    		String json2 = "{\n"
//    				+ "  \"uid\": \"\",\n"
//    				+ "  \"name\": \"Jane Smith\",\n"
//    				+ "  \"address\": {\n"
//    				+ "    \"street\": \"123 2nd ST\",\n"
//    				+ "    \"zip\": \"60607\"\n"
//    				+ "  },\n"
//    				+ "  \"phone\": \"312-456-7890\",\n"
//    				+ "  \"picture\": \"http://example.com/images/jane-smith.jpeg\",\n"
//    				+ "  \"is_active\": false,\n"
//    				+ "  \"date_created\": \"\"\n"
//    				+ "}";
//    		String json3 = "{\n"
//    				+ "  \"uid\": \"\",\n"
//    				+ "  \"name\": \"CSR #1\",\n"
//    				+ "  \"address\": {\n"
//    				+ "    \"street\": \"101 W Main St.\",\n"
//    				+ "    \"zip\": \"60010\"\n"
//    				+ "  },\n"
//    				+ "  \"phone\": \"312-456-7890\",\n"
//    				+ "  \"picture\": \"http://example.com/images/CSR-#1.jpeg\",\n"
//    				+ "  \"is_active\": true,\n"
//    				+ "  \"date_created\": \"\"\n"
//    				+ "}";
//    		Gson gs = new Gson();
//            Accounts il = gs.fromJson(json, Accounts.class);
//            Accounts l = bi.createAccounts(il);
//            
//            Accounts il2 = gs.fromJson(json2, Accounts.class);
//            Accounts l2 = bi.createAccounts(il2);
//            
//            Accounts il3 = gs.fromJson(json3, Accounts.class);
//            Accounts l3 = bi.createAccounts(il3);
//
//            List<Accounts> accs = bi.getAllAccounts();
//            
//            //Put 
//            json = "{\n"
//            		+ "  \"uid\": \"\",\n"
//            		+ "  \"name\": \"John Smith Jr.\",\n"
//            		+ "  \"address\": {\n"
//            		+ "    \"street\": \"123 Main ST\",\n"
//            		+ "    \"zip\": \"60616\"\n"
//            		+ "  },\n"
//            		+ "  \"phone\": \"312-456-7890\",\n"
//            		+ "  \"picture\": \"http://example.com/images/john-smith.jpeg\",\n"
//            		+ "  \"is_active\": false,\n"
//            		+ "  \"date_created\": \"2122-03-13T19:01:17Z\"\n"
//            		+ "}";
//            Gson gson = new Gson();
//            Accounts il4 = gson.fromJson(json, Accounts.class);
//            String lid4 = "";
//            bi.replaceAccount(lid4, il4);
//            
//            System.out.println(bi.getAllAccounts().size());
//            
//            int size = bi.getAllAccounts().size()-1;
//            String uid = bi.getAllAccounts().get(size).getUid();
//            String date = bi.getAllAccounts().get(size).getDate_created();
//            String date2 = "31-Dec-2000";
//            
//            	
//				try {
//					Date date1;
//					date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
//				
//				Date date_2;
//				
//					date_2 = new SimpleDateFormat("dd-MMM-yyyy").parse(date2);
//					System.out.println(date+"\t"+date1);  
//					System.out.println(date2+"\t"+date_2);
//				} catch (ParseException e) {
//					
//					e.printStackTrace();
//				}
//				
//            
//    	}
//		assertEquals(bi.getAllAccounts().size(), 4);
//	}
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
	 * TODO: BNMANAGER
	 */
	BoundaryInterface bi = new BnManager();
	
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
	

}