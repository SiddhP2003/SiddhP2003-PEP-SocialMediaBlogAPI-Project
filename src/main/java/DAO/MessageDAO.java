package DAO;

import Util.ConnectionUtil;
import java.sql.*;
import Model.Message;
import java.util.*;

public class MessageDAO {
  public Connection db = ConnectionUtil.getConnection();
  public ArrayList<Message> messages = new ArrayList<Message>();


  public ArrayList<Message> getAllMessages(){
    try {
      String sql = "SELECT * FROM message";
      PreparedStatement ps = db.prepareStatement(sql);

      ps.executeQuery();

      ResultSet rs = ps.getResultSet();

      while(rs.next()){
        Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
        messages.add(message);
      }
      return messages;

    } catch(SQLException e){
      System.out.println(e.getMessage());
    }
    return messages;
  }
  
  public Message getMessage(int message_id){
    try {
      String sql = "SELECT * message WHERE message_id = ?";
      PreparedStatement ps = db.prepareStatement(sql);

      ps.setInt(1, message_id);

      ps.executeQuery();

      ResultSet rs = ps.getResultSet();

      if(rs.next()){
        Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
        return message;
      }

    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

}