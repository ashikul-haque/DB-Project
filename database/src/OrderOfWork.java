public class OrderOfWork {
    private int orderOfWorkID;
    private int quoteID;
    private String dateCreated;
    private String status;

    // Default constructor
    public OrderOfWork() {
    }

    // Constructor with parameters
    public OrderOfWork(int orderOfWorkID, int quoteID, String dateCreated, String status) {
        this.orderOfWorkID = orderOfWorkID;
        this.quoteID = quoteID;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    // Getters and Setters

    public int getOrderOfWorkID() {
        return orderOfWorkID;
    }

    public void setOrderOfWorkID(int orderOfWorkID) {
        this.orderOfWorkID = orderOfWorkID;
    }

    public int getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(int quoteID) {
        this.quoteID = quoteID;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Override toString() for a string representation of the object

    @Override
    public String toString() {
        return "OrderOfWork{" +
                "orderOfWorkID=" + orderOfWorkID +
                ", quoteID=" + quoteID +
                ", dateCreated='" + dateCreated + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
