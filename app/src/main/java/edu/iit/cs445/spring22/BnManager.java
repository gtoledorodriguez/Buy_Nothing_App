package edu.iit.cs445.spring22;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;



public class BnManager implements BoundaryInterface {
	private static List<Accounts> accounts = new ArrayList<Accounts>();
	private static List<Asks> asks = new ArrayList<Asks>();
	private boolean inAccountsList = false;
	private boolean changingActiveStatus = false;
	
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
			key = key.toLowerCase();
			
			if(start_date!=null && end_date!=null) {
				try {
	            	Date date1= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
					Date startDate= new SimpleDateFormat("dd-MMM-yyyy").parse(start_date);
					Date endDate= new SimpleDateFormat("dd-MMM-yyyy").parse(end_date);
					
					if(!(date1.before(startDate) || date1.after(endDate))) {
						if(name.contains(key) || street.contains(key) || zip.contains(key)|| phone.contains(key)|| picture.contains(key)) {
	        				searchAccs.add(l);
	        			}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} 
			}else {
				if(name.contains(key) || street.contains(key) || zip.contains(key)|| phone.contains(key)|| picture.contains(key)) {
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

	

}
