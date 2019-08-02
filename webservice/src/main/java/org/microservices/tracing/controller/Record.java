package org.microservices.tracing.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = {"Collect"}, value = "Collect", description = "API to Collect metrics from remote host")
public class Record 
{
	private final RestTemplate restTemplate = new RestTemplate();
	
	@ApiOperation("Get value of a specific component")
	@GetMapping("/data/{hostname}/{component}")
	public ResponseEntity<?> getValue(
									 @ApiParam(value = "hostname", required = true) 
									 @PathVariable String hostname,									  
									 @ApiParam(value = "component name")
									 @PathVariable String component,
									 @ApiParam(value = "metric key")
							         @RequestParam(value="key", defaultValue="") String key)
	{
		if(key.isEmpty())
		{
			return restTemplate.getForEntity("http://"+hostname+":8080/actuator/"+component, String.class); 
		}
		else
		{
			return restTemplate.getForEntity("http://"+hostname+":8080/actuator/"+component+"/"+key, String.class);
		}
	}
	
	@ApiOperation("Get all available components")
	@GetMapping("/data/{hostname}")
	public ResponseEntity<?> getValue(@ApiParam(value = "hostname", required = true) @PathVariable String hostname)
	{
		return restTemplate.getForEntity("http://"+hostname+":8080/actuator", String.class); 
	}
}
