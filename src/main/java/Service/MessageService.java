package Service;

import DAO.MessageDAO;
import Model.Message;
import java.util.*;

public class MessageService {
  public MessageDAO messageDAO;


  public MessageService(){
    messageDAO = new MessageDAO();
  }

  public ArrayList<Message> getAllMessages(){
    return messageDAO.getAllMessages();
  }

  public Message getMessage(int message_id){
    return messageDAO.getMessage(message_id);
  }
}
