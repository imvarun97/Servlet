package com.caps.session;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 @WebServlet("/loginServ")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("sid");
		String passwd = req.getParameter("pass");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			/*
			 * 1. Load the Driver
			 */
			Class.forName("com.mysql.jdbc.Driver");
			
			/*
			 * 2. Get the DB Connection via Driver
			 */
						String dbUrl="jdbc:mysql://localhost:3306/capsv4_db?user=root&password=1387";
						
			//2nd Version of getConnection
				//FileReader in = new FileReader("E:/Files/db.properties");
				//Properties prop = new Properties();
				//prop.load(in);
				con = DriverManager.getConnection(dbUrl);
						
 			System.out.println("Connected...");
			
			/*
			 * 3. Issue the SQL query via connection
			 */
			String sql = "select * from students_info where "
					+ " sid=? and password=?";
 			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(userId));
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
 			/*
			 * 4. Process the results
			 */
			PrintWriter out = resp.getWriter();
			
			out.print("<html><head>");
			if(rs.next()){
				HttpSession session=req.getSession();
				int regno = rs.getInt("sid");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String gender = rs.getString("gender");
				passwd = rs.getString("password");
				String type = rs.getString("type");
 				out.println("<h4>"+regno+"</h4>");
				out.println("<H4>"+firstname+"</h4>");
				out.println("</h4>"+lastname+"</h4>");
				out.println("<h4>"+gender+"</h4>");
				out.println("<h4>"+passwd+"</h4>");
				out.println("<h4>"+type+"<h4>");
				out.println("*********************");
				out.println("<a href=getAllStudents>ViewAllStudents</a><br>"+"<a href=logout>logout</a>");
			}else {
				out.println("Login Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 5. close all the JDBC Objects
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
		}
	}
}