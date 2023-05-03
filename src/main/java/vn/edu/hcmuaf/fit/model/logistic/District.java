package vn.edu.hcmuaf.fit.model.logistic;

public class District {
    private String provinceID, districtID, districtName;

    public District(String provinceID, String districtID, String districtName) {
        this.provinceID = provinceID;
        this.districtID = districtID;
        this.districtName = districtName;
    }

    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public String toString() {
        return "District{" +
                "provinceID='" + provinceID + '\'' +
                ", districtID='" + districtID + '\'' +
                ", districtName='" + districtName + '\'' +
                '}';
    }
}
