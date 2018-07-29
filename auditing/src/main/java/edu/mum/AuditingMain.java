package edu.mum;

import java.io.IOException;

import org.springframework.context.support.GenericXmlApplicationContext;
 
public class AuditingMain {
    public static void main(String[] args) throws IOException {
    	
 
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load(
        		"classpath:META-INF/spring/jms-init-context.xml",
                "classpath:META-INF/spring/jms-listener-app-context.xml");
        context.refresh();
        
       
 
    }
}
