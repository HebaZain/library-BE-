package com.mokalmat.libraryManApp.Servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

/**
 * Servlet implementation class EditDetailsServlet
 */
@WebServlet(name="EditDetailsServlet" , urlPatterns={"/EditDetailsServlet"})
public class EditDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		String DB_URL="jdbc:postgresql://localhost:5432/Library";
		String userName="postgres";
		String password="database";
		String query;
		PreparedStatement pst;
		Response resSuccess =new Response();
		Response resFail =new Response();
		Gson gsonSucss = new Gson();
		Gson gsonFail =new Gson();
		Gson gsonConvert =new Gson();
		resSuccess.setStatusCode(0);
		resFail.setStatusCode(-1);
		String Succ =gsonSucss.toJson(resSuccess);
		String fail=gsonFail.toJson(resFail);
		Books book =new Books();
		PrintWriter writer = response.getWriter();
		try {
			//Open Connection
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(DB_URL, userName, password);
			System.out.println("Connection opended");
			
			//Read Data from front End 
			InputStream in = request.getInputStream();
			InputStreamReader streamReader = new InputStreamReader(in);
			//Convert gson(Data) request to java
			book=gsonConvert.fromJson(streamReader, Books.class);
			//Check
			System.out.println("Data added in book , BookTitle= "+ book.getTitle());
			
			//Update Query
			query ="update bookDetails set title=?, publisher=?, category=?, year=? , hide=? where id=?";
			pst=connection.prepareStatement(query);
			
			System.out.println("llll");
			
			//Set New Data
			pst.setString(1, book.getTitle());
			pst.setString(2, book.getPublisher());
			pst.setString(3, book.getCategory());
			pst.setInt(4, book.getYear());
			pst.setString(5,"NO");
			pst.setInt(6, book.getID());
			pst.executeUpdate();
			writer.print(Succ);
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
