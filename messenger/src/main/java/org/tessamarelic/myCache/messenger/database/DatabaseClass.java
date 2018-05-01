package org.tessamarelic.myCache.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.tessamarelic.myCache.messenger.model.Message;

//stub for database - should contain functions to connect to database
public class DatabaseClass {

	private static Map<Long, Message> messages = new HashMap<>();
	
	public static Map<Long, Message> getMessage() {
		return messages;
	}

}
