CREATE TABLE entries (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     title VARCHAR(200) NOT NULL,
     link VARCHAR(200) NOT NULL,
     PRIMARY KEY (id)
);

INSERT INTO entries (title, link) VALUES ('Simple continuous deployment from Jenkins to AWS','http://jamesburt.me.uk/continuousdelivery/simple-continuous-deployment-from-jenkins-to-aws/');

INSERT INTO entries (title, link) VALUES ('Java Infrastructure Part 7 - Adding coverage checking','http://jamesburt.me.uk/infrastructure/java-infrastructure-part-7-adding-coverage-checking/');
