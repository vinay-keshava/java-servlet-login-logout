

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
//			
//			String dbURL=;
//			String USER="root";
//			String PASS="Vinay@123";			
			try {
				PrintWriter pw=response.getWriter();
				Class.forName("com.mysql.cj.jdbc.Driver");
				String username=request.getParameter("name");
				String password=request.getParameter("pass");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/loginout","root","Vinay@123");
				String query1="insert into login values(?,?)";
				PreparedStatement pt=con.prepareStatement(query1);
			    pt.setString(1,username);
			    pt.setString(2,password);
			    pt.execute();
			    
			    pw.write("Please Login Now with the Same credentials provided");
		        RequestDispatcher rd=request.getRequestDispatcher("/Login.html");  
		        rd.forward(request, response);  
			}
	
	catch(SQLException e){
		System.out.println("Could not connect to the database"+e.getMessage());
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}

}
















//
//<servlet>
//<servlet-name>SignUp</servlet-name>
//	<servlet-class>SignUp</servlet-class>
//</servlet>
//<servlet>
//<servlet-name>login</servlet-name>
//	<servlet-class>Login</servlet-class>
//</servlet>
//
//
//<servlet-mapping>
//<servlet-name>login</servlet-name>
//<url-pattern>/login</url-pattern>
//</servlet-mapping>
//
//
//<servlet-mapping>
//<servlet-name>SignUp</servlet-name>
//<url-pattern>/signup</url-pattern>
//</servlet-mapping>

