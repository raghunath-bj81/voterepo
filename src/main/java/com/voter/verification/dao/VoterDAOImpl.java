package com.voter.verification.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.voter.verification.model.Voter;

/**
 * Voter Repository Implementation class to operate the CRUD operations.
 * @author rjosula
 *
 */
@Repository
public class VoterDAOImpl implements VoterDAO {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public Voter saveOrUpdateVoterInfo(Voter voterObj) {
		Voter voterSavedObj = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("personId").is(Integer.valueOf(voterObj.getpersonId())));
		try {
	        Update update = new Update();
	        update.set("personId" , voterObj.getpersonId());
	        update.set("personAge" , voterObj.getpersonAge());
	        update.set("personName", voterObj.getpersonName());
	        update.set("personAddress", voterObj.getpersonAddress());
	        update.set("state", voterObj.getState());
	        
	        voterSavedObj = mongoTemplate.findAndModify(query, update, Voter.class);
	        if(voterSavedObj == null ) {
	        	voterSavedObj = mongoTemplate.save(voterObj);
	        }
	        System.out.println("Updated the information ... " + voterSavedObj.toString());
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return voterSavedObj;
	}

	@Override
	public List<Voter> getListOfVoterInfo(Integer id) {
		return mongoTemplate.find(new Query().addCriteria(Criteria.where("personId").is(id)), Voter.class);
	}

}
