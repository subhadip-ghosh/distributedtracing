package org.microservices.tracing.web.store;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Simple, in-memory key-value datastore.
 * 
 * @author preetdeepkumar
 *
 */
public class DataStore<T> 
{
	private final Map<String, List<T>> dataMap = new HashMap<>();
	
	public List<T> getValue(String key)
	{
		if(dataMap.containsKey(key))
		{
			return dataMap.get(key);
		}
		else
		{
			return Collections.emptyList();
		}
	}
	
	public boolean addValue(String key, T value)
	{
		if( !dataMap.containsKey(key) )
		{
			dataMap.put(key, new LinkedList<T>());
		}
		
		return dataMap.get(key).add(value);
	}
	
	public void removeValue(String key, T value)
	{
		if( dataMap.containsKey(key) )
		{
			dataMap.get(key).remove(value);
		}
	}
	
	public Map<String, List<T>> getDataMap() 
	{
		return dataMap;
	}
}
