package org.tessamarelic.myCache.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.internal.Errors.ErrorMessage;
import org.tessamarelic.myCache.messenger.model.Message;
import org.tessamarelic.myCache.messenger.service.MessageService;

//import com.sun.research.ws.wadl.Response;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResources {

	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages() {
		
		return messageService.getAllMessages();
	}
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		
		message.setCreated(new Date());
		Message newMessage = messageService.addMessage(message);
		CacheControl c = new CacheControl();
		c.setMaxAge(30);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.cacheControl(c)
				.entity(newMessage)
				.build();
	}
	
	@PUT
	@Path("{id}")
	public Message updateMessage(@PathParam("id") long id, Message message) {
		message.setId(id); //the id set is the one entered on url
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteMessage(@PathParam("id") long id) {
		messageService.removeMessage(id);
	}
	
	@DELETE
	public String deleteAll() {
		return messageService.removeAll();
	}
	
	
	//make call to cache map if not there then get from text file
	@GET
	@Path("{id}")
    public Message getMessage(@PathParam("id") long id, @Context UriInfo uriInfo)  {
	
		Message  message = messageService.getMessage(id);
		String uri = getUriForSelf(uriInfo, message);
		message.addLink(uri,"self");
		return message;
    		
    }

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder().path(MessageResources.class)
				.path(Long.toString(message.getId())).build().toString();
		return uri;
				
	}
	
	
	
}
