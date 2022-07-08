package com.monocept.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monocept.model.Account;

@WebServlet("/dashboard")
public class DashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DashBoard() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Account account=(Account) session.getAttribute("currentUser");
		if(account==null)
		{
			response.sendRedirect("login");
		}
		else
		{
			RequestDispatcher view = request.getRequestDispatcher("Dashboard.jsp");
			view.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
