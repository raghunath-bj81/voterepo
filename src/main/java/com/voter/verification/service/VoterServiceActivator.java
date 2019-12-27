package com.voter.verification.service;

import java.io.File;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.voter.verification.model.PersonInformation;
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

	/**
	 * Service activator method to receive the input message
	 * 
	 * @param inputMesg
	 * @return
	 */
	public Message<VoterInformation> mapPersonToVoter(Message<PersonInformation> personMsg) {
		System.out.println("Inside service activator... " + personMsg.getPayload());
		PersonInformation personInfo = personMsg.getPayload();

		boolean isPersonEligible = false;
		if (personInfo.getpersonAge() >= 18) {
			isPersonEligible = true;
		}
		VoterInformation voterInfo = new VoterInformation();
		voterInfo.setVoterId(personInfo.getpersonId());
		voterInfo.setVoterName(personInfo.getpersonName());
		voterInfo.setVoterAddress(personInfo.getpersonAddress());
		voterInfo.setVoterProvince(personInfo.getState());
		voterInfo.setEligible(isPersonEligible);

		return MessageBuilder.withPayload(voterInfo).build();
	}

	/**
	 * Takes input as DomResult XML and converts to XML String.
	 * 
	 * @param inputJson
	 * @return
	 */
	public Message<String> voterXMLInput(Message<DOMResult> inputJson) {
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
		return msgOutput;
	}
}
