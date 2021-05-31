package net.apimessages.pd2.messagetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

import net.apimessages.pd2.model.Message;
import net.apimessages.pd2.rest.MessageRest;
import net.apimessages.pd2.service.MessageService;

@SpringBootTest
@PropertySource({"classpath:application.properties"})
@Sql({"classpath:schema-test.sql"})
@Import(MessageService.class)
public class MessageMockitoTest {
	
	@InjectMocks
	MessageRest rest;
	
	@Mock
	MessageService messageService;
	
	@Test
	void notNulls() {
		assertThat(rest).isNotNull();
		assertThat(messageService).isNotNull();		
	}
	
	@Test
	void showWhatRestHave() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Message m1 = new  Message("cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this message H2");
		Message m2 = new  Message("cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this message H2");
		List<Message> list = new LinkedList<Message>() {{
			add(m1);
			add(m2);
	    }};
		System.out.print(rest.getAll());
	    System.out.print(rest.create(m2));
		System.out.print(rest.create(m1));
		System.out.print(rest.getAll());
		assertThat(rest.getAll()).isNotNull();
		
		Message m3 = new  Message(1l,"cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this message H2");
		Message m4 = new  Message(2l,"cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this message H2");
		
		System.out.print(rest.getAll());
	    System.out.print(rest.create(m3));
		System.out.print(rest.create(m4));
		System.out.print(rest.getAll());
	}
	
	@Test
	void justSimpleTest() {
		Iterable<String> actual = Arrays.asList("fee", "fi", "foe");
		List<String> expected = Arrays.asList("fee", "fi", "foe");

		assertThat(actual).isNotNull()  
        .contains("fee"); 
	}
}
