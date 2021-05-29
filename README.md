# MessagesApiRest
Repo con error - Testing

Problema de casteo sobre la creación del List<Message> hacia la respuesta que brinda el @RestController que llamo y da un ResponseEntity.

Mensaje de error (acortado)
java.lang.ClassCastException: class net.apimessages.pd2.messagetest.MessageMockitoTest$1 cannot be cast to class org.springframework.http.ResponseEntity (net.apimessages.pd2.messagetest.MessageMockitoTest$1 and org.springframework.http.ResponseEntity are in unnamed module of loader 'app')
	at net.apimessages.pd2.messagetest.MessageMockitoTest.showWhatRestHave(MessageMockitoTest.java:65)
...

#To Run


