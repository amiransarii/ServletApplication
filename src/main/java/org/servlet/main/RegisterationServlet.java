package org.servlet.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.servlet.constant.DBConstant;
import org.servlet.util.LogUtils;

/**
 * Servlet implementation class RegisterationServlet
 */
public class RegisterationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LogUtils log= new LogUtils(RegisterationServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterationServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//doGet(request, response);	
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
	    String userName= request.getParameter("userName");
		String userPass=request.getParameter("pass");
		String confirmPass=request.getParameter("cpass");
		String userEmail= request.getParameter("userEmail");
		String userCountry= request.getParameter("userCountry");
		
		if(!userPass.equals(confirmPass)) {
			out.print("Sorry UserName or Password Error!");
			RequestDispatcher rd= request.getRequestDispatcher("/register.jsp");
			rd.include(request, response);
			log.debug("Password and Confirm Password does not match");
			return ;
		}
		
		log.debug("UserName "+userName +" userPass "+userPass +" confirmPass "+confirmPass +" userEmail" +
				" userCountry"+userCountry);
		
		try {
			Class.forName(DBConstant.DBCLASSNAME);
			Connection conn=DriverManager.getConnection(DBConstant.DBURL,DBConstant.DBUSER,DBConstant.DBPASSWORD);
			PreparedStatement psmt=conn.prepareStatement("insert into registrationtable (fullName,email,password,country) values(?,?,?,?)");
			psmt.setString(1, userName);
			psmt.setString(2, userEmail);
			psmt.setString(3, userPass);
			psmt.setString(4, userCountry);
			
			int i=psmt.executeUpdate();
			if(i>0) {
				out.print("You are successfully registered...");
			}
			
		} catch(SQLException e) {
			log.error("Sql Exception "+e.getMessage());
			
		} catch(Exception e) {
			log.error(e.getMessage());
		}
		
			
	}

}
