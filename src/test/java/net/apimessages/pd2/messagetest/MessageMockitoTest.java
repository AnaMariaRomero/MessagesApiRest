package net.apimessages.pd2.messagetest;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.web.servlet.ResultActions.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.*;
import net.apimessages.pd2.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.status.Status;
import net.apimessages.pd2.service.MessageService;
import org.springframework.http.HttpHeaders;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.http.MediaType;
import java.util.Arrays;


@SpringBootTest
@AutoConfigureMockMvc
public class MessageMockitoTest {

    @Autowired
    private MockMvc mockMvc;
	@Mock
	MessageService messageService;
	
	@Test
	void notNulls() {
		assertThat(messageService).isNotNull();		
	}
	
	@Test
	void connectionIsOK() throws Exception {
				
		Message m1 = new  Message(1l,"cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this message H2");
		Message m2 = new  Message(2l,"cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this message H2 two");
	    List list = Arrays.asList(m1,m2);
		when(messageService.findAll()).thenReturn(list);
	
		mockMvc.perform(get("/api/messages/"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	void createdIsOK() throws Exception {
				
		Message m1Saved = new  Message("cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this message H2");
		Message m2Returned = new  Message(1l,"cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this message H2");
	    
		when(messageService.create(any())).thenReturn(m2Returned);
	
		mockMvc.perform(post("/api/messages/").contentType(MediaType.APPLICATION_JSON).content(asJsonToString(m2Returned)))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(header().string(HttpHeaders.LOCATION, "/api/messages/1"))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.sender", is("cf08b179-a4c7-4dee-8965-d9c94f9f013a")))
		.andExpect(jsonPath("$.recipient", is("cf08b179-a4c7-4dee-8965-d9c94f9f013a")));
		
	}
	
	@Test
	void giveMessageByIdIsOk() throws Exception{
		Message m1Saved = new  Message(1l,"cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this message H2");
		when(messageService.getById(1l)).thenReturn(m1Saved);
		
	
		mockMvc.perform(get("/api/messages/{id}", 1l))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.sender", is("cf08b179-a4c7-4dee-8965-d9c94f9f013a")))
		.andExpect(jsonPath("$.recipient", is("cf08b179-a4c7-4dee-8965-d9c94f9f013a")));
	}
	
	@Test
	void getIdMessageNotFound() throws Exception{
		when(messageService.getById(6l)).thenReturn(new Message() {});
		mockMvc.perform(get("/api/messages/{id}", 6l))
		.andExpect(status().isNotFound())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	
	}
	
	@Test
	void createdBadRequest() throws Exception {	    
		when(messageService.create(null)).thenReturn(null);
		mockMvc.perform(post("/api/messages/"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void justSimpleTest() throws Exception{
		Iterable<String> actual = Arrays.asList("fee", "fi", "foe");
		List<String> expected = Arrays.asList("fee", "fi", "foe");

		assertThat(actual).isNotNull()  
        .contains("fee"); 
	}
	
	static String asJsonToString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
