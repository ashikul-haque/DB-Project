import java.util.ArrayList;
import java.util.List;

public class QuoteRequest {
    private Integer quoteRequestID;
    private Integer clientID;
    private String dateSubmitted;
    private String status;
    private String clientNote;
    private List <Tree> trees;
    private int treeCount;

    // Default constructor
    public QuoteRequest() {
    }

    // Constructor with parameters
    public QuoteRequest(Integer quoteRequestID, Integer clientID, String dateSubmitted, String status, String clientNote) {
        this.quoteRequestID = quoteRequestID;
        this.clientID = clientID;
        this.dateSubmitted = dateSubmitted;
        this.status = status;
        this.clientNote = clientNote;
    }
    
    public QuoteRequest(Integer quoteRequestID, Integer clientID, String dateSubmitted, String status) {
        this.quoteRequestID = quoteRequestID;
        this.clientID = clientID;
        this.dateSubmitted = dateSubmitted;
        this.status = status;
    }

    // Getters and Setters

    public Integer getQuoteRequestID() {
        return quoteRequestID;
    }

    public void setQuoteRequestID(Integer quoteRequestID) {
        this.quoteRequestID = quoteRequestID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
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
 
    public List<Tree> getTrees() {
        return trees;
    }
    
    public void setTrees(List<Tree> trees) {
        this.trees = trees;
    }
    
    public int getTreeCount() {
        return treeCount;
    }
    
    public void setTreeCount(int treeCount) {
        this.treeCount = treeCount;
    }

    // You can also override toString() if you want a string representation of the object

    @Override
    public String toString() {
        return "QuoteRequest{" +
                "quoteRequestID=" + quoteRequestID +
                ", clientID=" + clientID +
                ", dateSubmitted='" + dateSubmitted + '\'' +
                ", status='" + status + '\'' +
                ", clientNote='" + clientNote + '\'' +
                '}';
    }
}
