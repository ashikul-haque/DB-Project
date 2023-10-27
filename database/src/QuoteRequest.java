public class QuoteRequest {
    private int quoteRequestID;
    private int treeID;
    private int clientID;
    private String dateSubmitted;
    private String status;
    private String clientNote;

    // Default constructor
    public QuoteRequest() {
    }

    // Constructor with parameters
    public QuoteRequest(int quoteRequestID, int treeID, int clientID, String dateSubmitted, String status, String clientNote) {
        this.quoteRequestID = quoteRequestID;
        this.treeID = treeID;
        this.clientID = clientID;
        this.dateSubmitted = dateSubmitted;
        this.status = status;
        this.clientNote = clientNote;
    }
    
    public QuoteRequest(int quoteRequestID, int treeID, int clientID, String dateSubmitted, String status) {
        this.quoteRequestID = quoteRequestID;
        this.treeID = treeID;
        this.clientID = clientID;
        this.dateSubmitted = dateSubmitted;
        this.status = status;
    }

    // Getters and Setters

    public int getQuoteRequestID() {
        return quoteRequestID;
    }

    public void setQuoteRequestID(int quoteRequestID) {
        this.quoteRequestID = quoteRequestID;
    }

    public int getTreeID() {
        return treeID;
    }

    public void setTreeID(int treeID) {
        this.treeID = treeID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientNote() {
        return clientNote;
    }

    public void setClientNote(String clientNote) {
        this.clientNote = clientNote;
    }

    // You can also override toString() if you want a string representation of the object

    @Override
    public String toString() {
        return "QuoteRequest{" +
                "quoteRequestID=" + quoteRequestID +
                ", treeID=" + treeID +
                ", clientID=" + clientID +
                ", dateSubmitted='" + dateSubmitted + '\'' +
                ", status='" + status + '\'' +
                ", clientNote='" + clientNote + '\'' +
                '}';
    }
}
