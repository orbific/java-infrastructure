package com.riddlefox.greeting;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 
import org.springframework.ui.Model;
import javax.annotation.PostConstruct;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.web.WebApplicationInitializer;
import javax.servlet.ServletContext;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.ServletException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import javax.servlet.ServletRegistration;
 
import com.rometools.rome.feed.synd.SyndEntry;

@Controller
@SpringBootApplication
public class Greeter extends SpringBootServletInitializer implements WebApplicationInitializer {

	private Log log = LogFactory.getLog(Greeter.class);

	@Autowired
	EntryRepository entryRepository;
	
	public Greeter() {
 	  	log.info("\n\nConstructor\n\n");
	}
	
	@RequestMapping("/")
    public String greet(String name, Model model) {
    	if(entryRepository!=null) {
   			log.info("Entries exist");
    		for(Entry e : entryRepository.findAll()) {
    			log.info("Entry: "+e.getTitle());
    		}
    	} else {
  			log.info("Entry repo is null");
    	}
    	model.addAttribute("entries", entryRepository.findAll());
    	model.addAttribute("name", "james from url");
        return "index";
    }

	@RequestMapping("/titles")
    public String titles(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
    	if(entryRepository!=null) {
   			log.info("Entries exist");
    		for(Entry e : entryRepository.findAll()) {
    			log.info("Entry: "+e.getTitle());
    		}
    	} else {
  			log.info("Entry repo is null");
    	}
    	model.addAttribute("entries", entryRepository.findAll());
    	model.addAttribute("name", "james from url");
        return "titles";
    }

    
	public static void main(String[] args) throws Exception {
        SpringApplication.run(Greeter.class, args);
    }
    
	@PostConstruct
    public void setUpRssReader() {
    	log.info("\n\nSetting up Rss reader\n\n");
	    ApplicationContext context = new ClassPathXmlApplicationContext( "/feed-bean.xml");
 
        // create a pollable channel
        PollableChannel feedChannel = context.getBean("feedChannel", PollableChannel.class);
    	log.info("\n\nCreated channel\n\n");
 
        for (int i = 0; i < 10; i++) {
            // receive the message feed
	    	log.info("Read entry");
            Message<SyndEntry> message = (Message<SyndEntry>) feedChannel.receive(1000);
            if (message != null) {
                SyndEntry entry = message.getPayload();
		    	log.info("Entry "+entry.getTitle());
                // display
                log.info(entry.getPublishedDate() + " - " + entry.getTitle());
                Entry myEntry = new Entry(entry.getTitle(), entry.getLink());
                entryRepository.save(myEntry);
            } else {
            	try {
            		log.info("Sleeping 10s");
            		Thread.sleep(10000);
            	} catch (Throwable t) {
            		// ignore
            	}
            }
        }
    }
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = new AnnotationConfigWebApplicationContext();
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
    }	    
    
}
