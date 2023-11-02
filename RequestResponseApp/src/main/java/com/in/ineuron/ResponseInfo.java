package com.in.ineuron;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ResponseInfo
 */
@WebServlet("/info")
public class ResponseInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	response.setContentType("image/jpg");
	
    	//Reading the data from file
    	
    	File file=new File("D:\\Servlets\\ResponseInfoApp\\shrinu.jpg");
    	FileInputStream fis=new FileInputStream(file);
    	
    	//reading the data into byte array
    	
    	byte[] b=new byte[(int) file.length()];
    	
    	fis.read(b);
    	
    	ServletOutputStream stream=response.getOutputStream();
    	stream.write(b);//writing data to stream from byte array
    	stream.flush();
    	stream.close();
    	
    	
    	
    
    }

}
