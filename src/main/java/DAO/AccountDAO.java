package DAO;

import Util.ConnectionUtil;
import java.sql.*;
import Model.Account;

public class AccountDAO {
  public Connection db = ConnectionUtil.getConnection();

  public Account register(Account newAccount) {
    try {
      String sql = "INSERT INTO account (username, password) VALUES (?,?)";

      PreparedStatement ps = db.prepareStatement(sql);

      ps.setString(1,newAccount.getUsername());
      ps.setString(2, newAccount.getPassword());

      int checkInsert = ps.executeUpdate();

      if (checkInsert == 0) {
        throw new SQLException("Member was not entered into the database.");
      }
      
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()){
        int generatedKey = rs.getInt(1);
        newAccount.setAccount_id(rs.getInt(generatedKey));
      }
      return newAccount;

    } catch (SQLException e){
      e.printStackTrace();
    }

    return null;
  }

  public Account getAccountByUsername(String username){
    Connection db = ConnectionUtil.getConnection();

    try {
      String sql = "SELECT * FROM account WHERE username = ?";
      PreparedStatement preparedStatement = db.prepareStatement(sql);

      preparedStatement.setString(1, username);
      ResultSet rs = preparedStatement.executeQuery();
      while(rs.next()){
        Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
        return account;
      }
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
}