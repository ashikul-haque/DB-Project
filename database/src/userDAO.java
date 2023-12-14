import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<Client> listAllUsers() throws SQLException {
        List<Client> listUser = new ArrayList<Client>();        
        String sql = "SELECT * FROM Client";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String email = resultSet.getString("Email");
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String password = resultSet.getString("Password");
            String creditcard = resultSet.getString("CreditCardInformation");
            String address = resultSet.getString("Address"); 
            String phone = resultSet.getString("PhoneNumber");

             
            Client users = new Client(email,firstName, lastName, password, creditcard, address, phone);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(Client users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into Client(Email, FirstName, LastName, Password, CreditCardInformation,Address,PhoneNumber) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getCreditcard());
			preparedStatement.setString(6, users.getAddress());		
			preparedStatement.setString(7, users.getPhone());		

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void insert(Tree tree) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into Tree(QuoteRequestID, Size, Height, Location, DistanceToHouse, Picture1, Picture2, Picture3) values (?, ?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1, tree.getquoteReqID());
			preparedStatement.setString(2, tree.getSize());
			preparedStatement.setString(3, tree.getHeight());
			preparedStatement.setString(4, tree.getLocation());
			preparedStatement.setString(5, tree.getDistanceToHouse());
			preparedStatement.setBytes(6, tree.getPicture1());		
			preparedStatement.setBytes(7, tree.getPicture2());
			preparedStatement.setBytes(8, tree.getPicture3());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void insert(OrderOfWork order) throws SQLException {
    	connect_func();         
		String sql = "INSERT INTO OrderOfWork (QuoteID, DateCreated, Status) VALUES (?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1, order.getQuoteID());
			preparedStatement.setString(2, order.getDateCreated());
			preparedStatement.setString(3, order.getStatus());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void insert(Bill bill) throws SQLException {
    	connect_func();         
		String sql = "INSERT INTO Bill (QuoteRequestID, AmountDue, DateIssued, Status, Note) VALUES (?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, bill.getquoteReqID());
			preparedStatement.setDouble(2, bill.getAmountDue());
			preparedStatement.setString(3, bill.getDateIssued());
			preparedStatement.setString(4, bill.getStatus());
			preparedStatement.setString(5, bill.getNote());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void insert(BillDispute dispute) throws SQLException {
    	connect_func();         
		String sql = "INSERT INTO BillDispute (BillID, User, TimeAndDate, Changelog) VALUES (?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, dispute.getBillID());
			preparedStatement.setString(2, dispute.getuser());
			preparedStatement.setString(3, dispute.getTimeAndDate());
			preparedStatement.setString(4, dispute.getChangelog());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    
    public void insert(QuoteRequest quoteReq) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "INSERT INTO QuoteRequest (QuoteRequestID, ClientID, DateSubmitted, Status, ClientNote) values (?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1, quoteReq.getQuoteRequestID());
			preparedStatement.setInt(2, quoteReq.getClientID());
			preparedStatement.setString(3, quoteReq.getDateSubmitted());
			preparedStatement.setString(4, quoteReq.getStatus());
			preparedStatement.setString(5, quoteReq.getClientNote());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void insert(Quote quote) throws SQLException {
    	connect_func();         
		String sql = "INSERT INTO Quote (QuoteRequestID, Price, WorkPeriodStartDate, WorkPeriodEndDate, DateSubmitted, Status, Note) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1, quote.getQuoteRequestID());
			preparedStatement.setDouble(2, quote.getPrice());
			preparedStatement.setString(3, quote.getWorkPeriodStartDate());
			preparedStatement.setString(4, quote.getWorkPeriodEndDate());
			preparedStatement.setString(5, quote.getDateSubmitted());
			preparedStatement.setString(6, quote.getStatus());
			preparedStatement.setString(7, quote.getNote());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM Client WHERE Email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(Client users) throws SQLException {
        String sql = "update Client set FirstName=?, LastName =?,Password = ?,CreditCardInformation=?,Address=?, PhoneNumber =? where Email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getCreditcard());
		preparedStatement.setString(6, users.getAddress());			
		preparedStatement.setString(7, users.getPhone());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public boolean update(int quoteReqID, String status) throws SQLException {
        String sql = "UPDATE QuoteRequest SET Status=? WHERE QuoteRequestID = ?";
        connect_func();

        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, quoteReqID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;
    }
    
    public boolean updateOrder(int orderID, String status) throws SQLException {
        String sql = "UPDATE OrderOfWork SET Status=? WHERE OrderOfWorkID = ?";
        connect_func();

        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, orderID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;
    }
    
    public boolean updateBill(int billID, String status) throws SQLException {
        String sql = "UPDATE Bill SET Status=? WHERE BillID = ?";
        connect_func();

        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, billID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;
    }
    
    public boolean updateBill(int billID, double amountDue) throws SQLException {
        String sql = "UPDATE Bill SET AmountDue=? WHERE BillID = ?";
        connect_func();

        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setDouble(1, amountDue);
        preparedStatement.setInt(2, billID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;
    }
    
    public boolean updateQuoteReqStatus(int quoteID, String status) throws SQLException {
        String sqlSelect = "SELECT QuoteRequestID FROM Quote WHERE QuoteID = ?";
        String sqlUpdate = "UPDATE QuoteRequest SET Status=? WHERE QuoteRequestID = ?";

        connect_func();

        // Fetch the QuoteRequestID associated with the given QuoteID
        preparedStatement = connect.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, quoteID);
        ResultSet resultSet = preparedStatement.executeQuery();

        int quoteRequestID = 0;
        if (resultSet.next()) {
            quoteRequestID = resultSet.getInt("QuoteRequestID");
        }

        // Update the row in QuoteRequest with the new Status
        preparedStatement = connect.prepareStatement(sqlUpdate);
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, quoteRequestID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;

        // Close resources
        resultSet.close();
        preparedStatement.close();

        return rowUpdated;
    }

    
    public boolean updateQuote(int quoteID, String status) throws SQLException {
        String sql = "UPDATE Quote SET Status=? WHERE QuoteID = ?";
        connect_func();

        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, quoteID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;
    }

    
    public boolean updateQuote(int quoteID, String status, String note) throws SQLException {
        String sqlSelect = "SELECT Note FROM Quote WHERE QuoteID = ?";
        String sqlUpdate = "UPDATE Quote SET Status=?, Note=? WHERE QuoteID = ?";

        connect_func();

        // Fetch the existing note value
        preparedStatement = connect.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, quoteID);
        ResultSet resultSet = preparedStatement.executeQuery();

        String existingNote = "";
        if (resultSet.next()) {
            existingNote = resultSet.getString("Note");
        }

        // Concatenate the new note to the existing value
        String updatedNote = existingNote + " " + note;

        // Update the row with the new values
        preparedStatement = connect.prepareStatement(sqlUpdate);
        preparedStatement.setString(1, status);
        preparedStatement.setString(2, updatedNote);
        preparedStatement.setInt(3, quoteID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;

        // Close resources
        resultSet.close();
        preparedStatement.close();

        return rowUpdated;
    }
    

    
    public List<QuoteRequest> listAllQuoteReqs() throws SQLException {
        List<QuoteRequest> listUser = new ArrayList<QuoteRequest>();        
        String sql = "SELECT * FROM QuoteRequest";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	Integer QuoteRequestID = resultSet.getInt("QuoteRequestID");
        	Integer ClientID = resultSet.getInt("ClientID");
            String DateSubmitted = resultSet.getString("DateSubmitted"); 
            String Status = resultSet.getString("Status");
            String ClientNote = resultSet.getString("ClientNote");
            
            QuoteRequest quoteReq = null;
            
            quoteReq = new QuoteRequest(QuoteRequestID, ClientID, DateSubmitted, Status, ClientNote);
            
            String sql2 = "SELECT COUNT(*) AS TreeCount FROM Tree WHERE QuoteRequestID = ?";
            try (PreparedStatement preparedStatement = (PreparedStatement) connect.prepareStatement(sql2)) {
                preparedStatement.setInt(1, QuoteRequestID); // Set the actual QuoteRequestID
                try (ResultSet resultSet2 = preparedStatement.executeQuery()) {
                    if (resultSet2.next()) {
                        int treeCount = resultSet2.getInt("TreeCount");
                       quoteReq.setTreeCount(treeCount);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
            listUser.add(quoteReq);
        }   
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    public List<QuoteRequest> listClientQuoteReqs(Integer ClientID) throws SQLException {
    	//System.out.println("in function");
        List<QuoteRequest> listUser = new ArrayList<QuoteRequest>();        
        String sql = "SELECT * FROM QuoteRequest WHERE QuoteRequest.ClientID = ?";      
        
        connect_func();      
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, ClientID);
        
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	Integer QuoteRequestID = resultSet.getInt("QuoteRequestID");
            String DateSubmitted = resultSet.getString("DateSubmitted"); 
            String Status = resultSet.getString("Status");
            String ClientNote = resultSet.getString("ClientNote");
            
            QuoteRequest quoteReq = null;
            
            quoteReq = new QuoteRequest(QuoteRequestID, ClientID, DateSubmitted, Status, ClientNote);
            
            //System.out.println(quoteReq.toString());
            listUser.add(quoteReq);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    
    public List<Quote> listQuoteReqQuotes(Integer QuoteRequestID) throws SQLException {
    	//System.out.println("in function");
        List<Quote> listUser = new ArrayList<Quote>();        
        String sql = "SELECT * FROM Quote WHERE Quote.QuoteRequestID = ? ORDER BY DateSubmitted ASC";      
        
        connect_func();      
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, QuoteRequestID);
        
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	
        	Integer QuoteID = resultSet.getInt("QuoteID");
        	double Price = resultSet.getDouble("Price");
        	String WorkPeriodStartDate = resultSet.getString("WorkPeriodStartDate");
        	String WorkPeriodEndDate = resultSet.getString("WorkPeriodEndDate");
            String DateSubmitted = resultSet.getString("DateSubmitted"); 
            String Status = resultSet.getString("Status");
            String Note = resultSet.getString("Note");
            
            Quote quote = null;
            
            quote = new Quote(QuoteID, QuoteRequestID, Price, WorkPeriodStartDate, WorkPeriodEndDate, DateSubmitted, Status, Note);
            
            //System.out.println(quote.toString());
            listUser.add(quote);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    public OrderOfWork getOrderOfWorkDetails(int quoteRequestID) throws SQLException {
        OrderOfWork orderOfWork = null;
        String sql = "SELECT ow.*, q.WorkPeriodStartDate AS QuoteStartDate, q.WorkPeriodEndDate AS QuoteEndDate " +
                     "FROM OrderOfWork ow " +
                     "INNER JOIN Quote q ON ow.QuoteID = q.QuoteID " +
                     "WHERE q.QuoteRequestID = ? AND q.Status = 'accepted'";
        
        connect_func();
        
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setInt(1, quoteRequestID);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer orderOfWorkID = resultSet.getInt("OrderOfWorkID");
                    // Add other fields as needed
                    String startDate = resultSet.getString("QuoteStartDate");
                    String endDate = resultSet.getString("QuoteEndDate");
                    String dateCreated = resultSet.getString("DateCreated");
                    String status = resultSet.getString("Status");
                    
                    // Create the OrderOfWork object
                    orderOfWork = new OrderOfWork(orderOfWorkID, startDate, endDate, dateCreated, status);
                }
            }
        } finally {
            disconnect();
        }
        
        return orderOfWork;
    }
    
    public Bill getBill(int QuoteReqID) throws SQLException {
        Bill bill = null;

        String sql = "SELECT * FROM Bill WHERE QuoteRequestID = ?";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setInt(1, QuoteReqID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve attributes from the result set
                    int billID = resultSet.getInt("BillID");
                    double amountDue = resultSet.getDouble("AmountDue");
                    String DateIssued = resultSet.getString("DateIssued");
                    String Status  = resultSet.getString("Status");
                    String Note = resultSet.getString("Note");

                    // Create a new Bill object with retrieved attributes
                    bill = new Bill(billID, QuoteReqID, amountDue, DateIssued, Status, Note);
                }
            }
        }

        return bill;
    }

    
    public int getNextQuoteRequestId() throws SQLException {
        int nextQuoteRequestId = 0;

        String sql = "SELECT MAX(QuoteRequestID) + 1 AS NextQuoteRequestId FROM QuoteRequest";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                nextQuoteRequestId = resultSet.getInt("NextQuoteRequestId");
            }
        }

        return nextQuoteRequestId;
    }
    
    public double getPriceForOrderOfWork(int orderofworkID) throws SQLException {
        double price = 0.0;

        String sql = "SELECT q.Price " +
                     "FROM Quote q " +
                     "INNER JOIN OrderOfWork o ON q.QuoteID = o.QuoteID " +
                     "WHERE o.OrderOfWorkID = ?";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setInt(1, orderofworkID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    price = resultSet.getDouble("Price");
                }
            }
        }

        return price;
    }
    
    public String getQuoteReqStatus(int quoteReqID) throws SQLException {
        String status = null;

        String sql = "SELECT Status FROM QuoteRequest WHERE QuoteRequestID = ?";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setInt(1, quoteReqID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                	status = resultSet.getString("Status");
                }
            }
        }

        return status;
    }
    
    
    public Client getUser(String email) throws SQLException {
    	Client user = null;
        String sql = "SELECT * FROM Client WHERE Email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	Integer clientID = resultSet.getInt("ClientID");
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String password = resultSet.getString("Password");
            String creditcard = resultSet.getString("CreditCardInformation");
            String address = resultSet.getString("Address"); 
            String phone = resultSet.getString("PhoneNumber");
            user = new Client(clientID, email, firstName, lastName, password, creditcard, address,phone);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public List<Tree> listTrees(Integer QuoteRequestID) throws SQLException {
    	//System.out.println("in function");
        List<Tree> listTree = new ArrayList<Tree>();        
        String sql = "SELECT * FROM Tree WHERE Tree.QuoteRequestID = ?";      
        
        connect_func();      
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, QuoteRequestID);
        
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	
        	int treeID = resultSet.getInt("TreeID");
            int quoteRequestID = resultSet.getInt("QuoteRequestID");
            String size = resultSet.getString("Size");
            String height = resultSet.getString("Height");
            String location = resultSet.getString("Location");
            String distanceToHouse = resultSet.getString("DistanceToHouse");
            byte[] picture1 = resultSet.getBytes("Picture1");
            byte[] picture2 = resultSet.getBytes("Picture2");
            byte[] picture3 = resultSet.getBytes("Picture3");

            Tree tree = new Tree(treeID, quoteRequestID, size, height, location, distanceToHouse, picture1, picture2, picture3);
            listTree.add(tree);
        }        
        resultSet.close();
        disconnect();        
        return listTree;
    }
    
    public List<BillDispute> listAllDisputes(int billID) throws SQLException {
        List<BillDispute> billDisputes = new ArrayList<>();

        String sql = "SELECT * FROM BillDispute WHERE BillID = ?";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setInt(1, billID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int disputeID = resultSet.getInt("DisputeID");
                    String user = resultSet.getString("User");
                    String timeAndDate = resultSet.getString("TimeAndDate");
                    String changelog = resultSet.getString("Changelog");

                    BillDispute billDispute = new BillDispute(disputeID, billID, user, timeAndDate, changelog);
                    billDisputes.add(billDispute);
                }
            }
        }

        return billDisputes;
    }
    
    //all backend queries for roots
    
    public List<String> getTopClients() throws SQLException {
        List<String> topClients = new ArrayList<>();
        String sql = "SELECT C.ClientID, C.FirstName, C.LastName, COUNT(T.TreeID) AS NumberOfTrees, GROUP_CONCAT(QR.QuoteRequestID) AS PaidQuoteRequests " +
                "FROM Client C " +
                "JOIN QuoteRequest QR ON C.ClientID = QR.ClientID " +
                "JOIN Tree T ON QR.QuoteRequestID = T.QuoteRequestID " +
                "WHERE QR.Status = 'paid' " +
                "GROUP BY C.ClientID, C.FirstName, C.LastName " +
                "HAVING NumberOfTrees = (SELECT MAX(TreeCount) FROM (SELECT COUNT(T2.TreeID) AS TreeCount " +
                "FROM Client C2 " +
                "JOIN QuoteRequest QR2 ON C2.ClientID = QR2.ClientID " +
                "JOIN Tree T2 ON QR2.QuoteRequestID = T2.QuoteRequestID " +
                "WHERE QR2.Status = 'paid' " +
                "GROUP BY C2.ClientID) AS TreeCounts)";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int clientID = resultSet.getInt("ClientID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    int numberOfTrees = resultSet.getInt("NumberOfTrees");
                    String client = "ID: " + clientID + ", Name: " + firstName + " " + lastName + ", No of trees: " + numberOfTrees;
                    topClients.add(client);
                }
            }
        }
        return topClients;
    }

    
    /*public List<String> getTopClients() throws SQLException {
        List<String> topClients = new ArrayList<>();
        String sql = "SELECT C.ClientID, C.FirstName, C.LastName, COUNT(T.TreeID) AS NumberOfTrees, GROUP_CONCAT(QR.QuoteRequestID) AS PaidQuoteRequests " +
                "FROM Client C " +
                "JOIN QuoteRequest QR ON C.ClientID = QR.ClientID " +
                "JOIN Tree T ON QR.QuoteRequestID = T.QuoteRequestID " +
                "JOIN Quote Q ON QR.QuoteRequestID = Q.QuoteRequestID " +
                "WHERE Q.Status = 'accepted' AND QR.Status = 'paid' " +
                "GROUP BY C.ClientID, C.FirstName, C.LastName " +
                "ORDER BY NumberOfTrees DESC";
        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                	int clientID = resultSet.getInt("ClientID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    int numberOfTrees = resultSet.getInt("NumberOfTrees");
                    String client = "ID: "+ clientID + ", Name: " + firstName +" "+lastName+", No of trees: "+numberOfTrees;
                    topClients.add(client);
                }
            }
        }
        return topClients;
    }*/

    public List<String> getSingleQuoteClients() throws SQLException {
        List<String> clientsWithSingleAcceptedQuote = new ArrayList<>();

        String sql = "SELECT C.ClientID, C.FirstName, C.LastName " +
                "FROM Client C " +
                "JOIN QuoteRequest QR ON C.ClientID = QR.ClientID " +
                "JOIN Quote Q ON QR.QuoteRequestID = Q.QuoteRequestID " +
                "WHERE QR.Status IN ('accepted', 'completed', 'billed', 'paid')  " +
                "GROUP BY C.ClientID, C.FirstName, C.LastName, QR.QuoteRequestID " +
                "HAVING COUNT(Q.QuoteRequestID) = 1";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int clientID = resultSet.getInt("ClientID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String clientInfo = "ID: " + clientID + ", Name: " + firstName + " " + lastName;
                    clientsWithSingleAcceptedQuote.add(clientInfo);
                }
            }
        }

        return clientsWithSingleAcceptedQuote;
    }
    
    public List<Integer> getSingleTreeQuoteRequests() throws SQLException {
        List<Integer> quoteRequestIDs = new ArrayList<>();

        String sql = "SELECT QR.QuoteRequestID " +
                "FROM QuoteRequest QR " +
                "JOIN Tree T ON QR.QuoteRequestID = T.QuoteRequestID " +
                "WHERE QR.Status IN ('accepted', 'completed', 'billed', 'paid') " +
                "GROUP BY QR.QuoteRequestID " +
                "HAVING COUNT(T.TreeID) = 1";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int quoteRequestID = resultSet.getInt("QuoteRequestID");
                    quoteRequestIDs.add(quoteRequestID);
                }
            }
        }

        return quoteRequestIDs;
    }

    public List<String> getClientsWithoutOrdersOfWork() throws SQLException {
        List<String> clientsWithoutOrdersOfWork = new ArrayList<>();

        String sql = "SELECT C.ClientID, C.FirstName, C.LastName " +
                "FROM Client C " +
                "JOIN QuoteRequest QR ON C.ClientID = QR.ClientID " +
                "LEFT JOIN Quote Q ON QR.QuoteRequestID = Q.QuoteRequestID " +
                "LEFT JOIN OrderOfWork OOW ON Q.QuoteID = OOW.QuoteID " +
                "WHERE QR.Status IN ('pending', 'cancelled', 'quoted', 'rejected') AND OOW.OrderOfWorkID IS NULL " +
                "GROUP BY C.ClientID, C.FirstName, C.LastName";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int clientID = resultSet.getInt("ClientID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String clientInfo = "ID: " + clientID + ", Name: " + firstName + " " + lastName;
                    clientsWithoutOrdersOfWork.add(clientInfo);
                }
            }
        }

        return clientsWithoutOrdersOfWork;
    }

    public List<String> getHighestTrees() throws SQLException {
        List<String> highestTrees = new ArrayList<>();

        String sql = "SELECT T.TreeID, T.Height " +
                "FROM Tree T " +
                "JOIN QuoteRequest QR ON QR.QuoteRequestID = T.QuoteRequestID " +
                "WHERE QR.Status IN ('completed', 'billed', 'paid') " +
                "AND T.Height = (SELECT MAX(T2.Height) FROM Tree T2 " +
                "               JOIN QuoteRequest QR2 ON QR2.QuoteRequestID = T2.QuoteRequestID " +
                "               WHERE QR2.Status IN ('completed', 'billed', 'paid'))";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int treeID = resultSet.getInt("TreeID");
                    double treeHeight = resultSet.getDouble("Height");
                    String treeInfo = "TreeID: " + treeID + ", Height: " + treeHeight;
                    highestTrees.add(treeInfo);
                }
            }
        }

        return highestTrees;
    }


    public List<String> getUnpaidBillsAfterOneWeek() throws SQLException {
        List<String> unpaidBills = new ArrayList<>();

        String sql = "SELECT B.BillID, B.AmountDue, B.DateIssued " +
                "FROM Bill B " +
                "WHERE B.Status IN ('pending', 'disputed') " +
                "AND DATEDIFF(NOW(), B.DateIssued) > 7";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int billID = resultSet.getInt("BillID");
                    double amountDue = resultSet.getDouble("AmountDue");
                    String dateIssued = resultSet.getString("DateIssued");
                    String billInfo = "BillID: " + billID + ", AmountDue: " + amountDue + ", DateIssued: " + dateIssued;
                    unpaidBills.add(billInfo);
                }
            }
        }

        return unpaidBills;
    }

    public List<String> getClientsWithUnpaidBills() throws SQLException {
        List<String> clientsWithUnpaidBills = new ArrayList<>();

        String sql = "SELECT DISTINCT C.ClientID, C.FirstName, C.LastName " +
                "FROM Client C " +
                "JOIN QuoteRequest QR ON C.ClientID = QR.ClientID " +
                "LEFT JOIN Bill B ON QR.QuoteRequestID = B.QuoteRequestID " +
                "WHERE B.Status IN ('pending', 'disputed') AND DATEDIFF(NOW(), B.DateIssued) > 7 " +
                "AND C.ClientID NOT IN (SELECT DISTINCT C.ClientID " +
                                       "FROM Client C " +
                                       "JOIN QuoteRequest QR ON C.ClientID = QR.ClientID " +
                                       "JOIN Bill B ON QR.QuoteRequestID = B.QuoteRequestID " +
                                       "WHERE B.Status = 'paid')";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int clientID = resultSet.getInt("ClientID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String clientInfo = "ID: " + clientID + ", Name: " + firstName + " " + lastName;
                    clientsWithUnpaidBills.add(clientInfo);
                }
            }
        }

        return clientsWithUnpaidBills;
    }
    
    public List<String> getClientsPaidWithin24Hours() throws SQLException {
        List<String> clientsPaidWithin24Hours = new ArrayList<>();

        String sql = "SELECT DISTINCT C.ClientID, C.FirstName, C.LastName " +
                "FROM Client C " +
                "JOIN QuoteRequest QR ON C.ClientID = QR.ClientID " +
                "JOIN Bill B ON QR.QuoteRequestID = B.QuoteRequestID " +
                "WHERE B.Status = 'paid' AND TIMESTAMPDIFF(HOUR, B.DateIssued, " +
                "(SELECT MAX(BD.TimeAndDate) FROM BillDispute BD WHERE BD.BillID = B.BillID AND BD.Changelog LIKE '%Paid%')) <= 24 " +
                "AND C.ClientID NOT IN (SELECT DISTINCT C.ClientID " +
                                       "FROM Client C " +
                                       "JOIN QuoteRequest QR ON C.ClientID = QR.ClientID " +
                                       "JOIN Bill B ON QR.QuoteRequestID = B.QuoteRequestID " +
                                       "JOIN BillDispute BD ON B.BillID = BD.BillID " +
                                       "WHERE (B.Status = 'pending' AND TIMESTAMPDIFF(HOUR, B.DateIssued, NOW()) > 24) " +
                                       "OR (B.Status = 'paid' AND TIMESTAMPDIFF(HOUR, B.DateIssued, " +
                                       "(SELECT MAX(BD.TimeAndDate) FROM BillDispute BD WHERE BD.BillID = B.BillID AND BD.Changelog LIKE '%Paid%')) > 24))";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int clientID = resultSet.getInt("ClientID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String clientInfo = "ID: " + clientID + ", Name: " + firstName + " " + lastName;
                    clientsPaidWithin24Hours.add(clientInfo);
                }
            }
        }

        return clientsPaidWithin24Hours;
    }


    public List<String> getClientSummary() throws SQLException {
        List<String> clientSummaries = new ArrayList<>();

        String sql = "SELECT " +
                "C.ClientID, " +
                "C.FirstName, " +
                "C.LastName, " +
                "SUM(TotalTrees) AS TotalTrees, " +
                "IFNULL(SUM(CASE WHEN B.Status = 'paid' THEN B.AmountDue ELSE 0 END), 0) AS TotalPaidAmount, " +
                "IFNULL(SUM(CASE WHEN B.Status IN ('pending', 'disputed') THEN B.AmountDue ELSE 0 END), 0) AS TotalDueAmount " +
                "FROM " +
                "Client C " +
                "LEFT JOIN (" +
                "    SELECT QR.ClientID, QR.QuoteRequestID, COUNT(T.TreeID) AS TotalTrees " +
                "    FROM QuoteRequest QR " +
                "    LEFT JOIN Tree T ON QR.QuoteRequestID = T.QuoteRequestID " +
                "    WHERE QR.Status IN ('completed', 'paid', 'billed') " +
                "    GROUP BY QR.ClientID, QR.QuoteRequestID" +
                ") AS TreeCounts ON C.ClientID = TreeCounts.ClientID " +
                "LEFT JOIN Bill B ON TreeCounts.QuoteRequestID = B.QuoteRequestID " +
                "GROUP BY C.ClientID, C.FirstName, C.LastName";

        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int clientID = resultSet.getInt("ClientID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    int totalTrees = resultSet.getInt("TotalTrees");
                    double totalPaidAmount = resultSet.getDouble("TotalPaidAmount");
                    double totalDueAmount = resultSet.getDouble("TotalDueAmount");

                    String clientSummary = "ID: " + clientID +
                            ", Name: " + firstName + " " + lastName +
                            ", Total Trees: " + totalTrees +
                            ", Total Paid Amount: " + totalPaidAmount +
                            ", Total Due Amount: " + totalDueAmount;

                    clientSummaries.add(clientSummary);
                }
            }
        }

        return clientSummaries;
    }







    
    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM Client WHERE Email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    
    public boolean checkAddress(String address) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM Client WHERE Address = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, address);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM Client WHERE Password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM Client";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("Email").equals(email) && resultSet.getString("Password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists projectdb; ",
					        "create database projectdb; ",
					        "use projectdb; ",
					        "drop table if exists Client; ",
					        "drop table if exists Tree; ",
					        "drop table if exists QuoteRequest; ",
					        "drop table if exists Quote; ",
					        "drop table if exists OrderOfWork; ",
					        "drop table if exists Bill; ",
					        "drop table if exists BillHistory; ",
        					};
        
        String clientTableCreation = (
        	    "CREATE TABLE Client (" +
        	    "ClientID INT AUTO_INCREMENT PRIMARY KEY," +
        	    "FirstName VARCHAR(255)," +
        	    "LastName VARCHAR(255)," +
        	    "Address VARCHAR(255)," +
        	    "CreditCardInformation VARCHAR(255)," +
        	    "PhoneNumber VARCHAR(15)," +
        	    "Email VARCHAR(255)," +
        	    "Password VARCHAR(255));"
        	);

        
        String quoteRequestTableCreation = (
        	    "CREATE TABLE QuoteRequest (" +
        	    "QuoteRequestID INT PRIMARY KEY," +
        	    "ClientID INT," +
        	    "DateSubmitted DATE," +
        	    "Status ENUM('pending', 'accepted', 'rejected', 'quoted', 'cancelled', 'completed', 'billed', 'paid')," +
        	    "ClientNote TEXT," +
        	    "FOREIGN KEY (ClientID) REFERENCES Client(ClientID));"
        	);

        
        String treeTableCreation = (
        	    "CREATE TABLE Tree (" +
        	    "TreeID INT AUTO_INCREMENT PRIMARY KEY," +
        	    "QuoteRequestID INT," +
        	    "Size VARCHAR(255)," +
        	    "Height VARCHAR(255)," +
        	    "Location VARCHAR(255)," +
        	    "DistanceToHouse VARCHAR(255)," +
        	    "Picture1 LONGBLOB," +
        	    "Picture2 LONGBLOB," +
        	    "Picture3 LONGBLOB," +
        	    "FOREIGN KEY (QuoteRequestID) REFERENCES QuoteRequest(QuoteRequestID));"
        	);

        
        
        String quoteTableCreation = (
        	    "CREATE TABLE Quote (" +
        	    "QuoteID INT AUTO_INCREMENT PRIMARY KEY," +
        	    "QuoteRequestID INT," +
        	    "Price DECIMAL(10, 2)," +
        	    "WorkPeriodStartDate DATE," +
        	    "WorkPeriodEndDate DATE," +
        	    "DateSubmitted DATE," +
        	    "Status ENUM('pending', 'accepted', 'rejected')," +
        	    "Note TEXT," +
        	    "FOREIGN KEY (QuoteRequestID) REFERENCES QuoteRequest(QuoteRequestID));"
        	);

        
        String orderOfWorkTableCreation = (
        	    "CREATE TABLE OrderOfWork (" +
        	    "OrderOfWorkID INT AUTO_INCREMENT PRIMARY KEY," +
        	    "QuoteID INT," +
        	    "DateCreated DATE," +
        	    "Status ENUM('in progress', 'completed')," +
        	    "FOREIGN KEY (QuoteID) REFERENCES Quote(QuoteID));"
        	);

        
        
        String billTableCreation = (
        	    "CREATE TABLE Bill (" +
        	    "BillID INT AUTO_INCREMENT PRIMARY KEY," +
        	    "QuoteRequestID INT," +
        	    "AmountDue DECIMAL(10, 2)," +
        	    "DateIssued DATE," +
        	    "Status ENUM('pending', 'paid', 'disputed')," +
        	    "Note TEXT," +
        	    "FOREIGN KEY (QuoteRequestID) REFERENCES QuoteRequest(QuoteRequestID));"
        	);

        
        String billDisputeTableCreation = (
        	    "CREATE TABLE BillDispute (" +
        	    "DisputeID INT AUTO_INCREMENT PRIMARY KEY," +
        	    "BillID INT," +
        	    "User VARCHAR(255)," +
        	    "TimeAndDate DATETIME," +
        	    "Changelog TEXT," +
        	    "FOREIGN KEY (BillID) REFERENCES Bill(BillID));"
        	);

        
        
        
        String clientDataGeneration = (
        	    "INSERT INTO Client (ClientID, FirstName, LastName, Address, CreditCardInformation, PhoneNumber, Email, Password) VALUES " +
        	    "(101, 'test', 'test', '123 Main St, Cityville', '**** **** **** 1234', '+1234567890', 'test', 'pass1234'), " +
        	    "(102, 'Jane', 'Smith', '456 Oak St, Townsville', '**** **** **** 5678', '+9876543210', 'jane.smith@email.com', 'pass456'), " +
        	    "(103, 'Alice', 'Johnson', '789 Pine St, Villagetown', '**** **** **** 9876', '+1122334455', 'alice.johnson@email.com', 'secretPass'), " +
        	    "(104, 'Bob', 'Williams', '567 Elm St, Countryside', '**** **** **** 3456', '+4455667788', 'bob.williams@email.com', 'securePass123'), " +
        	    "(105, 'Eva', 'Miller', '890 Maple St, Suburbia', '**** **** **** 7890', '+9988776655', 'eva.miller@email.com', 'pass1234'), " +
        	    "(106, 'David', 'Jones', '234 Birch St, Riverside', '**** **** **** 2345', '+1122334455', 'david.jones@email.com', 'davidPass'), " +
        	    "(107, 'Sara', 'Davis', '678 Cedar St, Hillside', '**** **** **** 4567', '+3344556677', 'sara.davis@email.com', 'saraPass'), " +
        	    "(108, 'Mike', 'Taylor', '901 Redwood St, Lakeside', '**** **** **** 6789', '+8899001122', 'mike.taylor@email.com', 'mike123'), " +
        	    "(109, 'Grace', 'Anderson', '345 Fir St, Mountainside', '**** **** **** 2345', '+1122334455', 'grace.anderson@email.com', 'gracePass'), " +
        	    "(110, 'Peter', 'Brown', '123 Pine St, Oceanfront', '**** **** **** 4567', '+3344556677', 'peter.brown@email.com', 'peterPass');"
        	);

        
        String quoteRequestDataGeneration = (
        	    "INSERT INTO QuoteRequest (QuoteRequestID, ClientID, DateSubmitted, Status, ClientNote) VALUES " +
        	    "(1, 101, '2023-10-01', 'paid', 'Client needs a quote for tree trimming.'), " +
        	    "(2, 102, '2023-10-02', 'paid', 'Requesting quote for tree removal.'), " +
        	    "(3, 103, '2023-10-03', 'billed', 'Client approved the initial quote.'), " +
        	    "(4, 104, '2023-10-04', 'accepted', 'Interested in tree pruning services.'), " +
        	    "(5, 105, '2023-10-05', 'pending', 'Client requires a quote for tree maintenance.'), " +
        	    "(6, 106, '2023-10-06', 'quoted', 'Client accepted the initial quote for tree removal.');"
        	);

        
        String treeDataGeneration = (
        	    "INSERT INTO Tree (TreeID, QuoteRequestID, Size, Height, Location, DistanceToHouse, Picture1, Picture2, Picture3) VALUES " +
        	    "(1, 1, 'Medium', 5.5, 'Front Yard', 10.2, 'tree1_pic1.jpg', 'tree1_pic2.jpg', 'tree1_pic3.jpg'), " +
        	    "(2, 2, 'Large', 8.0, 'Back Yard', 15.0, 'tree2_pic1.jpg', 'tree2_pic2.jpg', 'tree2_pic3.jpg'), " +
        	    "(3, 2, 'Small', 3.0, 'Side Yard', 5.5, 'tree3_pic1.jpg', 'tree3_pic2.jpg', 'tree3_pic3.jpg'), " +
        	    "(4, 3, 'Medium', 6.0, 'Garden', 8.0, 'tree4_pic1.jpg', 'tree4_pic2.jpg', 'tree4_pic3.jpg'), " +
        	    "(5, 3, 'Large', 7.5, 'Park', 20.0, 'tree5_pic1.jpg', 'tree5_pic2.jpg', 'tree5_pic3.jpg'), " +
        	    "(6, 4, 'Small', 4.0, 'Street', 3.5, 'tree6_pic1.jpg', 'tree6_pic2.jpg', 'tree6_pic3.jpg'), " +
        	    "(7, 4, 'Medium', 5.0, 'Schoolyard', 12.0, 'tree7_pic1.jpg', 'tree7_pic2.jpg', 'tree7_pic3.jpg'), " +
        	    "(8, 5, 'Large', 9.0, 'Playground', 18.0, 'tree8_pic1.jpg', 'tree8_pic2.jpg', 'tree8_pic3.jpg'), " +
        	    "(9, 6, 'Small', 3.5, 'Front Yard', 7.0, 'tree9_pic1.jpg', 'tree9_pic2.jpg', 'tree9_pic3.jpg'), " +
        	    "(10, 6, 'Medium', 5.8, 'Back Yard', 14.5, 'tree10_pic1.jpg', 'tree10_pic2.jpg', 'tree10_pic3.jpg'), " +
        	    "(11, 6, 'Large', 10.0, 'Grove', 25.0, 'tree11_pic1.jpg', 'tree11_pic2.jpg', 'tree11_pic3.jpg'), " +
        	    "(12, 6, 'Medium', 6.5, 'Riverbank', 13.0, 'tree12_pic1.jpg', 'tree12_pic2.jpg', 'tree12_pic3.jpg');"
        	);

        
        String quoteDataGeneration = (
        	    "INSERT INTO Quote (QuoteID, QuoteRequestID, Price, WorkPeriodStartDate, WorkPeriodEndDate, DateSubmitted, Status, Note) VALUES " +
        	    "(1, 1, 500.00, '2023-10-05', '2023-10-10', '2023-10-04', 'rejected', 'Initial quote for tree trimming.'), " +
        	    "(11, 1, 450.00, '2023-10-05', '2023-10-10', '2023-10-04', 'accepted', 'Updated quote for tree trimming.'), " +
        	    "(2, 2, 1200.00, '2023-10-08', '2023-10-15', '2023-10-06', 'accepted', 'Quote for tree removal approved.'), " +
        	    "(3, 3, 300.00, '2023-10-12', '2023-10-18', '2023-10-09', 'accepted', 'Finalized quote for tree pruning.'), " +
        	    "(4, 4, 150.00, '2023-10-15', '2023-10-20', '2023-10-11', 'rejected', 'Initial quote for tree pruning.'), " +
        	    "(7, 4, 400.00, '2023-10-25', '2023-10-30', '2023-10-19', 'accepted', 'Initial quote for tree trimming in schoolyard.'), " +
        	    "(6, 6, 800.00, '2023-10-20', '2023-10-28', '2023-10-16', 'pending', 'Quote for tree removal accepted.');"
        	);


        
        String orderOfWorkDataGeneration = (
        	    "INSERT INTO OrderOfWork (OrderOfWorkID, QuoteID, DateCreated, Status) VALUES " +
        	    "(1, 11, '2023-10-10', 'completed'), " +
        	    "(2, 2, '2023-10-15', 'completed'), " +
        	    "(3, 3, '2023-10-18', 'completed'), " +
        	    "(4, 7, '2023-10-20', 'completed');"
        	);


        
        String billDataGeneration = (
        	    "INSERT INTO Bill (BillID, QuoteRequestID, AmountDue, DateIssued, Status, Note) VALUES " +
        	    "(1, 1, 250.00, '2023-10-20', 'paid', 'Work done.'), " +
        	    "(2, 2, 1200.00, '2023-10-20', 'paid', 'Work done.'), " +
        	    "(3, 3, 300.00, '2023-10-25', 'disputed', 'Work done.'), " +
        	    "(4, 4, 150.00, '2023-10-28', 'pending', 'Work done.');"
        	);


        
        String billDisputeDataGeneration = (
        	    "INSERT INTO BillDispute (DisputeID, BillID, User, TimeAndDate, Changelog) VALUES " +
        	    "(1, 2, 'user', '2023-10-25 10:45:00', 'paid'), " +
        	    "(2, 2, 'user', '2023-10-20 10:45:00', 'paid'), " +
        	    "(3, 3, 'user', '2023-11-05 12:15:00', 'Client disputes the tree maintenance charge.');"
        	);





        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        
        //statement.execute(clientTableCreation);
        //statement.execute(clientDataGeneration);
        
        String[] sqlStatements = {
        		clientTableCreation,
        		quoteRequestTableCreation,
        	    treeTableCreation,
        	    quoteTableCreation,
        	    orderOfWorkTableCreation,
        	    billTableCreation,
        	    billDisputeTableCreation,
        	    clientDataGeneration,
        	    quoteRequestDataGeneration,
        	    treeDataGeneration,
        	    quoteDataGeneration,
        	    orderOfWorkDataGeneration,
        	    billDataGeneration,
        	    billDisputeDataGeneration
        	};

        	// Execute SQL statements in a loop
        for (int i = 0; i < sqlStatements.length; i++) {
        	statement.execute(sqlStatements[i]);
        }
        
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
