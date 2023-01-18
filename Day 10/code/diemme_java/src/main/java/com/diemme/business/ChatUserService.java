package com.diemme.business;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.diemme.domain.mongo.Chat;
import com.diemme.domain.mongo.Message;
import com.diemme.domain.mysql.ChatUser;
import com.diemme.domain.mysql.User;
import com.diemme.wrapperForm.FormWrapperChat;


public interface ChatUserService {

	public Page<ChatUser> getAllUserChat(Integer page, Integer size, Long idUser) throws BusinessException;

	public Set<ChatUser> getAllChatUser(String idChatMongo) throws BusinessException;

	public Chat getChat(String idChatMongo) throws BusinessException;

	public void deleteChat(Long idChatUser, String idChatMongo) throws BusinessException;


	Message saveMessage(Message message) throws BusinessException;

	byte[] getImageChat(String idChatMongo, int index) throws BusinessException;

	byte[] postMEssage(String idChatMongo) throws BusinessException;

	void saveNewChat(FormWrapperChat formWrapperChat, MultipartFile contentImg, User userAuth) throws BusinessException;

	void updateChat(User userAuth, String message, MultipartFile attachment, String id) throws BusinessException;


}
