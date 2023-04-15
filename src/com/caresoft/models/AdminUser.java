package com.caresoft.models;

import java.util.ArrayList;
import java.util.Date;

import com.caresoft.interfaces.HIPAACompliantAdmin;
import com.caresoft.interfaces.HIPAACompliantUser;

public class AdminUser extends User implements HIPAACompliantAdmin, HIPAACompliantUser {
	
    private String role;
    private ArrayList<String> securityIncidents;
    
    public AdminUser(Integer id, String role) {
		super(id);
		this.role = role;
		this.securityIncidents = new ArrayList<String>();
	}
       
    
    public String getRole() {
		return role;
	}

    
	public void setRole(String role) {
		this.role = role;
	}


	public ArrayList<String> getSecurityIncidents() {
		return securityIncidents;
	}

	
	public void setSecurityIncidents(ArrayList<String> securityIncidents) {
		this.securityIncidents = securityIncidents;
	}


	public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }
    


	@Override
	public boolean assignPin(int pin) {
		int pinDigits = String.valueOf(pin).length();
		
		if(pinDigits >= 6) {
			System.out.println("You have succefully assigned a PIN");
			System.out.println("PIN: " + pin);
			super.setPin(pin);
			return true;
		}
		else {
			System.out.println("PIN must be 6 digits or more");
		return false;
		}
	}

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		
		Integer userID = super.getId();
		
		if(confirmedAuthID.equals(userID)) {
			System.out.println("Access Authorized");
			return true;
		}
		else {
			System.out.println("Access Denied");
			this.authIncident();
			return false;
		}
	}

	@Override
	public ArrayList<String> reportSecurityIncidents() {
		
		return this.securityIncidents;
		
	}

}
