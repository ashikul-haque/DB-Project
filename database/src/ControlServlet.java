import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/home":
        		//System.out.println(currentUser);
        		if (session.getAttribute("username").equals("david")) {
        			davidPage(request,response, "");
        		}
        		if (session.getAttribute("username").equals("")) {
        			response.sendRedirect("login.jsp");
        		}
        		else {
        			userPage(request,response, "");
        		}
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	case "/addtree":
        		addTree(request,response);
        		break;
        	case "/quotereq":
        		quoteReqSubmission(request,response);
        		break;
        	case "/giveQuote":
        		giveQuote(request,response);
        		break;
        	case "/list": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<Client> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    private void davidPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("david view");
			request.setAttribute("listQuoteReqs", userDAO.listAllQuoteReqs());
	    	request.getRequestDispatcher("activitypage.jsp").forward(request, response);
	    }
	    
	    private void userPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("user view");
	    	if(currentUser=="")
	    		response.sendRedirect("login.jsp");
	    	Integer ClientID = userDAO.getUser(currentUser).getClientID();
	    	//System.out.println(ClientID);
			request.setAttribute("listQuoteReqs", userDAO.listClientQuoteReqs(ClientID));
	    	request.getRequestDispatcher("activitypage2.jsp").forward(request, response);
	    }
	    
	    private void addTree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Add tree");
	    	request.getRequestDispatcher("requestquote.jsp").forward(request, response);
	    }
	    
	    private void quoteReqSubmission(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Quote Submission");
	    	request.getRequestDispatcher("activitypage2.jsp").forward(request, response);
	    }
	    
	    private void giveQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Generate Quote");
	    	String quoteReqID = request.getParameter("quoteReqID");
	    	List<Quote> quotes = userDAO.listQuoteReqQuotes(Integer.parseInt(quoteReqID));
	    	request.setAttribute("quoteRequestID", quoteReqID);
	    	
	    	Quote latest = quotes.get(quotes.size() - 1);
	    	
	    	if(session.getAttribute("username").equals("david"))
	    		request.setAttribute("showReject", 0);
	    	else if(latest.getStatus().equals("pending"))
	    		request.setAttribute("showReject", 1);
	    	
	    	request.setAttribute("listQuotes", quotes);
	    	request.getRequestDispatcher("giveQuote.jsp").forward(request, response);
	    }
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 
	    	 else if(email.equals("david") && password.equals("pass1234")) {
	    		 System.out.println("Login Successful! Redirecting to David");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 davidPage(request, response, "");
	    	 }
	    	 
	    	 else if(userDAO.isValid(email, password)) 
	    	 {
			 	 
			 	 currentUser = email;
			 	 session = request.getSession();
				 session.setAttribute("username", email);
				 System.out.println("Login Successful! Redirecting");
				 userPage(request, response, "");
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String creditcard = request.getParameter("creditcard");
	   	 	String address = request.getParameter("address"); 
	   	 	String phone = request.getParameter("phone"); 	   	 	
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkAddress(address)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            Client users = new Client(email,firstName, lastName, password, creditcard, address, phone);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Address already exists");
		    		 request.setAttribute("errorOne","Registration failed: Address exists.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
	    

	     
        
	    
	    
	    
	    
	    
}
	        
	        
	    
	        
	        
	        
	    


