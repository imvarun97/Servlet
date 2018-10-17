package com.caps.student;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
 import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 @WebServlet("/logout")
public class Logout extends HttpServlet {
	 @Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 HttpSession session=req.getSession(false);
		 if(session!=null)
		 {
			 session.invalidate();
		 }
			 
			 Cookie[] cookie=req.getCookies();
			 if(cookie!=null)
			 {
				 for(Cookie c:cookie)
				 {
					 c.getName().equals("JSESSIONID");
					 c.setMaxAge(0);
					 resp.addCookie(c);
					 resp.sendRedirect("http://localhost:8090/StudentWebApp/login.html");
				 }
			 }
		 }
		 
	 }
	 
	 


