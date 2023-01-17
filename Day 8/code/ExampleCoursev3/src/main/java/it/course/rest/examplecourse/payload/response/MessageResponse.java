package it.course.rest.examplecourse.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class MessageResponse {

	@Getter
	@Setter
	private String message;

	public MessageResponse(String message) {
	    this.message = message;
	  }
	@Getter
	@Setter
	private Map<String,String> mapMessage;

	public MessageResponse(Map<String,String> mapMessage) {
	    this.mapMessage = mapMessage;
	  }
}
