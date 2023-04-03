package vn.edu.hcmuaf.fit.model;

public class TypeOfCake {
    String idType;
    String name;
    public TypeOfCake(String idType, String name){
        this.idType=idType;
        this.name=name;
    }
    public TypeOfCake( String name){
        this.idType= null;
        this.name=name;
    }

    public String getIdType() {
        return idType;
    }

    public String getName() {
        return name;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setName(String name) {
        this.name = name;
    }
}
