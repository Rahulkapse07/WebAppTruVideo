package com.truvideo.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;

import org.testng.annotations.Test;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;

@Test
public class exampleemail {
	
	public void email() throws IOException, MailosaurException {
		    String apiKey = "0I12RZR2fG2B7Mdh8prK3XnUl8VoG38j";
		    String serverId = "rwy0mofv";
		    String serverDomain = "rwy0mofv.mailosaur.net";
		
		    MailosaurClient mailosaur = new MailosaurClient(apiKey);

		    MessageSearchParams params = new MessageSearchParams();
		    params.withServer(serverId);

		    SearchCriteria criteria = new SearchCriteria();
		    criteria.withSentTo("us@" + serverDomain);

		    Message message = mailosaur.messages().get(params, criteria);
		    System.out.println(message.subject());
		    System.out.println(message.cc());
		    
		    System.out.println(message.to().get(0).email());
		    System.out.println(message.from().get(0).email());
		    System.out.println("-----body"+ message.text().body());

		    assertNotNull(message);
		    assertEquals("Re: hello mailosaur test"
		    		, message.subject());
	}

}
