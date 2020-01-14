package com.voter.verification.dao;

import java.util.List;

import com.voter.verification.model.Voter;

/**
 * VoterDAO is an interface provides save or update of the Voter Information
 * @author rjosula
 *
 */
public interface VoterDAO {
	public Voter saveOrUpdateVoterInfo(Voter voterObj);
	public List<Voter> getListOfVoterInfo(Integer id);
}
