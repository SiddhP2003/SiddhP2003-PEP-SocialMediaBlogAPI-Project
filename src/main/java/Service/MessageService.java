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
}
