package com.mokalmat.libraryManApp.Servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import com.google.gson.GsonBuilder;

import libraryManApp.Books;
import libraryManApp.Response;
import libraryManApp.getConnectionLibrary;


/**
 * Servlet implementation class addBook
 */
@WebServlet(name = "addBook", urlPatterns = {"/addBook"})
public class addBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		String DB_URL="jdbc:postgresql://localhost:5432/Library";
		String userName="postgres";
		String password="database";
		Gson gson = new Gson();
		Response resSucc =new Response();
	    Response resFa =new Response();
		resSucc.setStatusCode(0);
		resFa.setStatusCode(-1);
		String jsonStrSucc = gson.toJson(resSucc);
		String jsonStrFa= gson.toJson(resFa);
		
		try {
			PrintWriter writer = response.getWriter();
			//Connection con=DriverManager.getConnection(DB_URL,userName,password);
			//System.out.println("connected successfully");
			writer.print(jsonStrSucc);
			
			
		   // getConnectionLibrary conAdd = new getConnectionLibrary();
		    
			/*PreparedStatement pst;
			String query;*/
			//Connection c1= conAdd.connect();
			/*query="insert into bookDetails(id, title, publisher, category, price) values (?, ?, ?, ?, ?)";
			pst=con.prepareStatement(query);*/
		
		 /*   InputStream in = request.getInputStream();
		    InputStreamReader streamReader = new InputStreamReader(in);
		    Books book = gson.fromJson(streamReader, Books.class);*/
			
			/*String bookID=request.getParameter("Id");
			Integer intID= Integer.parseInt(bookID);
			String title= request.getParameter("Title");  //title
			String publisher=request.getParameter("Publisher"); //publisher
			//Integer intYear= Integer.parseInt(year);
			String category= request.getParameter("Category"); //category
			String price =request.getParameter("Price");  //price*/
		    
			/*if (book == null) {
				writer.print(jsonStrFa);
				
			}
			else {
				writer.print(jsonStrSucc);
			}  */
			
			//Books book = new Books(intID,title,publisher,category,price );
			//book.setTitle(title);
			//book.setPublisher(publisher);
			//book.setYear(year);
			//book.setCategory(category);
			//System.out.println(book);
			/*Books book = new Books(1,"lm","mm","sml","10");
			pst.setInt(1, book.getID());
			pst.setString(2, book.getTitle());
			pst.setString(3, book.getPublisher());
			pst.setString(4, book.getCategory());
			pst.setString(5,book.getPrice());
			pst.executeUpdate();
			pst.addBatch();
	        pst.executeBatch();
	        con.commit();
	        PrintWriter print = response.getWriter();
	        print.println("");
	        response.sendRedirect("/success");
	        int  test=pst.executeUpdate();
	        if(test > 0) {
	        	writer.print(jsonStrSucc);
            }else {
            	writer.print(jsonStrFa);
            	}*/
		}catch(Exception e) {
			System.out.println(e.getMessage());
		} 
	        
	       /*for(int i=0 ; i<test.length;i++) {
	        	if(i > 0) {
	            	writer.write("Success");
	            	response.setStatus(200);
	            	response.setStatus(200, "Success" );
	            	System.out.println("Success");
	            }else {
	            	writer.write("Failed");
	            	response.setStatus(400);
	            	response.setStatus(400, "Failed" );
	            	System.out.println("Failed");
	            }
	        }*/
	        
	        /*if(pst.execute() == true) {
	        	writer.write("Success");
	        	response.setStatus(200);
	        }else {
	        	writer.write("Failed");
	        	response.setStatus(400);
	        }*/
        
		
		
		
		
	}

}
