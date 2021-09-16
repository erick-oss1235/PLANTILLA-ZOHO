package com.example.plantilla.app.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

	@GetMapping("/hello")
	public ResponseEntity<?> getHello(){
		Map<String,Object> response = new HashMap<>();
		try {
			response.put("message", "Hello World from ".concat( InetAddress.getLocalHost().toString() ));
		} catch (UnknownHostException e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/server")
	public ResponseEntity<?> serverResponse() {
		Map<String,Object> response = new HashMap<>();
		response = WebClient.builder().baseUrl("http://192.168.1.14:8080/plantilla-zoho/api").build().get().uri("/hello").accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Map.class).block();
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
}
