package com.example.demo.configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import com.example.demo.service.RoleServiceImpl;
@Configuration
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		
	}
	
	/**
	 * When the JerseyConfig class is executed (Spring executes it) this method is executed which calls REGISTER (which creates an Endpoint) and sends it the RoleServiceImple class.
	 */
	@PostConstruct
	public void setUp() {
		register(RoleServiceImpl.class);
	}
}
