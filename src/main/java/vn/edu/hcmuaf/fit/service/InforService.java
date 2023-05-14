package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.General_information;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class InforService {
    public static List<General_information> getData() {
        List<General_information> list = new LinkedList<>();
        Statement statement = DBConnect.getInstall().get();

        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT  id, type, content from general_infor");
                while(rs.next()) {
                     General_information gi = new General_information(rs.getString(1), rs.getString(2), rs.getString(3));
                    list.add(gi);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có thông tin");
        }
        return list;
    }
    public static List<General_information> getImgSlideShow() {
        List<General_information> listImgSlide = new LinkedList<>();
        List<General_information> list = InforService.getData();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getType().equals("SlideShow")){
                listImgSlide.add(list.get(i));
            }
        }
        return listImgSlide;
    }
    public static List<General_information> getImgLogo() {
        List<General_information> listImgLogo = new LinkedList<>();
        List<General_information> list = InforService.getData();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getType().equals("LogoWeb")){
                listImgLogo.add(list.get(i));
            }
        }
        return listImgLogo;
    }
    public static List<General_information> getIntroduce() {
        List<General_information> listIntroduce = new LinkedList<>();
        List<General_information> list = InforService.getData();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getType().equals("Introduce")){
                listIntroduce.add(list.get(i));
            }
        }
        return listIntroduce;
    }
    public static List<General_information> getInformation(String type) {
        List<General_information> listInfor = new LinkedList<>();
        List<General_information> list = InforService.getData();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getType().equals(type)){
                listInfor.add(list.get(i));
            }
        }
        return listInfor;
    }
    public static  void updateContent(String id,String content){
        Statement stm = DBConnect.getInstall().get();
        String sql = "UPDATE general_infor SET content = '" + content + "' WHERE id = '" + id +"'";
        try {
            stm.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static  void updateIntroduce(String id,String content){
        Statement stm = DBConnect.getInstall().get();
        String sql = "UPDATE general_infor SET content = '" + content + "' WHERE id = '" + id +"'";
        try {
            stm.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        List<General_information> list = InforService.getImgSlideShow();
//        System.out.println(list.get(1).getContent());
//        updateContent("GI001", "img/hero/GI001.jpg");
    }
}
