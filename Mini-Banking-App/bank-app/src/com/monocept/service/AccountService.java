package com.monocept.service;

import java.sql.SQLException;
import java.util.List;

import com.monocept.model.Account;
import com.monocept.model.Transaction;
import com.monocept.repository.AccountsRepository;

public class AccountService {

	private AccountsRepository repository;

	public AccountService(AccountsRepository accountsRepository) {
		this.repository=accountsRepository;
	}

	public List<Account> getAccounts() throws SQLException {
		return repository.getAccountList();
	}

	public void register(Account account) throws SQLException {
		repository.register(account);
	}
	
	public Account getAccount(String name) throws SQLException {
		return repository.getAccount(name);
	}
	
    public void deposite(Account account, int amount) throws SQLException {
		repository.deposite(account, amount);
	}
	
	public void withdraw(Account account, int amount) throws SQLException {
		repository.withdraw(account, amount);
	}	
   public List<Transaction> getTransactions(String name) throws SQLException {
		return repository.getTransactions(name);
	}
	
}
