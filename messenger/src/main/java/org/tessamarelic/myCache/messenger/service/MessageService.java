package org.tessamarelic.myCache.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.tessamarelic.myCache.messenger.database.DatabaseClass;
import org.tessamarelic.myCache.messenger.exceptions.NotFoundException;
import org.tessamarelic.myCache.messenger.model.Message;
import org.tessamarelic.myCache.messenger.threads.MakeThread;


public class MessageService {
	
	MakeThread m = new MakeThread();
	Thread t1 = new Thread(m);
	private Map<Long, Message> messages = DatabaseClass.getMessage();
	
	
	public MessageService() {
		//some to start off if need to test
		messages.put(1L, new Message(1, "Hello Message"));
		messages.put(2L, new Message(2, "Hello to you"));
		
		//begin thread to delete expired resources
		MakeThread m = new MakeThread();
		Thread t1 = new Thread(m);
		//t1.start();	
	}
	
	
	public List<Message> getAllMessages() {
		
		if(messages.isEmpty()) {
			throw new NotFoundException("there are no resources to return");
		}
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		
		if(message == null) {
			throw new NotFoundException("Resource id " + id + " not found");
		}
		
		return message;
	}
	
	public Message addMessage(long id) {
		return messages.get(id);
	}
	
	//store message in cache - map
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
	
	public String removeAll() {
		messages.clear();
		return "the cache has been cleared";
	}
	
}
