package com.diemme.business.interfaces;


public interface EmailService {
	
	public void sendContact(String from, String subject, String body, String sender) throws RuntimeException;	
	
	public void sendUserActive(String from, String sender) throws RuntimeException;

	void sendNotifyClientMessage(String from, String sender)throws RuntimeException;

	void sendNotifyDesignerMessage(String from, String sender)throws RuntimeException;

	void sendNotifyNewOrder(String from, String sender, String order)throws RuntimeException;

	void sendNotifyProductorMessage(String from, String sender)throws RuntimeException;

	void sendChangeStatusOrder(String from, String sender, String order, Boolean status)throws RuntimeException;

	void sendNotifyOrderShip(String from, String sender, String order, String address)throws RuntimeException;

	void sendUserActivated()throws RuntimeException;		


}
