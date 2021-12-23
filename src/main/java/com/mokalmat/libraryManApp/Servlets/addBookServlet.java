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
 * Servlet implementation class addBookServlet
 */
@WebServlet(name = "addBookServlet", urlPatterns = {"/addBookServlet"})
public class addBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String DB_URL="jdbc:postgresql://localhost:5432/Library";
		String userName="postgres";
		String password="database";
		Gson gson = new Gson();
		Response resSucc =new Response();
	    Response resFa =new Response();
	    Response hamada = new Response();
	    Response ca =new Response();
		resSucc.setStatusCode(0);
		resFa.setStatusCode(-1);
		hamada.setStatusCode(-2);
		ca.setStatusCode(-3);
		String jsonStrSucc = gson.toJson(resSucc);
		String jsonStrFa= gson.toJson(resFa);
		String jsonHamada = gson.toJson(hamada);
		String jsonCa = gson.toJson(ca);
		PrintWriter writer = response.getWriter();

		try {
			Connection connection = DriverManager.getConnection(DB_URL, userName, password);
			//Connection connection = DriverManager.getConnection(DB_URL, userName, password);
			System.out.println("Success");
			
			if(connection==null) {
				writer.print(jsonStrFa); //(-1)
			}
			else {
			writer.print(jsonStrSucc);}  //(0)
		}catch(Exception e) {
			System.out.println(e.getMessage());
        	writer.print(jsonCa);   //(-3)
		}
		writer.print(jsonHamada); //(-2)
	}

}
