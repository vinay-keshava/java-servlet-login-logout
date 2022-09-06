

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String username=request.getParameter("name");
			String password=request.getParameter("pass");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/loginout","root","Vinay@123");
			String query1="select * from login where user=? and pass=?";
			PreparedStatement pt=con.prepareStatement(query1);
		    pt.setString(1,username);
		    pt.setString(2,password);
		    PrintWriter pw=response.getWriter();
		    ResultSet rs= pt.executeQuery();
		    if(rs.next())
		    {
		    	pw.write("Welcome, "+username+"! You've successfully logged in!\n");
//		        RequestDispatcher rd=request.getRequestDispatcher("servlet2");  
		    }
		    else
		    		{
//		    	pw.write("Wrong Password");
		    		
		    		 pw.write("Please Login with correct credentials provided");
				        RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
				        rd.forward(request, response);  
		    		}    	
		}
catch(SQLException e){
	System.out.println("Could not connect to the database"+e.getMessage());
	e.printStackTrace();
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}	
	
	}

}
