package org.microservices.tracing.web.controller;

import org.microservices.tracing.web.store.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Record 
{
	@Autowired
	private DataStore<String> db;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@GetMapping("/data/{key}")
	public ResponseEntity<?> getValue(@PathVariable String key) throws JsonProcessingException
	{
		return new ResponseEntity<String>(mapper.writeValueAsString(db.getValue(key)), HttpStatus.OK);
	}
	
	@PostMapping("/data/{key}")
	@ResponseStatus(HttpStatus.OK)
	public void addValue( @PathVariable String key,
						   @RequestParam(value="value", defaultValue="") String value)
	{
		db.addValue(key, value);
	}
	
}
