package com.voter.verification.service;

import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.voter.verification.dao.VoterDAO;
import com.voter.verification.model.Voter;
import com.voter.verification.model.VoterInformation;

/**
 * Service activator to consume & apply business logic to transform from JSON to
 * XML
 * 
 * @author rjosula
 *
 */
@Component
public class VoterServiceActivator {
	
	@Autowired
	private VoterDAO voterDAO;

	/**
	 * Service activator method to receive the input message
	 * 
	 * @param inputMesg
	 * @return
	 */
	public Message<VoterInformation> mapPersonToVoter(Message<Voter> personMsg) {
		System.out.println("Inside service activator... " + personMsg.getPayload());
		Voter voterInput = personMsg.getPayload();
		Voter voterObjSaved = null;
		boolean isPersonEligible = false;
		if (voterInput.getpersonAge() >= 18) {
			isPersonEligible = true;
		}
		/** Saving the information using DAO Layer*/
		voterObjSaved = voterDAO.saveOrUpdateVoterInfo(voterInput);
		System.out.println("Voter information has been successfully saved or updated!...."+voterObjSaved.toString());
		
		VoterInformation voterInfo = new VoterInformation();
		voterInfo.setVoterId(voterInput.getpersonId());
		voterInfo.setVoterName(voterInput.getpersonName());
		voterInfo.setVoterAddress(voterInput.getpersonAddress());
		voterInfo.setVoterProvince(voterInput.getState());
		voterInfo.setEligible(isPersonEligible);
		voterInfo.setVoterAge(voterInput.getpersonAge());
		voterInfo.setReferenceId(voterInput.getReferenceId());
		voterInfo.setEmailId(voterInput.getEmailId());

		return MessageBuilder.withPayload(voterInfo).build();
	}

	/**
	 * Takes input as DomResult XML and converts to XML String.
	 * 
	 * @param inputJson
	 * @return
	 */
	public String voterXMLInput(Message<DOMResult> inputJson) {
		DOMResult payload = inputJson.getPayload();
		Transformer transformer;
		Message<String> msgOutput = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			Source input = new DOMSource(payload.getNode());
			transformer.transform(input, result);
			msgOutput = MessageBuilder.createMessage(result.getWriter().toString(), inputJson.getHeaders());
		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			e.printStackTrace();
		}
		return msgOutput.getPayload();
	}
	
}
