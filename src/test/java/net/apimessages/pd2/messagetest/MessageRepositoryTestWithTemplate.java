package net.apimessages.pd2.messagetest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import net.apimessages.pd2.model.Message;
/*
 * 
 * Test para verificar el funcionamiento de MessageRepository
 * 
 */
import net.apimessages.pd2.repository.MessageRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
public class MessageRepositoryTestWithTemplate {

	@Autowired
    private MessageRepository messageRepository;
	
	@Test
    public void findAllMessages() {
	 	Long id = 1l;
        Message message = new Message(id, "cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this content first");
        messageRepository.save(message);
        assertNotNull(messageRepository.findAll());
    }
	 
	 @Test
	    public void testSaveMessage() {
		 	Long id = 1l;
	        Message message = new Message(id, "cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this content first");
	        messageRepository.save(message);
	        Message message2 = messageRepository.getById(id);
	        assertNotNull(message);
	        assertEquals(message2.getContent(),message.getContent());
	    }
	 @Test
	    public void testGetMessageById() {
		 	Long id = 1l;
	        Message message = new Message(id, "cf08b179-a4c7-4dee-8965-d9c94f9f013a", "cf08b179-a4c7-4dee-8965-d9c94f9f013a","this content first");
	        messageRepository.save(message);
	        Message message2 = messageRepository.getById(id);
	        assertNotNull(message);
	        assertEquals(message2.getContent(),message.getContent());
	    }
}
