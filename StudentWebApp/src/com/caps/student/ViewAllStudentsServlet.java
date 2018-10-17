package com.caps.student;
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
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		PrintWriter out=resp.getWriter();
		HttpSession session=req.getSession(false);
		if(session!=null)
		{
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
			String sql = "select * from emp_info";

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			/*
			 * 4. Process the results
			 */

			out.print("<html>");
			out.print("<body background-color='black'>");
			out.print("<h1 align='center'>Employee Info</h1>");
			out.print("<table border='1px solid' bordercolor='teal' cellpadding='10px' cellspacing='0px'>");
			out.print("<tr><th>employee_id</th><th>Name</th><th>email</th><th>salary</th></tr>");
			while(rs.next()){
				
				
					out.print("<tr>");
					out.print("<td>"+rs.getInt("emp_id")+"</td>");
					out.print("<td>"+rs.getString("name")+"</td>");
					out.print("<td>"+rs.getString("email")+"</td>");
					out.print("<td>"+rs.getDouble("salary")+"</td>");
					out.print("</tr>");
					
					
				
				
					
					
				
				
				
				/*int id = rs.getInt("emp_id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Double salary = rs.getDouble("salary");
				
				System.out.println(id);
				out.println(id);
				out.println(name);
				out.println(email);
				out.println(salary);
				out.println("*********************");*/
			}
			
			out.println("<a href=logout>Logout</a><br>");
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
		{
			out.println("Please Do Login!!!");
						
		}
		

}
}