package com.caps.session;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Driver;

@WebServlet("/getAllStudents")
public class ViewAllStudentsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session= req.getSession(false);
		PrintWriter out=resp.getWriter();
		if(session!=null)
		{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			/*
			 * 1. Load the Driver
			 */
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");
			
			/*
			 * 2. Get the DB Connection via Driver
			 */
						String dbUrl="jdbc:mysql://localhost:3306/capsv4_db"
								+ "?user=root&password=1387";
			con = DriverManager.getConnection(dbUrl); //1st version of getConnection

			System.out.println("Connected...");
			
			/*
			 * 3. Issue the SQL query via connection
			 */
			String sql = "select * from students_info";

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			/*
			 * 4. Process the results
			 */

			out.print("<html>");
			out.print("<body background-color='black'>");
			out.print("<h1 align='center'>Students Info</h1>");
			out.print("<table border='1px solid' bordercolor='teal' cellpadding='10px' cellspacing='0px'>");
			out.print("<tr><th>SID</th><th>FirstName</th><th>LastName</th><th>Gender</th><th>Password<th>Type</th></tr>");
			while(rs.next()){
				out.print("<tr>");
				out.print("<td>"+rs.getInt("sid")+"</td>");
				out.print("<td>"+rs.getString("firstname")+"</td>");
				out.print("<td>"+rs.getString("lastname")+"</td>");
				out.print("<td>"+rs.getString("gender")+"</td>");
				out.print("<td>"+rs.getString("password")+"</td>");
				out.print("<td>"+rs.getString("type")+"</td>");
				out.print("</tr>");
				
				
			}
			out.print("<a href=logout>Logout</a>");
			out.print("</table>");
			out.print("</body>");
			out.print("</html>");
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		finally{
			try {
				con.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}else	
		{   out.println("<html></head>");
			out.println("<h4>Please Do Login!</h4>");
			out.print("<a href=http://localhost:8080/Session/login.html>Login</a>");
			out.println("</head></html>");
		}
			
		}
		
		

}



