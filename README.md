# java-infrastructure
An example of Java infrastructure, based on blog posts on riddlefox.com

What I want to do with this project – both on my blog and on github – is to build up a generic piece of 
software with simple Java code, but a rich infrastructure. I think there is a lot to learn from this – 
and at the end I’ll have a good base to work from with my own future projects.

The blog series discussing this can be found here:
http://jamesburt.me.uk/infrastructure/java-infrastructure-part-0-the-long-way/

## Build environment

This project is currently being built against Ubuntu 14.04 using Java 1.8.0_72. It uses gradle v2.11 via 
the gradle wrapper.

## Jenkins

The project uses a Jenkins instance for continuous integration. The configuration for this is stored within git. When setting up Jenkins, the latest version of the server can be downloaded from http://mirrors.jenkins-ci.org/war/latest/jenkins.war. You will need to set a JENKINS_HOME directory to specify the directory where Jenkins stores its configurations and data.
