package com.monocept.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monocept.model.Account;
import com.monocept.repository.AccountsRepository;
import com.monocept.service.AccountService;

@WebServlet("/transactions")
public class TransectionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TransectionController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("transactions.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Account account1 = (Account) session.getAttribute("currentUser");
		if (account1 == null) {
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			view.forward(request, response);
		}
		else
		{
			String user=account1.getName();
			int amount = Integer.parseInt(request.getParameter("amount"));
			String transactionType = request.getParameter("type");
			AccountService service;
			try {
				service = new AccountService(AccountsRepository.getInstance());
				Account account2 = service.getAccount(user);
				if (transactionType.equalsIgnoreCase("Deposite")) {
					service.deposite(account2, amount);
					account1.setBalance(account1.getBalance()+amount);
					out.println("Diposit");
				}
				else if((account1.getBalance()-amount)>=500)
				{
					service.withdraw(account2, amount);
					account1.setBalance(account1.getBalance()-amount);
					out.println("Withdraw");
				}
				else
				{
				  out.println("insufficient");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
