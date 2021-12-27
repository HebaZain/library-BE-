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
import com.google.gson.JsonArray;

import libraryManApp.BookDetails;
import libraryManApp.Response;

/**
 * Servlet implementation class DisplayBooksServlet
 */

@WebServlet(name = "DisplayBooksServlet", urlPatterns = {"/DisplayBooksServlet"})
public class DisplayBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayBooksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BookDetails> list = new ArrayList<BookDetails>();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		String DB_URL="jdbc:postgresql://localhost:5432/Library";
		String userName="postgres";
		String password="database";
		String query;
		PreparedStatement pst;
		Response resSuccess =new Response();
		Gson gsonDisplay = new Gson();
		Gson gsonList = new Gson();
		resSuccess.setStatusCode(0);
		
		String resSuccDisplay = gsonDisplay.toJson(resSuccess);
		String resList = gsonList.toJson(list);
		PrintWriter writer = response.getWriter();
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(DB_URL, userName, password);
			System.out.println("Connection Opened");
			
			query="select * from bookDetails";
			pst=con.prepareStatement(query);
			
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BookDetails bookdetail = new BookDetails();
				bookdetail.setID(rs.getInt(1));
				bookdetail.setTitle(rs.getString(2));
				bookdetail.setPublisher(rs.getString(3));
				bookdetail.setCategory(rs.getString(4));
				bookdetail.setYear(rs.getInt(5));
				bookdetail.setHide(rs.getString(6));
				list.add(bookdetail);	
			}
			System.out.println("PrintData");
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
			String json = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().
                    create().toJson(list  );
			writer.print(json);
			
			//writer.print(resSuccDisplay);
			
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
