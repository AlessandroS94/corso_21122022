package com.diemme.business.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.ChatUserService;
import com.diemme.domain.mongo.Chat;
import com.diemme.domain.mongo.ChatType;
import com.diemme.domain.mongo.Message;
import com.diemme.domain.mysql.ChatUser;
import com.diemme.domain.mysql.Role;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mongo.ChatRepository;
import com.diemme.repository.mongo.MessageRepository;
import com.diemme.repository.mysql.ChatUserRepository;
import com.diemme.wrapperForm.FormWrapperChat;

@Service
@Transactional
public class ChatUserServiceImpl implements ChatUserService {

	@Autowired
	private ChatUserRepository chatUserRepository;

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public byte[] getImageChat(String idChatMongo, int index) throws BusinessException {

		Chat chat = new Chat();
		Message message = new Message();

		chat = chatRepository.findById(idChatMongo)
				.orElseThrow(() -> new ResourceNotFoundException("FileLayout", "id", idChatMongo));

		if (!chat.getMessages().isEmpty() && chat.getMessages() != null) {
			Message[] messages = chat.getMessages().toArray(new Message[chat.getMessages().size()]);

			for (int i = 0; i < messages.length; i++) {

				if (i == index) {
					message = messages[i];
					byte[] contentImg = message.getFile();
					return contentImg;
				}
			}
		}

		return null;
	}

	@Override
	public byte[] postMEssage(String idChatMongo) throws BusinessException {
		Chat chat = new Chat();
		Message[] messages = null;
		chat = chatRepository.findById(idChatMongo)
				.orElseThrow(() -> new ResourceNotFoundException("FileLayout", "id", idChatMongo));

		if (!chat.getMessages().isEmpty() && chat.getMessages() != null) {
			messages = chat.getMessages().toArray(new Message[chat.getMessages().size()]);

		}

		return null;
	}

	@Override
	public Page<ChatUser> getAllUserChat(Integer page, Integer size, Long idUser) throws BusinessException {
		return chatUserRepository.findIdChatMongoDb(PageRequest.of(page, size), idUser);
	}

	@Override
	public Set<ChatUser> getAllChatUser(String idChatMongo) throws BusinessException {
		return chatUserRepository.findUserChatMongoDb(idChatMongo);
	}

	@Override
	public Chat getChat(String idChatMongo) {
		return chatRepository.findById(idChatMongo)
				.orElseThrow(() -> new ResourceNotFoundException("FileLayout", "id", idChatMongo));
	}

	@Override
	public void deleteChat(Long idChatUser, String idChatMongo) throws BusinessException {

		chatUserRepository.deleteUserChatMongoDb(idChatMongo);
		chatRepository.deleteById(idChatMongo);
	}

	@Override
	public void saveNewChat(FormWrapperChat formWrapperChat, MultipartFile contentImg, User userAuth)
			throws BusinessException {

		Chat chatSave = new Chat();
		ChatUser chatUser1 = new ChatUser();
		ChatUser chatUser2 = new ChatUser();
		Message message = new Message();
		String nameuser = new String();
		Set<Message> messageList = new HashSet<Message>();
		byte[] bytes = new byte[(int) contentImg.getSize()];

		nameuser = userAuth.getName() + " " + userAuth.getSurname();

		try {
			bytes = contentImg.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Role role : userAuth.getRoles()) {
			if (role.getRole().equals("CLIENT")) {
				chatUser1.setUser(userAuth);
				chatUser1.setNameProject(formWrapperChat.getNameProject());
				chatUser2.setUser(formWrapperChat.getUser());
				chatUser2.setNameProject(formWrapperChat.getNameProject());
				chatSave.setChatType(ChatType.CLIENT_DESIGNER);
			} else if (role.getRole().equals("DESIGNER")) {
				chatUser1.setUser(userAuth);
				chatUser1.setNameProject(formWrapperChat.getNameProject());
				chatUser2.setUser(formWrapperChat.getUser());
				chatUser2.setNameProject(formWrapperChat.getNameProject());
				chatSave.setChatType(ChatType.DESIGNER_PRODUCTOR);

			}
		}

		message.setMessage(formWrapperChat.getMessage());
		message.setFile(bytes);
		message.setIdUser(userAuth.getId());
		message.setName(nameuser);
		message.setDate(LocalDateTime.now());
		messageRepository.save(message);
		messageList.add(message);
		chatSave.setMessages(messageList);

		chatSave = chatRepository.insert(chatSave);
		chatUser1.setIdChatMongo(chatSave.getId());
		chatUserRepository.save(chatUser1);
		chatUser2.setIdChatMongo(chatSave.getId());
		chatUserRepository.save(chatUser2);

	}

	@Override
	public void updateChat(User userAuth, String message, MultipartFile attachment, String id)
			throws BusinessException {

		Chat chatUpdate = new Chat();
		Chat chatOld = new Chat();
		Query query = new Query();

		Message messageSave = new Message();
		Set<Message> messageList = new HashSet<Message>();
		String nameuser = new String();

		nameuser = userAuth.getName() + " " + userAuth.getSurname();

		chatOld = chatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("chat", "id", id));

		messageSave.setDate(LocalDateTime.now());
		messageSave.setIdUser(userAuth.getId());
		messageSave.setName(nameuser);
		messageSave.setMessage(message);

		if (attachment != null) {

			if (!attachment.isEmpty()) {

				byte[] bytes = new byte[(int) attachment.getSize()];
				try {
					bytes = attachment.getBytes();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				messageSave.setFile(bytes);
			}

		}
		messageList = chatOld.getMessages();
		messageList.add(messageSave);
		chatUpdate.setChatType(chatOld.getChatType());
		chatUpdate.setId(id);
		chatUpdate.setMessages(messageList);

		System.out.println("\n\n\n chat: " + chatUpdate);

		query.addCriteria(Criteria.where("_id").is(chatOld.getId()));
		Update update = new Update();
		update.set("messages", chatUpdate.getMessages());
		mongoTemplate.findAndModify(query, update, Chat.class);

	}

	@Override
	public Message saveMessage(Message message) throws BusinessException {

		Message messageSave = new Message();
		messageSave = messageRepository.save(message);

		return messageSave;
	}

}
