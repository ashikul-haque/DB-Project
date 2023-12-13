public class Bill {
    private int billID;
    private int quoteReqID;
    private double amountDue;
    private String dateIssued;
    private String status;
    private String note;

    // Default constructor
    public Bill() {
    }

    // Constructor with parameters
    public Bill(int billID, int quoteReqID, double amountDue, String dateIssued, String status, String note) {
        this.billID = billID;
        this.quoteReqID = quoteReqID;
        this.amountDue = amountDue;
        this.dateIssued = dateIssued;
        this.status = status;
        this.note = note;
    }
    
    public Bill(int quoteReqID, double amountDue, String dateIssued, String status, String note) {
        this.quoteReqID = quoteReqID;
        this.amountDue = amountDue;
        this.dateIssued = dateIssued;
        this.status = status;
        this.note = note;
    }
    
    public Bill(int billID, int quoteReqID, double amountDue, String dateIssued, String status) {
        this.billID = billID;
        this.quoteReqID = quoteReqID;
        this.amountDue = amountDue;
        this.dateIssued = dateIssued;
        this.status = status;
    }
    
    public Bill(int quoteReqID, double amountDue, String dateIssued, String status) {
        this.quoteReqID = quoteReqID;
        this.amountDue = amountDue;
        this.dateIssued = dateIssued;
        this.status = status;
    }
    
    

    // Getters and Setters

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getquoteReqID() {
        return quoteReqID;
    }

    public void setquoteReqID(int quoteReqID) {
        this.quoteReqID = quoteReqID;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // Override toString() for a string representation of the object

    @Override
    public String toString() {
        return "Bill{" +
                "billID=" + billID +
                ", quoteReqID=" + quoteReqID +
                ", amountDue=" + amountDue +
                ", dateIssued='" + dateIssued + '\'' +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
