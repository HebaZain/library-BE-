package com.mokalmat.libraryManApp.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import libraryManApp.Books;
import libraryManApp.Response;

@WebServlet(name="DeleteBookServlet" , urlPatterns= {"/DeleteBookServlet"})
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		String DB_URL="jdbc:postgresql://localhost:5432/Library";
		String userName="postgres";
		String password="database";
		String query;
		PreparedStatement pst;
		Response resSuccess =new Response();
		Gson gsonSucss = new Gson();
		resSuccess.setStatusCode(0);
		String Succ =gsonSucss.toJson(resSuccess);
		Books book =new Books();
		PrintWriter writer =response.getWriter();
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(DB_URL, userName, password);
			System.out.println("Connection Opened");
			
			query="delete from bookDetails where id=?";
			pst=con.prepareStatement(query);
			
			String ID = request.getParameter("ID");
			int intID = Integer.parseInt(ID);
			book.setID(intID);
			pst.setInt(1, book.getID());
			
			System.out.println("Get ID is:" + book.getID());
			pst.executeUpdate();
			writer.print(Succ);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


}
