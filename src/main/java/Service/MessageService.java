package Service;

import DAO.MessageDAO;
import Model.Message;
import Model.Account;
import java.util.*;

public class MessageService {
  public MessageDAO messageDAO;


  public MessageService(){
    messageDAO = new MessageDAO();
  }

  public Message createMessage(Message message){
    if (messageDAO.getAccountByPostedBy(message.getPosted_by()) != null){
      return messageDAO.createMessage(message);
    }
    return null;
  }

  public Account getAccountByPostedBy(int posted_by){
    return messageDAO.getAccountByPostedBy(posted_by);
  }

  public ArrayList<Message> getAllMessages(){
    return messageDAO.getAllMessages();
  }

  public Message getMessage(int message_id){
    return messageDAO.getMessage(message_id);
  }

  public int deleteMessage(int message_id){
    return messageDAO.deleteMessage(message_id);
  }

}
