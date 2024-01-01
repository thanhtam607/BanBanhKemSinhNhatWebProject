package vn.edu.hcmuaf.fit.model.logistic;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
//import com.mysql.cj.xdevapi.JsonParser;
import vn.edu.hcmuaf.fit.service.ReceiptService;
//import com.restfb.json.Json;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class LogisticController {
    public static String LOGISTIC_TOKEN;
    public static String FROM_DISTRICT_ID = "3695"; // thu duc
    public static String FROM_WARD_ID = "90737"; // linh trung

    public LogisticController() throws IOException {
        login("nhom27@gmail.com", "123456");
    }

    public JsonObject getJSONObjectFromConnection(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
//        System.out.println(response);
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response.toString());
//        System.out.println(element);
        JsonObject object = element.getAsJsonObject();

        return object;
    }

    public boolean register(String name, String email, String pass, String confirmPass) throws IOException {
        String urlString = "http://140.238.54.136/api/auth/register";
        String param = "?name=" + name + "&email=" + email + "&password=" + pass + "&password_confirmation=" + confirmPass;
        URL url = new URL(urlString + param);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        int responseCode = connection.getResponseCode();
        if (responseCode == 422) return false; // email da ton tai
        else if (responseCode == 201) {
            return true;
        }
        return false;
    }

    public String login(String email, String pass) throws IOException {
        String urlString = "http://140.238.54.136/api/auth/login";
        String param = "?email=" + email + "&password=" + pass;
        URL url = new URL(urlString + param);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            JsonObject object = getJSONObjectFromConnection(connection);
            String token = object.get("access_token").getAsString();
            LOGISTIC_TOKEN = token;
            return token;
        }
        return null;
    }

    public boolean changePass(String oldPass, String newPass, String confirmPass) throws IOException {
        String urlString = "http://140.238.54.136/api/auth/change_pass";
        String param = "?old_password=" + oldPass + "&new_password=" + newPass + "&new_password_confirmation=" + confirmPass;
        URL url = new URL(urlString + param);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + LOGISTIC_TOKEN);

        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);
        if (responseCode == 201)
            return true;
        return false;
    }

    public LogisticUser infoUser() throws IOException {
        String urlString = "http://140.238.54.136/api/auth/me";
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + LOGISTIC_TOKEN);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            JsonObject object = getJSONObjectFromConnection(connection);
            JsonObject data = object.get("data").getAsJsonObject();
            String id = data.get("id").getAsString();
            String name = data.get("name").getAsString();
            String email = data.get("email").getAsString();
            String emailVerifiedAt = String.valueOf(data.get("email_verified_at"));
            String createAt = data.get("created_at").getAsString();
            String updateAt = data.get("updated_at").getAsString();
            return new LogisticUser(id, name, email, emailVerifiedAt, createAt, updateAt);
        }
        return null;

    }

    public List<Province> getProvinces() throws IOException {
        String urlString = "http://140.238.54.136/api/province";
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + LOGISTIC_TOKEN);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            List<Province> res = new ArrayList<>();
            JsonObject object = getJSONObjectFromConnection(connection);
            JsonObject original = object.get("original").getAsJsonObject();
            JsonArray data = original.getAsJsonArray("data");
            for (int i = 0; i < data.size(); i++) {
                JsonObject province = data.get(i).getAsJsonObject();
                String id = province.get("ProvinceID").getAsString();
                String name = province.get("ProvinceName").getAsString();
                String code = String.valueOf(province.get("Code"));
                res.add(new Province(id, name, code));
            }
            return res;
        }
        return null;
    }

    public List<District> getDistrictByProvinceID(String provinceID) throws IOException {
        String urlString = "http://140.238.54.136/api/district";
        String param = "?provinceID=" + provinceID;
        URL url = new URL(urlString + param);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + LOGISTIC_TOKEN);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            List<District> res = new ArrayList<>();
            JsonObject object = getJSONObjectFromConnection(connection);
            JsonObject original = object.get("original").getAsJsonObject();
            JsonArray data = original.getAsJsonArray("data");
            for (int i = 0; i < data.size(); i++) {
                JsonObject district = data.get(i).getAsJsonObject();
                String proID = district.get("ProvinceID").getAsString();
                String distID = district.get("DistrictID").getAsString();
                String distName = String.valueOf(district.get("DistrictName"));
                res.add(new District(proID, distID, distName));
            }
            return res;
        }
        return null;
    }
    public List<Ward> getWardByDistrictID(String districtID) throws IOException {
        String urlString = "http://140.238.54.136/api/ward";
        String param = "?districtID=" + districtID;
        URL url = new URL(urlString + param);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + LOGISTIC_TOKEN);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            List<Ward> res = new ArrayList<>();
            JsonObject object = getJSONObjectFromConnection(connection);
            JsonObject original = object.get("original").getAsJsonObject();
            JsonArray data = original.getAsJsonArray("data");
            for (int i = 0; i < data.size(); i++) {
                JsonObject ward = data.get(i).getAsJsonObject();
                String wardCode = ward.get("WardCode").getAsString();
                String distID = ward.get("DistrictID").getAsString();
                String wardName = String.valueOf(ward.get("WardName"));
                res.add(new Ward(wardCode, distID, wardName));
            }
            return res;
        }
        return null;
    }
    public double getCalculateFee(String fromDistrictID, String fromWardID, String toDistrictID, String toWardID, int height, int length, int width, int weight) throws IOException {
        String urlString = "http://140.238.54.136/api/calculateFee";
        String param = "?from_district_id=" + fromDistrictID + "&from_ward_id=" + fromWardID + "&to_district_id=" + toDistrictID + "&to_ward_id=" + toWardID + "&height=" + height + "&length=" + length + "&width=" + width + "&Weight=" + weight;
        URL url = new URL(urlString + param);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + LOGISTIC_TOKEN);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            JsonObject object = getJSONObjectFromConnection(connection);
            JsonArray data = object.get("data").getAsJsonArray();
            double fee = data.get(0).getAsJsonObject().get("service_fee").getAsLong();
            return fee;
        }
        return 0;
    }
    public String getLeadTime(String fromDistrictID, String fromWardID, String toDistrictID, String toWardID, int height, int length, int width, int weight) throws IOException {
        String urlString = "http://140.238.54.136/api/leadTime";
        String param = "?from_district_id=" + fromDistrictID + "&from_ward_id=" + fromWardID + "&to_district_id=" + toDistrictID + "&to_ward_id=" + toWardID + "&height=" + height + "&length=" + length + "&width=" + width + "&weight=" + weight;
        URL url = new URL(urlString + param);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + LOGISTIC_TOKEN);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            JsonObject object = getJSONObjectFromConnection(connection);
            JsonArray data = object.get("data").getAsJsonArray();
            long timestamp = data.get(0).getAsJsonObject().get("timestamp").getAsLong();
            String formattedDate = data.get(0).getAsJsonObject().get("formattedDate").getAsString();
            return formattedDate;
        }
        return null;
    }

    public TransportOrder registerTranport(String fromDistrictID, String fromWardID, String toDistrictID, String toWardID, int height, int length, int width, int weight) throws IOException {
        String urlString = "http://140.238.54.136/api/registerTransport";
        String param = "?from_district_id=" + fromDistrictID + "&from_ward_id=" + fromWardID + "&to_district_id=" + toDistrictID + "&to_ward_id=" + toWardID + "&height=" + height + "&length=" + length + "&width=" + width + "&weight=" + weight;
        URL url = new URL(urlString + param);
        TransportOrder transportOrder = null;

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + LOGISTIC_TOKEN);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200 | responseCode == 201) {
            JsonObject object = getJSONObjectFromConnection(connection);
//            System.out.println(object);
            JsonObject logistic = object.get("Transport").getAsJsonObject();
            long fee = logistic.get("fee").getAsLong();
            long leadTime = logistic.get("leadTime").getAsLong();
            boolean active = logistic.get("active").getAsBoolean();
            String updatedAt = logistic.get("updated_at").getAsString();
            String createdAt = logistic.get("created_at").getAsString();
            String id = logistic.get("id").getAsString();
            transportOrder = new TransportOrder(fromDistrictID, fromWardID, toDistrictID, toWardID, height, length, width, weight, fee, leadTime, active, updatedAt, createdAt, id);
//            System.out.println(logistic);
            return transportOrder;
        }
        return null;

    }
    public String registerTranportMessage(String fromDistrictID, String fromWardID, String toDistrictID, String toWardID, int height, int length, int width, int weight) throws IOException {
        String urlString = "http://140.238.54.136/api/registerTransport";
        String param = "?from_district_id=" + fromDistrictID + "&from_ward_id=" + fromWardID + "&to_district_id=" + toDistrictID + "&to_ward_id=" + toWardID + "&height=" + height + "&length=" + length + "&width=" + width + "&weight=" + weight;
        URL url = new URL(urlString + param);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + LOGISTIC_TOKEN);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200 | responseCode == 201) {
            JsonObject object = getJSONObjectFromConnection(connection);
            String logistic = object.get("message").getAsString();
            return logistic;
        }
        return null;

    }


    public long getFeeByID(String idTransport) throws IOException {
        String urlString = "http://140.238.54.136/api/getInfoTransport";
        String param = "?id=" + idTransport;
        URL url = new URL(urlString + param);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + LOGISTIC_TOKEN);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200 | responseCode == 201) {
            JsonObject object = getJSONObjectFromConnection(connection);
            JsonArray data = object.get("data").getAsJsonArray();
            long fee = data.get(0).getAsJsonObject().get("fee").getAsLong();
            return fee;
        }
        return 0;

    }

    public List<TransportOrder> getAllInfoTransport() throws IOException {
        String urlString = "http://140.238.54.136/api/allTransports";
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + LOGISTIC_TOKEN);


        int responseCode = connection.getResponseCode();
        if (responseCode==200 | responseCode==201){
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response.toString());
            JsonArray object = element.getAsJsonArray();

        }
        return null;

    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        LogisticController controller = new LogisticController();
