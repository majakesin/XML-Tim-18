package com.example.request;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RequestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestApplication.class, args);
		System.out.println("REQUEST APP STARTED_____________________________");
	}

	@Bean
	public ServletWebServerFactory servletContainer() {
		// Enable SSL Trafic
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};

		// Add HTTP to HTTPS redirect
		tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

		return tomcat;
	}

	/*
    We need to redirect from HTTP to HTTPS. Without SSL, this application used
    port 9090. With SSL it will use port 8443. So, any request for 9090 needs to be
    redirected to HTTPS on 8443.
     */
	private Connector httpToHttpsRedirectConnector() {
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);
		return connector;
	}

}
