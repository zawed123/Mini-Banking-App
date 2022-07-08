package com.monocept.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monocept.model.Account;
import com.monocept.model.Transaction;
import com.monocept.repository.AccountsRepository;
import com.monocept.service.AccountService;

@WebServlet("/passbook")
public class PassbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PassbookController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("currentUser");
		if (account == null) {
			response.sendRedirect("login");
		} else {
			try {
				AccountService service = null;
				service = new AccountService(AccountsRepository.getInstance());
				List<Transaction> transactionList = service.getTransactions(account.getName());
				request.setAttribute("transactions", transactionList);
				RequestDispatcher view = request.getRequestDispatcher("Passbook.jsp");
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
