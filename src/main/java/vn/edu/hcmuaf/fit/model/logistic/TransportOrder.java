package vn.edu.hcmuaf.fit.model.logistic;

public class TransportOrder {
    private String fromDistrictID, fromWardID, toDistrictID, toWardID;
    private int  height, length, width, weight;
    private long fee, leadTime;
    private boolean active;
    private String updatedAt, createdAt;
    private String  id;

    public TransportOrder(String fromDistrictID, String fromWardID, String toDistrictID, String toWardID, int height, int length, int width, int weight, long fee, long leadTime, boolean active, String updatedAt, String createdAt, String id) {
        this.fromDistrictID = fromDistrictID;
        this.fromWardID = fromWardID;
        this.toDistrictID = toDistrictID;
        this.toWardID = toWardID;
        this.height = height;
        this.length = length;
        this.width = width;
        this.weight = weight;
        this.fee = fee;
        this.leadTime = leadTime;
        this.active = active;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.id = id;
    }

    public String getFromDistrictID() {
        return fromDistrictID;
    }

    public void setFromDistrictID(String fromDistrictID) {
        this.fromDistrictID = fromDistrictID;
    }

    public String getFromWardID() {
        return fromWardID;
    }

    public void setFromWardID(String fromWardID) {
        this.fromWardID = fromWardID;
    }

    public String getToDistrictID() {
        return toDistrictID;
    }

    public void setToDistrictID(String toDistrictID) {
        this.toDistrictID = toDistrictID;
    }

    public String getToWardID() {
        return toWardID;
    }

    public void setToWardID(String toWardID) {
        this.toWardID = toWardID;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public long getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(long leadTime) {
        this.leadTime = leadTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TranportOrder{" +
                "fromDistrictID='" + fromDistrictID + '\'' +
                ", fromWardID='" + fromWardID + '\'' +
                ", toDistrictID='" + toDistrictID + '\'' +
                ", toWardID='" + toWardID + '\'' +
                ", height=" + height +
                ", length=" + length +
                ", width=" + width +
                ", weight=" + weight +
                ", fee=" + fee +
                ", leadTime=" + leadTime +
                ", active=" + active +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
