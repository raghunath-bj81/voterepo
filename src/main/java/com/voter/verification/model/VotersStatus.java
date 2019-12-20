package com.voter.verification.model;

import java.io.Serializable;
import java.util.List;

/**
 * Statistics class which will store the eligible & ineligible voters.
 * @author rjosula
 *
 */
public class VotersStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int noOfVotersEligible;
	private int noOfVotersInEligible;
	private List<VoterInformation> eligibleVoters;
	private List<VoterInformation> inEligibleVoters;
	
	public int getNoOfVotersEligible() {
		return noOfVotersEligible;
	}
	public void setNoOfVotersEligible(int noOfVotersEligible) {
		this.noOfVotersEligible = noOfVotersEligible;
	}
	public int getNoOfVotersInEligible() {
		return noOfVotersInEligible;
	}
	public void setNoOfVotersInEligible(int noOfVotersInEligible) {
		this.noOfVotersInEligible = noOfVotersInEligible;
	}
	public List<VoterInformation> getEligibleVoters() {
		return eligibleVoters;
	}
	public void setEligibleVoters(List<VoterInformation> eligibleVoters) {
		this.eligibleVoters = eligibleVoters;
	}
	public List<VoterInformation> getInEligibleVoters() {
		return inEligibleVoters;
	}
	public void setInEligibleVoters(List<VoterInformation> inEligibleVoters) {
		this.inEligibleVoters = inEligibleVoters;
	}
	@Override
	public String toString() {
		return "VotersStatus [noOfVotersEligible=" + noOfVotersEligible + ", noOfVotersInEligible="
				+ noOfVotersInEligible + ", eligibleVoters=" + eligibleVoters.size() + ", inEligibleVoters=" + inEligibleVoters.size()
				+ "]";
	}
	
	
}
