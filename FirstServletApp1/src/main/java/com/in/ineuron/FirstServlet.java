package com.in.ineuron;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	

    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException { // TODO Auto-generated method stub 

    System.out.println("The Received Request is of Type: GET"); 
    //String Name=request.getParameter("username");
    	
    //System.out.println("Entered Name:"+Name);

    }

    @Override public void doPost(HttpServletRequest request, HttpServletResponse 
    response) throws ServletException { // TODO Auto-generated method stub 
    System.out.println("The Received Request is of Type: POST"); 
    //String Name=request.getParameter("username"); System.out.println("Entered Name:"+Name);

    	}

}
