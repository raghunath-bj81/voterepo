package com.voter.verification.gateway;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Gateway interface to accept the Voter Information Message
 * @author rjosula
 *
 */
@Component
public interface VoterInformationGateway {

	public void sendToInputQ(Message<String> voterJson);
}
