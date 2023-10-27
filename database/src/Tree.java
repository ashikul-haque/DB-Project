public class Tree {
    private int treeID;
    private String size;
    private double height;
    private String location;
    private double distanceToHouse;
    private String picture1;
    private String picture2;
    private String picture3;

    // Default constructor
    public Tree() {
    }

    // Constructor with parameters
    public Tree(int treeID, String size, double height, String location, double distanceToHouse,
                String picture1, String picture2, String picture3) {
        this.treeID = treeID;
        this.size = size;
        this.height = height;
        this.location = location;
        this.distanceToHouse = distanceToHouse;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
    }
    
    public Tree(int treeID, String size, double height, String location, double distanceToHouse) {
		this.treeID = treeID;
		this.size = size;
		this.height = height;
		this.location = location;
		this.distanceToHouse = distanceToHouse;
    } 

    // Getters and Setters

    public int getTreeID() {
        return treeID;
    }

    public void setTreeID(int treeID) {
        this.treeID = treeID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getDistanceToHouse() {
        return distanceToHouse;
    }

    public void setDistanceToHouse(double distanceToHouse) {
        this.distanceToHouse = distanceToHouse;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    // You can also override toString() if you want a string representation of the object

    @Override
    public String toString() {
        return "Tree{" +
                "treeID=" + treeID +
                ", size='" + size + '\'' +
                ", height=" + height +
                ", location='" + location + '\'' +
                ", distanceToHouse=" + distanceToHouse +
                ", picture1='" + picture1 + '\'' +
                ", picture2='" + picture2 + '\'' +
                ", picture3='" + picture3 + '\'' +
                '}';
    }
}
