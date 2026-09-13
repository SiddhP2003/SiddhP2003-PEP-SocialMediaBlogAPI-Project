package DAO;

import Util.ConnectionUtil;
import java.sql.*;
import Model.Message;
import Model.Account;
import java.util.*;

public class MessageDAO {
  public Connection db = ConnectionUtil.getConnection();
  public ArrayList<Message> messages = new ArrayList<Message>();


  public Message createMessage(Message newMessage){
    try {
      String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?)";
      PreparedStatement ps = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      ps.setInt(1, newMessage.getPosted_by());
      ps.setString(2, newMessage.getMessage_text());
      ps.setLong(3, newMessage.getTime_posted_epoch());

      int checkInsert = ps.executeUpdate();

      if (checkInsert == 0) {
        throw new SQLException("Message was not entered into the database.");
      }

      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()){
        int generatedKey = rs.getInt(1);
        newMessage.setMessage_id(generatedKey);
      }
      return newMessage;


    } catch(SQLException e){
      e.printStackTrace();
    }

    return null;
  }

  public Account getAccountByPostedBy(int postedBy){
    try {
      String sql = "SELECT * FROM account WHERE account_id = ?";
      PreparedStatement preparedStatement = db.prepareStatement(sql);

      preparedStatement.setInt(1, postedBy);

      ResultSet rs = preparedStatement.executeQuery();
      if(rs.next()){
        Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
        return account;
      }
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }


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

  public int deleteMessage(int message_id){
    try {
      String sql = "DELETE FROM message WHERE message_id = ?";
      PreparedStatement ps = db.prepareStatement(sql);

      ps.setInt(1, message_id);

      int checkUpdate = ps.executeUpdate();

      return checkUpdate;

      
    } catch(SQLException e){
      e.printStackTrace();
    }
    return 0;
  }

}