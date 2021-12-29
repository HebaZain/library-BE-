package com.mokalmat.libraryManApp.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import libraryManApp.Books;
import libraryManApp.Response;
import libraryManApp.UserBookDetails;


@WebServlet(name="UserBooksAction" , urlPatterns= {"/UserBooksAction"})
public class UserBooksAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<UserBookDetails> bookList = new ArrayList<UserBookDetails>();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		String DB_URL="jdbc:postgresql://localhost:5432/Library";
		String userName="postgres";
		String password="database";
		String query;
		PreparedStatement pst;
		Response resSuccess =new Response();
		Gson gsonDisplay = new Gson();
		Gson gsonList = new Gson();
		resSuccess.setStatusCode(0);
		PrintWriter writer =response.getWriter();
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(DB_URL, userName, password);
			System.out.println("Connection Opened");
			
			
			//String statusHide ="NO";
			query="select title, publisher, category, year from bookDetails ";/*' "+ statusHide +"'"*/ //where hide=No
			pst=con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			System.out.println("Query ok");
			while(rs.next()) {
				UserBookDetails book = new UserBookDetails();
				book.setTitle(rs.getString(1));
				book.setPublisher(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setYear(rs.getInt(4));
				bookList.add(book);
			}
			System.out.println("Data Geted");
			System.out.println("PrintData");
			for(int i=0; i<bookList.size(); i++) {
				System.out.println(bookList.get(i));
			}
			Collections.sort(bookList);
			System.out.println("PrintData");
			for(int i=0; i<bookList.size(); i++) {
				System.out.println(bookList.get(i));
			}
			String json = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().
                    create().toJson(bookList  );
			writer.print(json);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
