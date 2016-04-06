package vendor.servlet;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vendor.bean.User;
import vendor.service.LoginService;
import vendor.service.VendorService;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	/**
	 * @author 971204
	 * @Class LoginController
	 * @CreationDate 17 July, 2015
	 */
	
	
	private static final Logger logger=Logger.getLogger(LoginController.class);
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		if("loginUser".equals(action)){
			
			
			
			
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			/*java.util.Enumeration params = request.getParameterNames();
			while(params.hasMoreElements()) 
			{ 	String paramName = (String) params.nextElement(); 
				String paramValue = request.getParameter(paramName);
				System.out.println(paramName+" "+paramValue);
			}*/
			System.out.println(userId+" "+password);
			User user = new User();
			user.setUserId(userId);
			user.setPassword(password);
			
			LoginService loginService = new LoginService();
			
			try {
				String status = loginService.loginUser(userId, password);
				System.out.println(status);
				//request.setAttribute("user", user);
				if(!status.equalsIgnoreCase("Failed")){
					//HttpSession session = request.getSession();
					logger.debug("you are successfully logged in");
					
				RequestDispatcher rd = request.getRequestDispatcher("vendorlogin.jsp");
				HttpSession httpSession=request.getSession(true);
				httpSession.setAttribute("vendorId", status);
				request.setAttribute("vendorId", userId);
				rd.forward(request, response);
				}else{
					
					
					RequestDispatcher rd = request.getRequestDispatcher("Home_Vendor.jsp");
					PrintWriter printWriter=response.getWriter();
					String Error="<div class=\"alert alert-danger alert-dismissable\"> ";
					Error+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
					Error+=" aria-hidden=\"true\">";
					Error+="&times";
					Error+="</button>";
					Error+="Error ! Invalid UserId and Password.";
					Error+="</div>";
					request.setAttribute("error",Error);
					printWriter.println(Error);
					rd.forward(request, response);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("authentication failed",e);
			}
			
		}
		
	}

}