//       TransportOrder transportOrder = controller.registerTranport(FROM_DISTRICT_ID, FROM_WARD_ID, "2270", "231013", 12, 20,20, 400);
//        System.out.println(transportOrder);
//       System.out.println(ReceiptService.convertDate(transportOrder.getLeadTime()));
//        ReceiptService.updateDeliveryDateInBill("HD01", ReceiptService.convertDate(transportOrder.getLeadTime()));
//        System.out.println(controller.getCalculateFee(FROM_DISTRICT_ID,FROM_WARD_ID, "2270", "231013", 12, 20,20, 400));
//        System.out.println(controller.getLeadTime(FROM_DISTRICT_ID,FROM_WARD_ID, "2270", "231013", 100, 100, 100, 100).replaceAll("[TZ]", ""));
//        System.out.println(controller.registerTranport(controller.FROM_DISTRICT_ID, controller.FROM_WARD_ID, "1456", "21501", 12, 20,20, 400));
//        System.out.println(ReceiptService.convertDate(1686441599));
//        System.out.println(controller.register("Nhom17", "nhom17@gmail.com", "123456", "123456"));
//        controller.changePass("1234536", "123", "123");
//        System.out.println(controller.infoUser());

//        List<Province> provinces = controller.getProvinces();
//        for (Province province: provinces) System.out.println(province);

//        List<District> dis = controller.getDistrictByProvinceID("202");
//        for (District dt: dis) System.out.println(dt);
////
//        List<Ward> wards = controller.getWardByDistrictID("2270");
//        for (Ward w: wards) System.out.println(w);
// phường 90750, quận 3695, tp 202
        System.out.println(controller.registerTranport(FROM_DISTRICT_ID, FROM_WARD_ID, FROM_DISTRICT_ID, "90767", 100, 200, 200, 300));
        System.out.println(controller.getAllInfoTransport());
    }


}
