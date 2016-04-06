package vendor.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import vendor.service.EmployeeLoginService;

/**
 * Servlet implementation class EmployeeLoginController
 */
public class EmployeeLoginController extends HttpServlet {
	/**
	 * @author 971204
	 * @Class EmployeeLoginController
	 * @CreationDate 18 July, 2015
	 */
	
	private static final Logger logger=Logger.getLogger(EmployeeLoginController.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeLoginController() {
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
		if("employeeView".equalsIgnoreCase(action)){
			
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			
			EmployeeLoginService employeeLoginService = new EmployeeLoginService();
			
			try {
			
				String status = employeeLoginService.getLogin(userId, password);
				System.out.println(status);
				
				if(!status.equalsIgnoreCase("Failed")){
					//HttpSession session = request.getSession();
					logger.debug("welcome!!");
				RequestDispatcher rd = request.getRequestDispatcher("employeeLoginView.jsp");
				HttpSession httpSession=request.getSession(true);
				httpSession.setAttribute("vendorId", status);
				request.setAttribute("vendorId", userId);
				rd.forward(request, response);
				}else{
					logger.debug("authentication failed!!");
					RequestDispatcher rd = request.getRequestDispatcher("employeeLogin.jsp");
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
				logger.error("Invalid UserId and Password",e);
			}
			
			
		}
		
		
	}

}
