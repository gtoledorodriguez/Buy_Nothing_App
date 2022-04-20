package edu.iit.cs445.spring22;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.gson.Gson;




public class BnManager implements BoundaryInterface {
	private static List<Accounts> accounts = new ArrayList<Accounts>();
	private static List<Asks> asks = new ArrayList<Asks>();
	private boolean inAccountsList = false;
	private boolean inAsksList = false;
	private boolean changingActiveStatus = false;
	private boolean changingAskActiveStatus = false;
	
	//TODO: ACCOUNTS
	public void preLoadAccountsList() {

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
        Accounts l = this.createAccounts(il);
        
        Accounts il2 = gs.fromJson(json2, Accounts.class);
        Accounts l2 = this.createAccounts(il2);
        
        Accounts il3 = gs.fromJson(json3, Accounts.class);
        Accounts l3 = this.createAccounts(il3);
        
        System.out.println(this.getAllAccounts().size());
	}
	public List<Accounts> getAllAccounts() {
		if(accounts.size() == 0) {
			this.preLoadAccountsList();
    	}
		
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
	
	public List<Accounts> searchAccounts(String key, String start_date, String end_date) {
		List<Accounts> searchAccs = new ArrayList<Accounts>();
		List<Accounts> allAccs = this.getAllAccounts();
		
		for(int i = 0; i<allAccs.size();i++) {
			
			Accounts l = allAccs.get(i);
			String name = l.getName().toLowerCase();
			Address address = l.getAddress();
			String phone = l.getPhone().toLowerCase();
			String picture = l.getPicture().toLowerCase();
			String street = address.getStreet().toLowerCase();
			String zip = address.getZip().toLowerCase();
			String date = l.getDate_created();
			if(key!=null) {
				key = key.toLowerCase();
			}
			
			if(start_date!=null && end_date!=null) {
				try {
	            	Date date1= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
					Date startDate= new SimpleDateFormat("dd-MMM-yyyy").parse(start_date);
					Date endDate= new SimpleDateFormat("dd-MMM-yyyy").parse(end_date);
					
					if((!(date1.before(startDate) || date1.after(endDate))) && key!=null) {
						if(name.contains(key) || street.contains(key) || zip.contains(key)|| phone.contains(key)|| picture.contains(key)) {
	        				searchAccs.add(l);
	        			}
					}else if(!(date1.before(startDate) || date1.after(endDate))) {
						searchAccs.add(l);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} 
			}else {
				if(key!=null && (name.contains(key) || street.contains(key) || zip.contains(key)|| phone.contains(key)|| picture.contains(key))) {
    				searchAccs.add(l);
    			}
			}
			
		}
		return searchAccs;
	}

	//TODO: ASKS
	public Asks createAsks(Asks il) {
		Asks l= new Asks(il);
        asks.add(l);
        return(l);
	}
	
	public Asks getAskDetail(String lid) {
		return (findByAid(lid));
	}
	private Asks findByAid(String lid) {
    	//System.out.println(lid);
    	Iterator<Asks> li = asks.listIterator();
        while(li.hasNext()) {
            Asks l = li.next();
            if(l.matchesAid(lid)) {
            	l.setIsNil(false);
            	this.setInAsksList(true);
            	return(l);
            }
        }
        this.setInAsksList(false);
        return (new NullAsk());
	}
	public boolean isChangingAskActiveStatus() {
		return changingAskActiveStatus;
	}
	public void setChangingAskActiveStatus(boolean changingAskActiveStatus) {
		this.changingAskActiveStatus = changingAskActiveStatus;
	}
	
	public void replaceAsk(String aid, Asks il) {//String lid, 
		//Will not active account
    	Asks l = findByAid(aid);
//    	l.setUid(il.getUid());
		l.setType(il.getType()); 
		l.setDescription(il.getDescription());
		l.setStart_date(il.getStart_date());
		l.setEnd_date(il.getEnd_date());
		l.setExtra_zip(il.getExtra_zip());
    	
    	if(il.isIs_active()) {
    		this.setChangingActiveStatus(true);
    	}else {
    		this.setChangingActiveStatus(false);
    	}
    	
    	if(!isInAsksList()) {
    		asks.add(l);
    	}
		
	}
	public boolean isInAsksList() {
		return inAsksList;
	}
	public void setInAsksList(boolean inAsksList) {
		this.inAsksList = inAsksList;
	}

	public Asks deactivateAskDetail(String aid) {
		Asks l = findByAid(aid);
		l.setIs_active(false);
        return l;
	}
	
	public List<Asks> getAllAsks() {
		return asks;
	}

	@Override
	public List<Asks> searchAsksByUidAndActiveStatus(String lid, String b) {
		List<Asks> searchAsks = new ArrayList<Asks>();
		List<Asks> allAsks = this.getAllAsks();
		
		for(int i = 0; i<allAsks.size();i++) {
			Asks l = allAsks.get(i);
			if(b!=null) {
				if(l.getUid().equals(lid) && (l.isIs_active()== Boolean.parseBoolean(b))) {
					searchAsks.add(l);
				}
			}else {
				if(l.getUid().equals(lid)) {
					searchAsks.add(l);
				}
			}
			
		}
		return searchAsks;
	}
	
	public List<Asks> searchAsksByUidAndActiveStatusAndZipCodes(String lid, boolean b) {
		List<Asks> searchAsks = new ArrayList<Asks>();
		List<Asks> allAsks = this.getAllAsks();
		List<Accounts> allAccounts = this.getAllAccounts();
		Accounts userView = new Accounts();
		
		for(int i = 0; i<allAccounts.size();i++) {
			Accounts l = allAccounts.get(i);
			if(l.getUid().equals(lid)) {
				userView = l;
				break;
			}
			//userIDWZipCode.put(l.getUid(), l.getAddress().getZip());
		}
		
		String zip = userView.getAddress().getZip();
		
		for(int i = 0; i<allAsks.size();i++) {
			Asks l = allAsks.get(i);

				for(int j = 0; j<l.getExtra_zip().length; j++) {
					if(b) {
						if(l.isIs_active() && (zip.equals(l.getExtra_zip()[j]))) {
							searchAsks.add(l);
						}
					}else {
						if((zip.equals(l.getExtra_zip()[j]))) {
							searchAsks.add(l);
						}
					}
//					if(b && (zip==l.getExtra_zip()[j]) && l.isIs_active()) {
//						searchAsks.add(l);
//					}else {
//						if((zip==l.getExtra_zip()[j])) {
//							searchAsks.add(l);
//						}
//					}
				}


		}
		return searchAsks;
	}
	@Override
	public void deleteAsk(String lid) {

    	Asks l = findByAid(lid);
    	if (l.isNil()) {
    		throw new NoSuchElementException();
    	} else {
    		asks.remove(l);
    	}
		
	}

	

}
