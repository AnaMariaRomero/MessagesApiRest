package net.apimessages.pd2.messagetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

import net.apimessages.pd2.model.Message;
import net.apimessages.pd2.rest.MessageRest;
import net.apimessages.pd2.service.MessageService;

@SpringBootTest
@Import(MessageService.class)
@Sql({"schema-data.sql"})
public class MessageMockitoTest {
	
	@InjectMocks
	MessageRest rest;
	
	@Mock
	MessageService messageService;
	
	WebClient webClient = WebClient.create("https://api.github.com");
	
	@Test
	void notNulls() {
		assertThat(rest).isNotNull();
		assertThat(messageService).isNotNull();		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void showWhatRestHave() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Message m1 = new  Message(1L,"cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this message H2");
		Message m2 = new  Message(2L,"cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this message H2");
		@SuppressWarnings("serial")
		List<Message> list = new LinkedList<Message>() {{
			add(m1);
			add(m2);
	    }};
		ResponseEntity<Optional<List<Message>>> responseEntity = rest.getAll();
		
		when(rest.getAll()).thenReturn((ResponseEntity<Optional<List<Message>>>) list);
		
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		
	}
}
