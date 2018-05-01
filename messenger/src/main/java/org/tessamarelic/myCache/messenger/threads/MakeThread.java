package org.tessamarelic.myCache.messenger.threads;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.tessamarelic.myCache.messenger.database.DatabaseClass;
import org.tessamarelic.myCache.messenger.model.Message;

public class MakeThread implements Runnable {
	
	Date currentTime = new Date();
	int timeTillExpired = 30;
	private Map<Long, Message> messages = DatabaseClass.getMessage();

	public void run() {
        while(true) {
            try {
                //Thread.sleep(1000*60*60);
                Thread.sleep(30000);
                Iterator<Entry<Long,Message>> it = messages.entrySet().iterator();
        			while(it.hasNext()) {
        				Entry<Long,Message> resource = it.next();
        				Date resourceTime = resource.getValue().getCreated();
        				double timeDifference = (currentTime.getTime() - resourceTime.getTime())/1000;
        					if(timeDifference >= timeTillExpired) {
        						long id = resource.getValue().getId();
        						messages.remove(id);
        					}
        						
        			}
            } catch (InterruptedException ie) {
            }
        }
    }
		
}
