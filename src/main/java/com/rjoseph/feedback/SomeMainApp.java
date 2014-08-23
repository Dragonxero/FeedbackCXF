package com.rjoseph.feedback;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SomeMainApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Load Spring XML context file(s)
		// See
		// http://cxf.apache.org/docs/jaxrs-services-configuration.html#JAXRSServicesConfiguration-ConfiguringJAX-RSservicesprogrammaticallywithSpringconfigurationfile.
		// http://stackoverflow.com/questions/4377699/spring-contextconfiguration-how-to-put-the-right-location-for-the-xml
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/applicationContext-cxf.xml",
				"classpath*:/applicationContext-*.xml", "file:src/main/webapp/WEB-INF/spring/applicationContext-beans.xml");
		JAXRSServerFactoryBean sfb = (JAXRSServerFactoryBean) context.getBean("feedbackService");
		System.out.println(context.getBean("someString"));
		//Server server = sfb.create();
		context.close();
	}

}
