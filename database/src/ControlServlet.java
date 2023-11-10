import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
	    maxFileSize = 1024 * 1024 * 10,        // 10MB
	    maxRequestSize = 1024 * 1024 * 50      // 50MB
	)

public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    private static int nextQuoteRequestId = 20;
	    
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
        	case "/treeadded":
        		treeAdded(request,response);
        		break;
        	case "/giveQuote":
        		giveQuote(request,response);
        		break;
        	case "/rejectQuoteReq":
        		rejectQuoteReq(request,response);
        		break;
        		
        	case "/treeDetails":
        		treeDetails(request,response);
        		break;
        	case "/submitQuote":
        		submitQuote(request,response);
        		break;
        		
        	case "/acceptQuote":
        		acceptQuote(request,response);
        		break;
        		
        	case "/rejectQuote":
        		rejectQuote(request,response);
        		break;
        		
        	case "/quitQuote":
        		quitQuote(request,response);
        		break;
        	case "/requestQuote":
        		requestQuote(request,response);
        		break;
        		
        	case "/generateQuoteReq":
        		generateQuoteReq(request,response);
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
	    
	    private synchronized int getNextQuoteRequestId() {
	        return nextQuoteRequestId++;
	    }
	    
	    int quoteReqID = -1;
	    private void requestQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Request Quote");
	    	quoteReqID = getNextQuoteRequestId();
	    	request.setAttribute("quoteReqID", quoteReqID);
	    	request.setAttribute("treeAddStatus", "");
	    	request.getRequestDispatcher("requestquote.jsp").forward(request, response);
	    }
	    
	    private void addTree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Add tree");
	    	if(quoteReqID != -1) {
	    		request.setAttribute("quoteReqID", quoteReqID);
	    	}
	    	else {
	    		System.out.println("quoteReqID is -1");
	    	}
	    	request.getRequestDispatcher("addtree.jsp").forward(request, response);
	    }
	    
	    List<Tree> treeList = new ArrayList<>();
	    
	    private String saveFile(Part part, String fileName) throws IOException {
	        String uploadPath = "/Users/ashikulhaque/project-workspace/database/WebContent/img";  // Change this to the desired directory
	        String filePath = uploadPath + File.separator + fileName;

	        try (InputStream inputStream = part.getInputStream();
	             FileOutputStream outputStream = new FileOutputStream(filePath)) {

	            byte[] buffer = new byte[4096];
	            int bytesRead = -1;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }

	            return filePath;
	        }
	    }
	    
	    private void treeAdded(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Request Quote: Tree added");
	    	
	    	quoteReqID = Integer.parseInt(request.getParameter("quoteReqID"));
	    	String size = request.getParameter("size");
	   	 	String height = request.getParameter("height");
	   	 	String location = request.getParameter("location");
	   	 	String distance = request.getParameter("distance");
	   	 	Part picture1Part = request.getPart("picture1");
	   	 	Part picture2Part = request.getPart("picture2");
	   	 	Part picture3Part = request.getPart("picture3");
	   	 	
	   	 	saveFile(picture1Part, quoteReqID + "_tree1.jpg");
	   	 	saveFile(picture2Part, quoteReqID + "_tree2.jpg");
	   	 	saveFile(picture3Part, quoteReqID + "_tree3.jpg");
	   	 	
	   	 	String picture1Path = quoteReqID + "_tree1.jpg";
	   	 	String picture2Path = quoteReqID + "_tree2.jpg";
	   	 	String picture3Path = quoteReqID + "_tree3.jpg";
	   	 	
	   	 	System.out.println(picture1Path);
	   	 	
	   	 	//TODO: photo handling
	    	
	   	 	Tree tree = new Tree(quoteReqID, size, height, location, distance, picture1Path, picture2Path, picture3Path);
	   	 	treeList.add(tree);
	 		//userDAO.insert(tree);
	 		
	   	 	
	 		request.setAttribute("quoteReqID", quoteReqID);
	    	request.setAttribute("treeAddStatus", "Tree Added");
	    	request.getRequestDispatcher("requestquote.jsp").forward(request, response);
	    }
	    
	    private void generateQuoteReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Quote Submission");
	    	
	    	Client client = userDAO.getUser(currentUser);
	    	LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String currentDateAsString = currentDate.format(formatter);
	    	String status = "pending";
	    	String note = request.getParameter("note");
	    	System.out.println(note);
	    	
	    	
	    	
	    	QuoteRequest quoteReq = new QuoteRequest(quoteReqID, client.getClientID(), currentDateAsString, status, note);
	    	userDAO.insert(quoteReq);
	    	//System.out.println("Quote added");
	    	
	    	for (Tree tree : treeList) {
	    		tree.setquoteReqID(quoteReqID);
	    		userDAO.insert(tree);
	    	}
	    	
	    	userPage(request,response, "");
	    	//request.getRequestDispatcher("activitypage2.jsp").forward(request, response);
	    }
	    
	    private void rejectQuoteReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Reject Quote Req");
	    	String quoteReqID = request.getParameter("quoteReqID");
	    	
	    	
	    	//change status from pending to reject
	    	String status = "rejected";
	    	if(userDAO.update(Integer.parseInt(quoteReqID), status)) {
	    		davidPage(request,response, "");
	    	}
	    	else {
	    		System.out.println("Update failed for rejectQuoteReq");
	    		davidPage(request,response, "");
	    	}
	    	
	    }
	    
	    private void acceptQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Accept Quote");
	    	String quoteID = request.getParameter("quoteID");
	    	
	    	
	    	//change status from pending to reject
	    	String status = "accepted";
	    	if(userDAO.updateQuote(Integer.parseInt(quoteID), status)) {
	    		userDAO.updateQuoteReqStatus(Integer.parseInt(quoteID), status);
	    		userPage(request,response, "");
	    	}
	    	else {
	    		System.out.println("Update failed for acceptQuote");
	    		userPage(request,response, "");
	    	}
	    }
	    
	    private void rejectQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Reject Quote");
	    	String quoteID = request.getParameter("quoteID");
	    	String note = request.getParameter("note");
	    	note = "\nclient: "+ note;
	    	System.out.println(quoteID);
	    	
	    	
	    	//change status from pending to reject
	    	String status = "rejected";
	    	if(userDAO.updateQuote(Integer.parseInt(quoteID), status, note)) {
	    		userPage(request,response, "");
	    	}
	    	else {
	    		System.out.println("Update failed for rejectQuote");
	    		userPage(request,response, "");
	    	}
	    }
	    
	    private void quitQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Cancel QuoteReq");
	    	String quoteID = request.getParameter("quoteID");
	    	String note = request.getParameter("note");
	    	note = "\nclient: "+ note;
	    	System.out.println(quoteID);
	    	
	    	
	    	//change status from pending to reject
	    	String status = "rejected";
	    	if(userDAO.updateQuote(Integer.parseInt(quoteID), status, note)) {
	    		status = "cancelled";
	    		userDAO.updateQuoteReqStatus(Integer.parseInt(quoteID), status);
	    		userPage(request,response, "");
	    	}
	    	else {
	    		System.out.println("Update failed for rejectQuote");
	    		userPage(request,response, "");
	    	}
	    }
	    
	    private void treeDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Tree details view");
	    	
	    	String quoteReqID = request.getParameter("quoteReqID");
			request.setAttribute("listTrees", userDAO.listTrees(Integer.parseInt(quoteReqID)));
			request.setAttribute("quoteReqID", quoteReqID);
			
	    	request.getRequestDispatcher("treedetails.jsp").forward(request, response);
	    }
	    
	    private void giveQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Generate Quote");
	    	String quoteReqID = request.getParameter("quoteReqID");
	    	
	    	
	    	
	    	String status = "quoted";
	    	if(!userDAO.update(Integer.parseInt(quoteReqID), status)) {
	    		System.out.println("Update failed for giveQuote");
	    	}
	    	
	    	
	    	System.out.println("Update success for giveQuote");
	    	List<Quote> quotes = userDAO.listQuoteReqQuotes(Integer.parseInt(quoteReqID));
	    	request.setAttribute("quoteRequestID", quoteReqID);
	    	
	    	//Quote latest = quotes.get(quotes.size() - 1);
	    	
	    	if(session.getAttribute("username").equals("david"))
	    		request.setAttribute("showReject", 1);
	    	else
	    		request.setAttribute("showReject",0);
	    	
	    	request.setAttribute("listQuotes", quotes);
	    	request.getRequestDispatcher("giveQuote.jsp").forward(request, response);
	    }
	    
	    private void submitQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Submit Quote");
	    	String quoteReqID = request.getParameter("quoteRequestID");
	    	System.out.println(quoteReqID);
	    	
	    	String price = request.getParameter("price");
	    	String workPeriodStartDate = request.getParameter("workPeriodStartDate");
	    	String workPeriodEndDate = request.getParameter("workPeriodEndDate");
	    	
	    	LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String currentDateAsString = currentDate.format(formatter);
	        
			String status = "pending";
			String note = request.getParameter("note");
	    	
			Quote quote = new Quote(Integer.parseInt(quoteReqID), Double.parseDouble(price), workPeriodStartDate, workPeriodEndDate, currentDateAsString, status, note);
	    	userDAO.insert(quote);
	    	//System.out.println("insert success");
	    	
	    	
	    	
	    	List<Quote> quotes = userDAO.listQuoteReqQuotes(Integer.parseInt(quoteReqID));
	    	request.setAttribute("quoteRequestID", quoteReqID);
	    	
	    	Quote latest = quotes.get(quotes.size() - 1);
	    	
	    	if(session.getAttribute("username").equals("david"))
	    		request.setAttribute("showReject", 1);
	    	else
	    		request.setAttribute("showReject",0);
	    	
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
				 currentUser = email;
				 rootPage(request, response, "");
	    	 }
	    	 
	    	 else if(email.equals("david") && password.equals("pass1234")) {
	    		 System.out.println("Login Successful! Redirecting to David");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 currentUser = email;
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
	        
	        
	    
	        
	        
	        
	    


