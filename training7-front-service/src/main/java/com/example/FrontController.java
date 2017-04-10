package com.example;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class FrontController {

	private final RestTemplate restTemplate;
	private final URI messageServiceUri;

	public FrontController(RestTemplate restTemplate,
			@Value("${message.service.uri}") URI messageServiceUri) {
		this.restTemplate = restTemplate;
		this.messageServiceUri = messageServiceUri;
	}

	@GetMapping("/")
	String helloFront() {
		return restTemplate.getForObject(
				UriComponentsBuilder
				.fromUri(messageServiceUri)
				.build()
				.toUri(), 
				String.class);
	}
}
