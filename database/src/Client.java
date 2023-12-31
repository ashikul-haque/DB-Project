public class Client 
{
		
		protected String password;
	 	protected String email;
	    protected String firstName;
	    protected String lastName;
	    protected String address;
	    protected String creditcard;
	    protected String phone;
	    private Integer ClientID;
	 
	    //constructors
	    public Client() {
	    }
	 
	    public Client(String email) 
	    {
	        this.email = email;
	    }
	    
	    public Client(Integer ClientID, String email,String firstName, String lastName, String password,String creditcard, String address, String phone) 
	    {
	    	this(ClientID, firstName,lastName,password,creditcard, address,phone);
	    	this.email = email;
	    }
	 
	
	    public Client(Integer ClientID, String firstName, String lastName, String password,String creditcard, String address, String phone) 
	    {
	    	this.ClientID = ClientID;
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    	this.password = password;
	        this.creditcard = creditcard;
	        this.address = address;
	        this.phone = phone;
	    }
	    
	    public Client(String email, String firstName, String lastName, String password,String creditcard, String address, String phone) 
	    {
	    	this.email = email;
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    	this.password = password;
	        this.creditcard = creditcard;
	        this.address = address;
	        this.phone = phone;
	    }
	    
	   //getter and setter methods
	    public Integer getClientID() {
	        return ClientID;
	    }
	    public void setClientID(Integer ClientID) {
	        this.ClientID = ClientID;
	    }
	    
	    public String getEmail() {
	        return email;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    
	    public String getFirstName() {
	        return firstName;
	    }
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	    
	    public String getLastName() {
	        return lastName;
	    }
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	    
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	  
	    public String getCreditcard() {
	    	return creditcard;
	    }
	    public void setCreditcard(String creditcard) {
	    	this.creditcard = creditcard;
	    }
	    
	    public String getAddress() {
	        return address;
	    }
	    public void setAdress(String address) {
	        this.address = address;
	    }
	    
	    public String getPhone() {
	    	return phone;
	    }
	    public void setPhone(String phone) {
	    	this.phone = phone;
	    }
	}