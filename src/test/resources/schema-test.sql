DROP TABLE IF EXISTS MESSAGES;

 CREATE TABLE MESSAGES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  sender VARCHAR(50) NOT NULL,
  recipient VARCHAR(50) NOT NULL,
  content VARCHAR(250) DEFAULT NULL
);

INSERT INTO MESSAGES (sender, recipient, content) VALUES
  ('cf08b179-a4c7-4dee-8965-d9c94f9f013a', 'cf08b179-a4c7-4dee-8965-d9c94f9f013a', 'this message H2'),
  ('cf08b179-a4c7-4dee-8965-d9c94f9f013a', 'cf08b179-a4c7-4dee-8965-d9c94f9f013a', 'this message H2 two');