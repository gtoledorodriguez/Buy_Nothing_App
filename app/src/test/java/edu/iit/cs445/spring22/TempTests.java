package edu.iit.cs445.spring22;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class TempTests {

	@Test
	void test() {
		BoundaryInterface bi = new BnManager();
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
    				+ "  \"picture\": \"http://example.com/images/CSR-#1.jpeg\",\n"
    				+ "  \"is_active\": true,\n"
    				+ "  \"date_created\": \"\"\n"
    				+ "}";
    		Gson gs = new Gson();
            Accounts il = gs.fromJson(json, Accounts.class);
            Accounts l = bi.createAccounts(il);
            
            Accounts il2 = gs.fromJson(json2, Accounts.class);
            Accounts l2 = bi.createAccounts(il2);
            
            Accounts il3 = gs.fromJson(json3, Accounts.class);
            Accounts l3 = bi.createAccounts(il3);
            int i = 0;
            List<Accounts> accs = bi.getAllAccounts();
            while (i != accs.size()){
            	System.out.println(accs.get(i).getIsActive());
            	i++;
            	
            }
            
            System.out.println(bi.getAllAccounts().size());
    	}
		assertEquals(bi.getAllAccounts().size(), 3);
	}

}
