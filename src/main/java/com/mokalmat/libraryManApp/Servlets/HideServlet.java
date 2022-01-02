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

import libraryManApp.BookDetails;
import libraryManApp.Books;
import libraryManApp.Response;
import libraryManApp.libraryConnection;

/**
 * Servlet implementation class HideServlet
 */
@WebServlet(name="HideServlet", urlPatterns= {"/HideServlet"})
public class HideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	libraryConnection con = new libraryConnection();
	Connection connection=null;
	
	public void init() throws ServletException{
		super.init();
		connection=con.libraryCon();
	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.setHeader("Access-Control-Allow-Origin", "*");
		//response.setHeader("Access-Control-Allow-Methods", "GET");
		/*String DB_URL="jdbc:postgresql://localhost:5432/Library";
		String userName="postgres";
		String password="database";*/
		String query;
		PreparedStatement pst;
		Response resSuccess =new Response();
		Gson gsonSucss = new Gson();
		resSuccess.setStatusCode(0);
		String Succ =gsonSucss.toJson(resSuccess);
		PrintWriter writer = response.getWriter();
		try {
			/*Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(DB_URL, userName, password);
			System.out.println("Connection Opened");*/
			
			query="update bookDetails set hide=? where id=?";
			pst=connection.prepareStatement(query);
			
			String id= request.getParameter("ID");
			int intID = Integer.parseInt(id);
			
			BookDetails book = new BookDetails();
			book.setID(intID);
			book.setHide("Yes");
			
			pst.setString(1, book.getHide());
			pst.setInt(2, book.getID());
			pst.executeUpdate();
			writer.print(Succ);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


}
