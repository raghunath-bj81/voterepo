package com.voter.verification.service;

import javax.xml.transform.dom.DOMResult;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.voter.verification.model.PersonInformation;
import com.voter.verification.model.VoterInformation;

/**
 * Service activator to consume & apply business logic to transform from JSON to XML
 * @author rjosula
 *
 */
@Component
public class VoterServiceActivator {

	/**
	 * Service activator method to receive the input message
	 * @param inputMesg
	 * @return
	 */
	public Message<VoterInformation> mapPersonToVoter(Message<PersonInformation> personMsg) {
		System.out.println("Inside service activator... "+personMsg.getPayload());
		PersonInformation personInfo = personMsg.getPayload();
		
		boolean isPersonEligible = false;
		if(personInfo.getpersonAge() >= 18) {
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
	 * Takes input as 
	 * @param inputJson
	 * @return
	 */
	public Message<DOMResult> voterXMLInput(Message<DOMResult> inputJson) {
		DOMResult payload = inputJson.getPayload();
		Node node = payload.getNode();
		NodeList nodes = node.getChildNodes();
		NodeList childNodes = nodes.item(0).getChildNodes();
		int noOfChilds = childNodes.getLength();
		if(noOfChilds > 0) {
			for(int i =0 ; i < noOfChilds; i++) {
				Node eachNode = childNodes.item(i);
				System.out.println(eachNode.getLocalName()+  " = " +  eachNode.getTextContent());
			}
		}
		return inputJson;
	}
}
