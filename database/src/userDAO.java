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
        	    "Status ENUM('pending', 'accepted', 'rejected', 'quoted', 'cancelled')," +
        	    "ClientNote TEXT," +
        	    "FOREIGN KEY (ClientID) REFERENCES Client(ClientID));"
        	);

        
        String treeTableCreation = (
        	    "CREATE TABLE Tree (" +
        	    "TreeID INT AUTO_INCREMENT PRIMARY KEY," +
        	    "QuoteRequestID INT," +
        	    "Size VARCHAR(255)," +
        	    "Height FLOAT," +
        	    "Location VARCHAR(255)," +
        	    "DistanceToHouse FLOAT," +
        	    "Picture1 BLOB," +
        	    "Picture2 BLOB," +
        	    "Picture3 BLOB," +
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
        	    "OrderOfWorkID INT," +
        	    "AmountDue DECIMAL(10, 2)," +
        	    "DateIssued DATE," +
        	    "Status ENUM('pending', 'paid', 'disputed')," +
        	    "Note TEXT," +
        	    "FOREIGN KEY (OrderOfWorkID) REFERENCES OrderOfWork(OrderOfWorkID));"
        	);

        
        String billDisputeTableCreation = (
        	    "CREATE TABLE BillDispute (" +
        	    "DisputeID INT AUTO_INCREMENT PRIMARY KEY," +
        	    "BillID INT," +
        	    "ClientID INT," +
        	    "TimeAndDate DATETIME," +
        	    "Changelog TEXT," +
        	    "FOREIGN KEY (BillID) REFERENCES Bill(BillID)," +
        	    "FOREIGN KEY (ClientID) REFERENCES Client(ClientID));"
        	);

        
        
        
        String clientDataGeneration = (
        	    "INSERT INTO Client (ClientID, FirstName, LastName, Address, CreditCardInformation, PhoneNumber, Email, Password) VALUES " +
        	    "(101, 'test', 'test', '123 Main St, Cityville', '**** **** **** 1234', '+1234567890', 'test@email.com', 'pass1234'), " +
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
        	    "(1, 101, '2023-10-01', 'pending', 'Client needs a quote for tree trimming.'), " +
        	    "(2, 102, '2023-10-02', 'pending', 'Requesting quote for tree removal.'), " +
        	    "(3, 103, '2023-10-03', 'accepted', 'Client approved the initial quote.'), " +
        	    "(4, 104, '2023-10-04', 'pending', 'Interested in tree pruning services.'), " +
        	    "(5, 105, '2023-10-05', 'pending', 'Client requires a quote for tree maintenance.'), " +
        	    "(6, 106, '2023-10-06', 'accepted', 'Client accepted the initial quote for tree removal.'), " +
        	    "(7, 107, '2023-10-07', 'pending', 'Requesting quote for tree trimming in schoolyard.'), " +
        	    "(8, 108, '2023-10-08', 'pending', 'Client needs a quote for tree pruning in the playground.'), " +
        	    "(9, 109, '2023-10-09', 'accepted', 'Client approved the quote for tree maintenance.'), " +
        	    "(10, 110, '2023-10-10', 'pending', 'Interested in tree trimming services for the backyard.');"
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
        	    "(12, 6, 'Medium', 6.5, 'Riverbank', 13.0, 'tree12_pic1.jpg', 'tree12_pic2.jpg', 'tree12_pic3.jpg'), " +
        	    "(13, 7, 'Small', 4.2, 'Mountain', 6.0, 'tree13_pic1.jpg', 'tree13_pic2.jpg', 'tree13_pic3.jpg'), " +
        	    "(14, 7, 'Large', 8.5, 'Oceanfront', 22.5, 'tree14_pic1.jpg', 'tree14_pic2.jpg', 'tree14_pic3.jpg'), " +
        	    "(15, 8, 'Medium', 5.0, 'Hillside', 11.0, 'tree15_pic1.jpg', 'tree15_pic2.jpg', 'tree15_pic3.jpg'), " +
        	    "(16, 8, 'Large', 8.0, 'Back Yard', 15.0, 'tree16_pic1.jpg', 'tree16_pic2.jpg', 'tree16_pic3.jpg'), " +
        	    "(17, 8, 'Small', 3.0, 'Side Yard', 5.5, 'tree17_pic1.jpg', 'tree17_pic2.jpg', 'tree17_pic3.jpg'), " +
        	    "(18, 9, 'Medium', 6.0, 'Garden', 8.0, 'tree18_pic1.jpg', 'tree18_pic2.jpg', 'tree18_pic3.jpg'), " +
        	    "(19, 10, 'Large', 7.5, 'Park', 20.0, 'tree19_pic1.jpg', 'tree19_pic2.jpg', 'tree19_pic3.jpg'), " +
        	    "(20, 10, 'Small', 4.0, 'Street', 3.5, 'tree20_pic1.jpg', 'tree20_pic2.jpg', 'tree20_pic3.jpg');"
        	);






        
        String quoteDataGeneration = (
        	    "INSERT INTO Quote (QuoteID, QuoteRequestID, Price, WorkPeriodStartDate, WorkPeriodEndDate, DateSubmitted, Status, Note) VALUES " +
        	    "(1, 1, 500.00, '2023-10-05', '2023-10-10', '2023-10-04', 'pending', 'Initial quote for tree trimming.'), " +
        	    "(11, 1, 500.00, '2023-10-05', '2023-10-10', '2023-10-04', 'pending', 'Initial quote for tree trimming.'), " +
        	    "(2, 2, 1200.00, '2023-10-08', '2023-10-15', '2023-10-06', 'accepted', 'Quote for tree removal approved.'), " +
        	    "(3, 3, 300.00, '2023-10-12', '2023-10-18', '2023-10-09', 'accepted', 'Finalized quote for tree pruning.'), " +
        	    "(4, 4, 150.00, '2023-10-15', '2023-10-20', '2023-10-11', 'pending', 'Initial quote for tree pruning.'), " +
        	    "(5, 5, 250.00, '2023-10-18', '2023-10-25', '2023-10-14', 'pending', 'Quote for tree maintenance services.'), " +
        	    "(6, 6, 800.00, '2023-10-20', '2023-10-28', '2023-10-16', 'accepted', 'Quote for tree removal accepted.'), " +
        	    "(7, 7, 400.00, '2023-10-25', '2023-10-30', '2023-10-19', 'pending', 'Initial quote for tree trimming in schoolyard.'), " +
        	    "(8, 8, 600.00, '2023-10-28', '2023-11-05', '2023-10-22', 'pending', 'Quote for tree pruning in the playground.'), " +
        	    "(9, 9, 200.00, '2023-10-30', '2023-11-08', '2023-10-25', 'accepted', 'Finalized quote for tree maintenance.'), " +
        	    "(10, 10, 350.00, '2023-11-02', '2023-11-10', '2023-10-28', 'pending', 'Quote for tree trimming in the backyard.');"
        	);


        
        String orderOfWorkDataGeneration = (
        	    "INSERT INTO OrderOfWork (OrderOfWorkID, QuoteID, DateCreated, Status) VALUES " +
        	    "(1, 1, '2023-10-10', 'in progress'), " +
        	    "(2, 2, '2023-10-15', 'completed'), " +
        	    "(3, 3, '2023-10-18', 'in progress'), " +
        	    "(4, 4, '2023-10-20', 'in progress'), " +
        	    "(5, 5, '2023-10-25', 'in progress'), " +
        	    "(6, 6, '2023-10-28', 'completed'), " +
        	    "(7, 7, '2023-10-30', 'in progress'), " +
        	    "(8, 8, '2023-11-02', 'in progress'), " +
        	    "(9, 9, '2023-11-05', 'completed'), " +
        	    "(10, 10, '2023-11-08', 'in progress');"
        	);


        
        String billDataGeneration = (
        	    "INSERT INTO Bill (BillID, OrderOfWorkID, AmountDue, DateIssued, Status, Note) VALUES " +
        	    "(1, 1, 500.00, '2023-10-12', 'pending', 'Amount due for tree trimming work.'), " +
        	    "(2, 2, 1200.00, '2023-10-20', 'paid', 'Payment received for tree removal.'), " +
        	    "(3, 3, 300.00, '2023-10-25', 'pending', 'Amount due for tree pruning work.'), " +
        	    "(4, 4, 150.00, '2023-10-28', 'pending', 'Amount due for tree pruning work.'), " +
        	    "(5, 5, 250.00, '2023-11-02', 'pending', 'Amount due for tree maintenance work.'), " +
        	    "(6, 6, 800.00, '2023-11-08', 'paid', 'Payment received for tree removal.'), " +
        	    "(7, 7, 400.00, '2023-11-12', 'pending', 'Amount due for tree trimming work in schoolyard.'), " +
        	    "(8, 8, 600.00, '2023-11-18', 'pending', 'Amount due for tree pruning work in the playground.'), " +
        	    "(9, 9, 200.00, '2023-11-22', 'paid', 'Payment received for tree maintenance work.'), " +
        	    "(10, 10, 350.00, '2023-11-28', 'pending', 'Amount due for tree trimming work in the backyard.');"
        	);


        
        String billDisputeDataGeneration = (
        	    "INSERT INTO BillDispute (DisputeID, BillID, ClientID, TimeAndDate, Changelog) VALUES " +
        	    "(1, 1, 101, '2023-10-14 15:30:00', 'Client disputes the tree trimming charge.'), " +
        	    "(2, 2, 102, '2023-10-28 10:45:00', 'Client queries the additional charge for tree pruning.'), " +
        	    "(3, 3, 103, '2023-11-05 12:15:00', 'Client disputes the tree maintenance charge.'), " +
        	    "(4, 4, 104, '2023-11-12 14:00:00', 'Client disputes the schoolyard tree trimming charge.'), " +
        	    "(5, 5, 105, '2023-11-30 09:30:00', 'Client queries the tree trimming charge for the backyard.'), " +
        	    "(6, 6, 106, '2023-10-22 11:00:00', 'Client disputes the tree removal charge.'), " +
        	    "(7, 7, 107, '2023-10-30 13:45:00', 'Client queries the additional charge for tree pruning.'), " +
        	    "(8, 8, 108, '2023-11-10 16:20:00', 'Client disputes the tree removal charge.'), " +
        	    "(9, 9, 109, '2023-11-18 09:00:00', 'Client queries the additional charge for tree pruning.'), " +
        	    "(10, 10, 110, '2023-11-25 14:30:00', 'Client disputes the tree maintenance charge.');"
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
