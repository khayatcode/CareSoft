package com.caresoft.models;

import java.util.ArrayList;
import java.util.Date;

import com.caresoft.interfaces.HIPAACompliantUser;

public class Physician extends User implements HIPAACompliantUser {
	
	private ArrayList<String> patientNotes;

	public Physician(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	

	public ArrayList<String> getPatientNotes() {
		return patientNotes;
	}

	public void setPatientNotes(ArrayList<String> patientNotes) {
		this.patientNotes = patientNotes;
	}


	@Override
	public boolean assignPin(int pin) {
		int pinDigits = String.valueOf(pin).length();
		
		if(pinDigits == 4) {
			System.out.println("You have succefully assigned a PIN");
			System.out.println("PIN: " + pin);
			super.setPin(pin);
			return true;
		}
		else {
			System.out.println("PIN must be 4 digits");
			return false;
		}
	}
	

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		
		Integer physicianID = super.getId();
		
		if(confirmedAuthID.equals(physicianID)) {
			System.out.println("Access Authorized");
			return true;
		}
		else {
			System.out.println("Access Denied");
			return false;
		}
	
	}
    
	
    public void newPatientNotes(String notes, String patientName, Date date) {
        String report = String.format(
            "Datetime Submitted: %s \n", date);
        report += String.format("Reported By ID: %s\n", this.id);
        report += String.format("Patient Name: %s\n", patientName);
        report += String.format("Notes: %s \n", notes);
        this.patientNotes.add(report);
    }
	

}
