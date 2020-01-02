package com.voter.verification.service;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class DummyActivator {

	public Message<String> executeVoterCmd(Message<String> input){
		return input;
	}
}
