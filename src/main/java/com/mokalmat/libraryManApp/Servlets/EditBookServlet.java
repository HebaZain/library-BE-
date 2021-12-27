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

@WebServlet(name="EditBookServlet", urlPatterns= {"/EditBookServlet"})
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public EditBookServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		String DB_URL="jdbc:postgresql://localhost:5432/Library";
		String userName="postgres";
		String password="database";
		String query;
		PreparedStatement pst;
		Response resSuccess =new Response();
		Gson gsonSucss = new Gson();
		resSuccess.setStatusCode(0);
		Gson gsonData =new Gson();
		Gson gsonConvert =new Gson();
		String Succ =gsonSucss.toJson(resSuccess);
		Books book =new Books();
		PrintWriter writer =response.getWriter();
		try {
			
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(DB_URL, userName, password);
			System.out.println("Connection Opened");
			
			query="select * from bookDetails where id=?";
			pst=con.prepareStatement(query);
			/*InputStream in = request.getInputStream();
		    InputStreamReader streamReader = new InputStreamReader(in);
		    book = gsonConvert.fromJson(streamReader, Books.class);*/
			String id=request.getParameter("ID");
			int intID =Integer.parseInt(id);
			book.setID(intID);
			pst.setInt(1, book.getID());
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				book.setID(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setPublisher(rs.getString(3));
				book.setCategory(rs.getString(4));
				book.setYear(rs.getInt(5));
			}
			writer.print(Succ);
			System.out.println("GETED");
			System.out.println("ID:"+book.getID() + "Title: "+ book.getTitle());
			String bookData = gsonData.toJson(book);
			writer.print(bookData);
			con.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
