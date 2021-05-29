package net.apimessages.pd2.messagetest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import net.apimessages.pd2.MessagesApiRestApplication;
import net.apimessages.pd2.model.Message;

@SpringBootTest(classes = MessagesApiRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageWebResponseTest {

	@Autowired
    private TestRestTemplate restTemplate;

	@LocalServerPort
    private int port;
	
    private String getRootUrl() {
        return "http://localhost:" + port;
    }	
    
    @Test
    public void testGetAllEmployees() {
    	HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/messages/",
        HttpMethod.GET, entity, String.class);  
        assertNotNull(response.getBody());
        System.out.print(response.getBody());
   }
    
    @Test
    public void testCreateMessage() {
    	Long id = 1l;
        Message message = new Message(id, "cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this content first");
        ResponseEntity<Message> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/messages/", message, Message.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        System.out.print("La respuesta del postResponse" + postResponse.getBody());
     }
    
    @Test
    public void testGetMessageByIdNotNull() {
    	Long id = 1l;
        Message message = new Message(id, "cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this content first");
        ResponseEntity<Message> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/messages/", message, Message.class);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/messages/1",
        HttpMethod.GET, entity, String.class);  
        assertNotNull(response.getBody());
        System.out.print("La respuesta del response: " + response.getBody());
        assertNotNull(postResponse.getBody());
        System.out.print("La respuesta del postResponse: " + postResponse.getBody());
     }
}
