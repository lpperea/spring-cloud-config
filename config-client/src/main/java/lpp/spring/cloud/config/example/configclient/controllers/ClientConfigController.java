package lpp.spring.cloud.config.example.configclient.controllers;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientConfigController {

	private final Environment environment;
	
	public ClientConfigController(Environment environment) {
		this.environment = environment;
	}
	
	private String readValue() {
		return this.environment.getProperty("example.message");
	}
	
	@GetMapping("/value")
	String getValue() {
		return this.readValue();
	}
	
	//Test for amqp events... when activated
	@EventListener({RefreshScopeRefreshedEvent.class})
	public void onRefreshedEvent() {
		System.out.println("Refreshed event received: " + this.readValue());
	}
	
	@EventListener({ApplicationReadyEvent.class})
	public void onApplicationReadyEvent() {
		System.out.println("Application ready event received: " + this.readValue());
	}
}
