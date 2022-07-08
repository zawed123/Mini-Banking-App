package com.monocept.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monocept.model.Account;
import com.monocept.repository.AccountsRepository;
import com.monocept.service.AccountService;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RegisterController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("register.jsp");
		view.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String username = request.getParameter("user_name");
		int balance = Integer.parseInt(request.getParameter("user_amt"));
		String password = request.getParameter("user_password");
		PrintWriter out=response.getWriter();
		try {
			Thread.sleep(2000);
			AccountsRepository repository = AccountsRepository.getInstance();
			AccountService service = new AccountService(repository);
			service.register(new Account(username, balance, password));
			//request.setAttribute("accountList", service.getAccounts());
			out.println("done");
		} catch (Exception e) {
			out.println("error");
		}
	}

}
