package org.twinnation.springasbackend.dto;


import com.google.gson.Gson;


public class ServerMessage {
	
	private boolean error;
	private String message;
	
	
	public ServerMessage(boolean error, String message) {
		this.error = error;
		this.message = message;
	}
	
	
	public boolean isError() {
		return error;
	}
	
	
	public void setError(boolean error) {
		this.error = error;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	
}
