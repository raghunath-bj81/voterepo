package com.voter.verification.gateway;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.voter.verification.model.VotersStatus;

/**
 * Gateway interface to accept the Voter Information Message
 * @author rjosula
 *
 */
@Component
public interface VoterInformationGateway {

	public Message<VotersStatus> sendToInputQ(Message<String> voterJson);
}
