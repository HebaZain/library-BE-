package com.mokalmat.libraryManApp.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
import libraryManApp.libraryConnection;

@WebServlet(name="SearchServlet" , urlPatterns= {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	libraryConnection con = new libraryConnection();
	Connection connection=null;
	
	public void init() throws ServletException{
		super.init();
		connection=con.libraryCon();
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.setHeader("Access-Control-Allow-Origin", "*");
		//response.setHeader("Access-Control-Allow-Methods", "GET");
		/*String DB_URL="jdbc:postgresql://localhost:5432/Library";
		String userName="postgres";
		String password="database";*/
		List <Books> bookList = new ArrayList<Books>();
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
			/*Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(DB_URL, userName, password);
			System.out.println("Connection Opened");*/
			
			query = "select id, title, publisher, category, year from bookDetails where title like ?"; //like -> =
			pst= connection.prepareStatement(query);
			
			//'"+BookName+"%'
			String BookName = request.getParameter("title");
			book.setTitle("%"+BookName+"%");
			pst.setString(1,book.getTitle());
			
			System.out.print("Title is :" +book.getTitle());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Books bookresp = new Books();
				bookresp.setID(rs.getInt(1));
				bookresp.setTitle(rs.getString(2));
				System.out.println("T"+bookresp.getTitle());
				bookresp.setPublisher(rs.getString(3));
				bookresp.setCategory(rs.getString(4));
				bookresp.setYear(rs.getInt(5));
				bookList.add(bookresp);
			}
			
			String json = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().
                    create().toJson(bookList);
			writer.print(json);
			
			/*System.out.print("ID is :" +book.getID() + "publ"+ book.getPublisher());
			
			String data = respData.toJson(book);
			writer.print(data);*/
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}

//Old code
/* query = "select id, title, publisher, category, year from bookDetails where title=?"; //like -> =
			pst= connection.prepareStatement(query);
			
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
		*/
