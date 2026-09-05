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
      
      return newAccount;

    } catch (SQLException e){
      e.printStackTrace();
    }

    return null;
  }
}
