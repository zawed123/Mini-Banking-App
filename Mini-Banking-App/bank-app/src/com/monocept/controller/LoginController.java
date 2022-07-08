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
import javax.servlet.http.HttpSession;

import com.monocept.model.Account;
import com.monocept.model.User;
import com.monocept.repository.AccountsRepository;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		  response.setContentType("text/html");
		  PrintWriter out=response.getWriter();
		   Account account=null;
		    String username=request.getParameter("user_name");
		    String password=request.getParameter("user_password");
		     try {
				account=AccountsRepository.getInstance().loginAuthentication(username,password);
				if(account!=null)
				{
					HttpSession session=request.getSession();	
					session.setAttribute("currentUser", account);
					//response.sendRedirect("dashboard");
					out.println("success");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		    
	}

}
