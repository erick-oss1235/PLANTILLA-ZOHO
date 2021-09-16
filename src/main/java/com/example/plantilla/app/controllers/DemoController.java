package com.example.plantilla.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.plantilla.app.configuration.ConfigurationComponent;
import com.example.plantilla.app.models.entity.Demo;
import com.example.plantilla.app.models.service.IDemoService;
import com.zoho.crm.library.exception.ZCRMException;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private IDemoService service;
	
	@Autowired
	private ConfigurationComponent configuration;
	
	@GetMapping("/getData/{module}/{id}")
	public ResponseEntity<?> getRecordById(@PathVariable String module,@PathVariable Long id){
		System.out.println("Hola mundo");
<<<<<<< HEAD
		System.out.println("******************************");
=======
		
>>>>>>> b7b74c22fab503b23b1004d02a7592d69b5fb3e3
		configuration.configurationZoho();
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			response = service.getRecordById(module, id);
		} catch (ZCRMException e) {
			response.put("ok", "false");
			response.put("error", "Error: ".concat(e.getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("ok", true);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/addData/{module}")
	public ResponseEntity<?> getRecordById(@PathVariable String module, @RequestBody Map<String,Object> data){
		configuration.configurationZoho();
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			System.out.println("TEST!!");
			response = service.addRecordInModule(module, data);
		} catch (ZCRMException e) {
			response.put("ok", "false");
			response.put("error", "Error: ".concat(e.getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("ok", true);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updateData/{module}/{id}")
	public ResponseEntity<?> updateRecordById(@PathVariable String module, @PathVariable Long id, @RequestBody Map<String,Object> data){
		configuration.configurationZoho();
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			response = service.updateRecordById(module, id, data);
		} catch (ZCRMException e) {
			response.put("ok", "false");
			response.put("error", "Error: ".concat(e.getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("ok", true);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteRecord/{module}/{id}")
	public ResponseEntity<?> deleteRecordById(@PathVariable String module, @PathVariable Long id){
		configuration.configurationZoho();
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			response = service.deleteRecordById(module, id);
		} catch (ZCRMException e) {
			response.put("ok", "false");
			response.put("error", "Error: ".concat(e.getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("ok", true);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getDataRelated/{module}/{id}")
	public ResponseEntity<?> getRelatedRecordById(@PathVariable String module,@PathVariable Long id){
		configuration.configurationZoho();
		List<Map<String,Object>> response = new ArrayList<>();
		Map<String,Object> response2 = new HashMap<>();
		try {
			response = service.getRelatedListRecords(module, id);
		} catch (ZCRMException e) {
			response2.put("ok", "false");
			response2.put("error", "Error: ".concat(e.getMessage()));
			return new ResponseEntity<Map<String,Object>>(response2,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response2.put("ok", true);
		return new ResponseEntity<Map<String,Object>>(response2,HttpStatus.OK);
	}
	
	@PostMapping("/data")
	public ResponseEntity<?> sendData(@Valid @RequestBody Demo demo, BindingResult result) {
		Map<String,Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		response.put("demo", demo);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
}
