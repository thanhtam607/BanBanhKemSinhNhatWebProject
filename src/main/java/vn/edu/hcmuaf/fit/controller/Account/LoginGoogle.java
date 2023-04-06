package vn.edu.hcmuaf.fit.controller.Account;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import vn.edu.hcmuaf.fit.Google.Constants;
import vn.edu.hcmuaf.fit.Google.UserGoogleDTO;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Customer;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.service.CartService;
import vn.edu.hcmuaf.fit.service.CustomerService;
import vn.edu.hcmuaf.fit.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginGoogle", value = "/LoginGoogle")
public class LoginGoogle extends HttpServlet {
    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }
    public static UserGoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDTO googlePojo = new Gson().fromJson(response, UserGoogleDTO.class);

        return googlePojo;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        UserGoogleDTO userGG = getUserInfo(accessToken);
        User user = new User();
        if(UserService.checkEmail(userGG.getEmail())){

            user.setId(userGG.getId());
            user.setName(userGG.getName());
            user.setEmail(userGG.getEmail());
            user.setRole(0);
            user.setStatus(0);
            user.setType("google");
            UserService.addAccGG(user);
            Customer newCus = new Customer();
            CustomerService.registerKH(newCus,user);
        }else{
            user=UserService.findByEmail(userGG.getEmail());
        user.setType("google");
        UserService.updateType(userGG.getEmail(), "google");}


        HttpSession session = request.getSession(true);
        session.setAttribute("auth", user);

        Customer customer = CustomerService.getCusByIdAcc(user.getId());
        session.setAttribute("cust", customer);

        List<ItemProductInCart> listItemCart = CartService.findItemCartByIdUser(user.getId());
        session.setAttribute("itemCart", listItemCart);
        response.sendRedirect(request.getContextPath() + "/Index");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
