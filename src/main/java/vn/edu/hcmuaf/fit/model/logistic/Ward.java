package vn.edu.hcmuaf.fit.model.logistic;

public class Ward {
    private String wardCode, districtID, wardName;

    public Ward(String wardCode, String districtID, String wardName) {
        this.wardCode = wardCode;
        this.districtID = districtID;
        this.wardName = wardName;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "wardCode='" + wardCode + '\'' +
                ", districtID='" + districtID + '\'' +
                ", wardName='" + wardName + '\'' +
                '}';
    }
}
