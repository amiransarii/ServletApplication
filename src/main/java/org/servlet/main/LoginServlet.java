package org.servlet.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.servlet.dboperation.DbOperation;
import org.servlet.util.LogUtils;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//create the log file 
	private static LogUtils log= new LogUtils(LoginServlet.class.getSimpleName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String userName=request.getParameter("userName");
		String userPass=request.getParameter("userPassword");
		if(DbOperation.isValidateUser(userName, userPass)) {
			out.print("You are successfully logged in!");  
            out.print("<br>Welcome, "+userName);  
			 request.getRequestDispatcher("menulink.html").include(request, response);
			 Cookie ck= new Cookie("name",userName);
			 response.addCookie(ck);
		}
		else {
			out.print("Sorry username or password error");
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}
		out.close();
		
	}

}
