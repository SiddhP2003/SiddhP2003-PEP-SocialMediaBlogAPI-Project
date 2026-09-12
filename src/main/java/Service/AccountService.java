package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
  public AccountDAO accountDAO;



  public AccountService(){
    accountDAO = new AccountDAO();
  }

  // public AccountService(AccountDAO accountDAO){
  //     this.accountDAO = accountDAO;
  // }

  public Account register(Account account){
    if (accountDAO.getAccountByUsername(account.getUsername()) == null){
      return accountDAO.register(account);
    }
    return null;
  }

  public Account getAccountByUsername(String username){
    return accountDAO.getAccountByUsername(username);
  }

  public Account login(Account account){
    Account validAccount = accountDAO.login(account);

    if(validAccount != null){
      return validAccount;
    }
    return null;
  }


}
