# MessagesApiRest
Repo con error - Testing

# Para correr
>Colocarse dentro del directorio /MessagesApiRest
	
>mvnw -DskipTests clean install

# Para correr el test erroneo
>mvnw -Dtest=MessageMockitoTest test

# Problema de casteo sobre la creación del List<Message> hacia la respuesta que brinda el @RestController que llamo y da un ResponseEntity.

Mensaje de error (acortado)
java.lang.ClassCastException: class net.apimessages.pd2.messagetest.MessageMockitoTest$1 cannot be cast to class org.springframework.http.ResponseEntity (net.apimessages.pd2.messagetest.MessageMockitoTest$1 and org.springframework.http.ResponseEntity are in unnamed module of loader 'app')
	at net.apimessages.pd2.messagetest.MessageMockitoTest.showWhatRestHave(MessageMockitoTest.java:65)
...
	
# (Volvió a surgir el error de lectura de SQL script que pensé que se había solucionado.
	
 Results:
[INFO]
[ERROR] Errors: 
[ERROR]   MessageMockitoTest.notNulls » CannotReadScript Cannot read SQL script from cla...
[ERROR]   MessageMockitoTest.showWhatRestHave » CannotReadScript Cannot read SQL script ...
[INFO]
[ERROR] Tests run: 2, Failures: 0, Errors: 2, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  18.449 s
[INFO] Finished at: 2021-05-29T00:09:28-03:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.22.2:test (default-test) on project MessagesApiRest: There are test
 failures.
[ERROR]
[ERROR] Please refer to C:\Users\kaomeram\Documents\Carrera\MessagesApiRest\target\surefire-reports for the individual test results.
[ERROR] Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1])



