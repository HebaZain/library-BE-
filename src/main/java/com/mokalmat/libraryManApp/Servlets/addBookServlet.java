package com.mokalmat.libraryManApp.Servlets;

import java.beans.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import libraryManApp.Books;
import libraryManApp.Response;
import libraryManApp.libraryConnection;




/**
 * Servlet implementation class addBookServlet
 */
@WebServlet(name = "addBookServlet", urlPatterns = {"/addBookServlet"})
public class addBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	libraryConnection con = new libraryConnection();
	Connection connection=null;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	  public void init() throws ServletException{
		  super.init();
		  connection= con.libraryCon();
	  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		Response resSucc =new Response();
	    Response resFa =new Response();
		resSucc.setStatusCode(0);
		resFa.setStatusCode(-1);
		String jsonStrSucc = gson.toJson(resSucc);
		String jsonStrFa= gson.toJson(resFa);
		PrintWriter writer = response.getWriter();

		try {
			//response.setHeader("Access-Control-Allow-Origin", "*");
			//response.setHeader("Access-Control-Allow-Methods", "POST");
			//Class.forName("org.postgresql.Driver");
			//String DB_URL="jdbc:postgresql://localhost:5432/Library";
			//String userName="postgres";
			//String password="database";
			String queryInsert, queryCreate, queryError;
			PreparedStatement pstCreate, pstInsert, pstError;
			//Connection connection = DriverManager.getConnection(DB_URL, userName, password);
			//if(connection==null) {
				//System.out.println("Not");
			//}
			//else {
			//System.out.println("Opened");
			//}  
			InputStream in = request.getInputStream();
		    InputStreamReader streamReader = new InputStreamReader(in);
		    Books book = gson.fromJson(streamReader, Books.class);
		    
			/*String bookID=request.getParameter("Id");
			int IntID = Integer.parseInt(bookID);
			String title=request.getParameter("title");
			String publisher = request.getParameter("publisher");
			String category =request.getParameter("category");
			String price = request.getParameter("price");
			
			Books book =new Books();
			book.setID(IntID);
			book.setTitle(title);
			book.setPublisher(publisher);
			book.setCategory(category);
			book.setPrice(price);*/
			System.out.println("Added in book class");
			System.out.println(
					"ID" +book.getID()
					+"title"+book.getTitle()
					);			
			/*queryCreate="create table bookDetails (id int, title varchar (200), publisher varchar (150), category varchar (100), year int , hide varchar(10), primary key(id))";
			pstCreate=connection.prepareStatement(queryCreate);
			pstCreate.execute();
			writer.print(jsonStrSucc);
            System.out.println("table Added");*/
			
			
			
			queryInsert="insert into bookDetails (id, title, publisher, category, year, hide) values (?, ?, ?, ?, ?, ?)";
			pstInsert=connection.prepareStatement(queryInsert);
			
			pstInsert.setInt(1, book.getID());
			pstInsert.setString(2, book.getTitle());
			pstInsert.setString(3, book.getPublisher());
			pstInsert.setString(4,book.getCategory());
			pstInsert.setInt(5, book.getYear());
			pstInsert.setString(6,"no");
			pstInsert.executeUpdate();
            //connection.commit();
            writer.print(jsonStrSucc);
            System.out.println("Inserted");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
