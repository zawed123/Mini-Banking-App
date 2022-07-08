package com.monocept.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.monocept.model.Account;
import com.monocept.model.Transaction;
import com.monocept.model.User;

public class AccountsRepository {

	private static List<Account> accountList;
	private Connection connection=null;

	private AccountsRepository() throws SQLException, ClassNotFoundException {
		accountList = new ArrayList<Account>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
	}

	public static AccountsRepository getInstance() throws ClassNotFoundException, SQLException {
		AccountsRepository repository=null;
		if(repository==null) {
			repository = new AccountsRepository();
		}
		return repository;
	}

	public void register(Account account) throws SQLException {
		
		   try
		   {
			    PreparedStatement statement1 = connection.prepareStatement("insert into account values(?,?,?)");
				statement1.setString(1, account.getName());
				statement1.setInt(2, account.getBalance());
				statement1.setString(3, account.getPassword());
				statement1.executeUpdate();
				Date date = new Date();  
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");  
			    String stringDate = formatter.format(date); 
			    
			    PreparedStatement statement2 = connection.prepareStatement("insert into transection values(?,?,?,?)");
			    statement2.setString(1, account.getName());
			    statement2.setInt(2, account.getBalance());
			    statement2.setString(3, "Deposit");
			    statement2.setString(4, stringDate); 
			    
			    statement2.executeUpdate();
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
	}
	public Account getAccount(String name) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from account where name=?");
		statement.setString(1, name);
		ResultSet resultSet = statement.executeQuery();
		Account account=null;
		while(resultSet.next()) {
			account = new Account(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3));
		}
		return account;
	}
	
	public List<Account> getAccountList() throws SQLException {

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from account");
		while (resultSet.next()) {
			String name = resultSet.getString("name");
			int balance = resultSet.getInt("balance");
			String password = resultSet.getString("password");
			Account account = new Account(name, balance, password);
			accountList.add(account);
		}
		return accountList;
	}
	public Account loginAuthentication(String username,String password)
	{
		Account account=null;
		try
		{
			PreparedStatement statement1 = connection.prepareStatement("select * from account where name=? and password=?");	
			statement1.setString(1, username);
			statement1.setString(2, password);
			ResultSet set=statement1.executeQuery();
			if(set.next())
			{
			    account=new Account();
				account.setName(set.getString("name"));
				account.setBalance(set.getInt("balance"));
				account.setPassword(set.getString("password"));	
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return account;
	}
	
	public List<Transaction> getTransactions(String name) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from transection where name=?");
		statement.setString(1, name);
		ResultSet resultSet = statement.executeQuery();
		Transaction transaction = null;
		List<Transaction> transactionList = new ArrayList<Transaction>();
		while(resultSet.next()) {
			transaction = new Transaction(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));
			transactionList.add(transaction);
		}
		return transactionList;
	}
	public void deposite(Account account,int amount) throws SQLException {		
			try {
				connection.setAutoCommit(false);
				PreparedStatement statement1 = connection.prepareStatement("update account set balance=? where name=?");
				statement1.setInt(1, account.getBalance()+amount);
				statement1.setString(2, account.getName());
				Date date = new Date();  
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");  
			    String stringDate = formatter.format(date);  
			    PreparedStatement statement2 = connection.prepareStatement("insert into transection values(?,?,?,?)");
				statement2.setString(1, account.getName());
				statement2.setInt(2, amount);
				statement2.setString(3, "Deposite");
				statement2.setString(4, stringDate);
				statement1.executeUpdate();
				statement2.executeUpdate();
				connection.commit();
			} catch (Exception e) {
				connection.rollback();
			}
		}
	public void withdraw(Account account, int amount) throws SQLException {
		try {
			connection.setAutoCommit(false);
			PreparedStatement statement1 = connection.prepareStatement("update account set balance=? where name=?");
			statement1.setInt(1, account.getBalance()-amount);
			statement1.setString(2, account.getName());
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");  
		    String stringDate = formatter.format(date);  
		    PreparedStatement statement2 = connection.prepareStatement("insert into transection values(?,?,?,?)");
			statement2.setString(1, account.getName());
			statement2.setInt(2, amount);
			statement2.setString(3, "Withdraw");
			statement2.setString(4, stringDate);
			int stmt1=statement1.executeUpdate();
			int stmt2=statement2.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
		}
	}
	
}
	
