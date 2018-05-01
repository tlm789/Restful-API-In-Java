package org.tessamarelic.myCache.messenger.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.tessamarelic.myCache.messenger.model.ErrorMessage;

@Provider //registers class
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException>{

	@Override
	public Response toResponse(NotFoundException ex) {
		
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404, "https://jersey.github.io");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}
}
