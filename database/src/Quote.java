public class Quote {
    private Integer quoteID;
    private Integer quoteRequestID;
    private double price;
    private String workPeriodStartDate;
    private String workPeriodEndDate;
    private String dateSubmitted;
    private String status;
    private String note;

    // Default constructor
    public Quote() {
    }

    // Constructor with parameters
    public Quote(Integer quoteID, Integer quoteRequestID, double price, String workPeriodStartDate,
                 String workPeriodEndDate, String dateSubmitted, String status, String note) {
        this.quoteID = quoteID;
        this.quoteRequestID = quoteRequestID;
        this.price = price;
        this.workPeriodStartDate = workPeriodStartDate;
        this.workPeriodEndDate = workPeriodEndDate;
        this.dateSubmitted = dateSubmitted;
        this.status = status;
        this.note = note;
    }
    
	public Quote(Integer quoteID, Integer quoteRequestID, double price, String workPeriodStartDate, String workPeriodEndDate,
			String dateSubmitted, String status) {
		this.quoteID = quoteID;
		this.quoteRequestID = quoteRequestID;
		this.price = price;
		this.workPeriodStartDate = workPeriodStartDate;
		this.workPeriodEndDate = workPeriodEndDate;
		this.dateSubmitted = dateSubmitted;
		this.status = status;
	}

    // Getters and Setters

    public Integer getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(Integer quoteID) {
        this.quoteID = quoteID;
    }

    public Integer getQuoteRequestID() {
        return quoteRequestID;
    }

    public void setQuoteRequestID(Integer quoteRequestID) {
        this.quoteRequestID = quoteRequestID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWorkPeriodStartDate() {
        return workPeriodStartDate;
    }

    public void setWorkPeriodStartDate(String workPeriodStartDate) {
        this.workPeriodStartDate = workPeriodStartDate;
    }

    public String getWorkPeriodEndDate() {
        return workPeriodEndDate;
    }

    public void setWorkPeriodEndDate(String workPeriodEndDate) {
        this.workPeriodEndDate = workPeriodEndDate;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // Override toString() for a string representation of the object

    @Override
    public String toString() {
        return "Quote{" +
                "quoteID=" + quoteID +
                ", quoteRequestID=" + quoteRequestID +
                ", price=" + price +
                ", workPeriodStartDate='" + workPeriodStartDate + '\'' +
                ", workPeriodEndDate='" + workPeriodEndDate + '\'' +
                ", dateSubmitted='" + dateSubmitted + '\'' +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
