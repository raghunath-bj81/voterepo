package com.voter.verification.model;

import java.io.Serializable;

public class PersonInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int personId;
	private String personName;
	private int personAge;
	private String personAddress;
	private String state;
	public int getpersonId() {
		return personId;
	}
	public void setpersonId(int personId) {
		this.personId = personId;
	}
	public String getpersonName() {
		return personName;
	}
	public void setpersonName(String personName) {
		this.personName = personName;
	}
	public int getpersonAge() {
		return personAge;
	}
	public void setpersonAge(int personAge) {
		this.personAge = personAge;
	}
	public String getpersonAddress() {
		return personAddress;
	}
	public void setpersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public PersonInformation(int personId, String personName, int personAge, String personAddress,
			String state) {
		super();
		this.personId = personId;
		this.personName = personName;
		this.personAge = personAge;
		this.personAddress = personAddress;
		this.state = state;
	}
	
	public PersonInformation() {
	}
	
	@Override
	public String toString() {
		return "PersonInformation [personId=" + personId + ", personName=" + personName + ", personAge="
				+ personAge + ", personAddress=" + personAddress + ", state=" + state + "]";
	}
}
