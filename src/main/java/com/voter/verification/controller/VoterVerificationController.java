package com.voter.verification.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.voter.verification.gateway.VoterInformationGateway;

/**
 * Handler class to handle the person object to prepare the voter information
 * based on the persons age list of voters shall be decided.
 * 
 * @author rjosula
 *
 */
@RestController
@RequestMapping("/voter/verificationapp")
public class VoterVerificationController {

	@Autowired
	private VoterInformationGateway voterInputGateway;
	
	/**
	 * Controller method recieves employee information json string which will be processed
	 * to verify whether the employee is eligible for voting or not.
	 * @return
	 */
	@RequestMapping(value = "/sendVoterInfo", method = RequestMethod.POST, produces = {"application/xml"})
	public ResponseEntity<?> publishVoterInfo(@RequestBody String personString) {
		
		Map<String, Object> headers = new HashMap<>();
		headers.put("content-type", "application/json");
		
		MessageHeaders header = new MessageHeaders(headers);
		Message<String> msg = MessageBuilder.createMessage(personString, header);
		voterInputGateway.sendToInputQ(msg);
		
		
		return new ResponseEntity<String>("Published successfully", HttpStatus.ACCEPTED);
	}
}
