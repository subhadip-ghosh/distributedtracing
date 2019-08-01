package org.microservices.tracing.web.controller.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.microservices.tracing.web.controller.Record;
import org.microservices.tracing.web.store.DataStore;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Record.class)
@AutoConfigureMockMvc
public class RecordControllerTest 
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DataStore<String> db;
	
	@Test
	public void testGet() throws Exception 
	{
		List<String> mockList = new LinkedList<>();
		mockList.add("sampledata");
		
		when(db.getValue("samplekey")).thenReturn(mockList);
		mockMvc.perform(get("/api/data/samplekey").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
	}

}
