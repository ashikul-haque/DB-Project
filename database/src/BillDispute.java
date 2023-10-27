public class BillDispute {
    private int disputeID;
    private int billID;
    private int clientID;
    private String timeAndDate;
    private String changelog;

    // Default constructor
    public BillDispute() {
    }

    // Constructor with parameters
    public BillDispute(int disputeID, int billID, int clientID, String timeAndDate, String changelog) {
        this.disputeID = disputeID;
        this.billID = billID;
        this.clientID = clientID;
        this.timeAndDate = timeAndDate;
        this.changelog = changelog;
    }

    // Getters and Setters

    public int getDisputeID() {
        return disputeID;
    }

    public void setDisputeID(int disputeID) {
        this.disputeID = disputeID;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(String timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    // Override toString() for a string representation of the object

    @Override
    public String toString() {
        return "BillDispute{" +
                "disputeID=" + disputeID +
                ", billID=" + billID +
                ", clientID=" + clientID +
                ", timeAndDate='" + timeAndDate + '\'' +
                ", changelog='" + changelog + '\'' +
                '}';
    }
}
