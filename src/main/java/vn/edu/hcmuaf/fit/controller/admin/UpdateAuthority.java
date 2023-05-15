package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateAuthority", value = "/admin/UpdateAuthority")
public class UpdateAuthority extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = (List<User>) UserService.getListEmployee();
        String[] id = new String[userList.size()];
        String[] addvalue = new String[userList.size()];
        int[] add = new int[userList.size()];
        String[] editvalue = new String[userList.size()];
        int[] edit = new int[userList.size()];
        String[] deletevalue = new String[userList.size()];
        int[] delete = new int[userList.size()];
        for(int i = 0; i < userList.size(); i++){
            id[i] = request.getParameter("idnv" + i);
            addvalue[i] = request.getParameter("isadd" + i);
            if (addvalue[i] == null) {
                addvalue[i] = String.valueOf(userList.get(i).getIsadd());
            }
            add[i] = Integer.parseInt(addvalue[i]);
            editvalue[i] = request.getParameter("isedit" + i);
            if (editvalue[i] == null) {
                editvalue[i] = String.valueOf(userList.get(i).getIsedit());
            }
            edit[i] = Integer.parseInt(editvalue[i]);
            deletevalue[i] = request.getParameter("isdelete" + i);
            if (deletevalue[i] == null) {
                deletevalue[i] = String.valueOf(userList.get(i).getIsdelete());
            }
            delete[i] = Integer.parseInt(deletevalue[i]);
            UserService.updateAdd(id[i], add[i]);
            UserService.updateEdit(id[i], edit[i]);
            UserService.updateDelete(id[i], delete[i]);
        }
        response.sendRedirect("AdminDecentralization.jsp");
    }
}