package com.mokalmat.libraryManApp.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import libraryManApp.Books;
import libraryManApp.Response;

@WebServlet(name="SearchServlet" , urlPatterns= {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		String DB_URL="jdbc:postgresql://localhost:5432/Library";
		String userName="postgres";
		String password="database";
		String query;
		PreparedStatement pst;
		Books book = new Books();
		Response resFail =new Response();
		resFail.setStatusCode(-1);
		Gson resFailgson = new Gson();
		String Fail = resFailgson.toJson(resFail);
		Gson respData = new Gson();
		PrintWriter writer= response.getWriter();
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(DB_URL, userName, password);
			System.out.println("Connection Opened");
			
			query = "select id, title, publisher, category, year from bookDetails where title=?";
			pst= con.prepareStatement(query);
			
			String BookName = request.getParameter("title");
			book.setTitle(BookName);
			pst.setString(1, book.getTitle());
			
			System.out.print("Title is :" +book.getTitle());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				book.setID(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setPublisher(rs.getString(3));
				book.setCategory(rs.getString(4));
				book.setYear(rs.getInt(5));
			}
			
			System.out.print("ID is :" +book.getID() + "publ"+ book.getPublisher());
			
			String data = respData.toJson(book);
			writer.print(data);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
