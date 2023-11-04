import java.util.ArrayList;
import java.util.List;

public class QuoteRequest {
    private Integer quoteRequestID;
    private Integer treeID1;
    private Integer treeID2;
    private Integer treeID3;
    private Integer clientID;
    private String dateSubmitted;
    private String status;
    private String clientNote;

    // Default constructor
    public QuoteRequest() {
    }

    // Constructor with parameters
    public QuoteRequest(Integer quoteRequestID, Integer treeID1, Integer treeID2, Integer treeID3, Integer clientID, String dateSubmitted, String status, String clientNote) {
        this.quoteRequestID = quoteRequestID;
        this.treeID1 = treeID1;
        this.treeID2 = treeID2;
        this.treeID3 = treeID3;
        this.clientID = clientID;
        this.dateSubmitted = dateSubmitted;
        this.status = status;
        this.clientNote = clientNote;
    }
    
    public QuoteRequest(Integer quoteRequestID, Integer treeID1, Integer treeID2, Integer treeID3, Integer clientID, String dateSubmitted, String status) {
        this.quoteRequestID = quoteRequestID;
        this.treeID1 = treeID1;
        this.treeID2 = treeID2;
        this.treeID3 = treeID3;
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

    public List<Integer> getTreeIDs() {
    	List<Integer> treeList = new ArrayList<>();

        // Add elements to the list
    	treeList.add(treeID1);
    	treeList.add(treeID2);
    	treeList.add(treeID3);
        return treeList;
    }

    public void setTreeIDs(List<Integer> treeList) {
        this.treeID1 = treeList.get(0);
        this.treeID2 = treeList.get(1);
        this.treeID3 = treeList.get(2);
    }
    
    public Integer gettreeID1() {
        return treeID1;
    }

    public void settreeID1(Integer treeID1) {
        this.treeID1 = treeID1;
    }
    
    public Integer gettreeID2() {
        return treeID2;
    }

    public void settreeID2(Integer treeID2) {
        this.treeID2 = treeID2;
    }
    
    public Integer gettreeID3() {
        return treeID3;
    }

    public void settreeID3(Integer treeID3) {
        this.treeID3 = treeID3;
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

    // You can also override toString() if you want a string representation of the object

    @Override
    public String toString() {
        return "QuoteRequest{" +
                "quoteRequestID=" + quoteRequestID +
                ", treeID1=" + treeID1 +
                ", treeID2=" + treeID2 +
                ", treeID1=" + treeID3 +
                ", clientID=" + clientID +
                ", dateSubmitted='" + dateSubmitted + '\'' +
                ", status='" + status + '\'' +
                ", clientNote='" + clientNote + '\'' +
                '}';
    }
}
