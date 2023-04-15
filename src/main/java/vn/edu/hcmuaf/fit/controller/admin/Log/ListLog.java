package vn.edu.hcmuaf.fit.controller.admin.Log;



import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "ListLog", value = "/admin/ListLog")
public class ListLog  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> listLog = LogService.getListLog();
        request.setAttribute("listLog", listLog);

        List<String> listUser = LogService.getListUser();
        request.setAttribute("listUser", listUser);

        List<String> listContent = LogService.getListContent();
        request.setAttribute("listContent", listContent);

        List<String> listLevel = new ArrayList<>();
        listLevel.add("Nguy hiểm");
        listLevel.add("Cảnh báo");
        listLevel.add("Bình thường");
        request.setAttribute("listUser", listUser);

        String numPage = request.getParameter("page");
        if(numPage == null || numPage.equals("0")){
            numPage = "1";
        }
        int page = Integer.parseInt(numPage);
        List<Log> listPa = new ArrayList<>();
        int begin = (page - 1) * 10;
        int endList = begin + 10;
        if (begin > listLog.size() - 10) {
            endList = listLog.size();
        }
        for (int i = begin; i < endList; i++) {
            listPa.add(listLog.get(i));
        }
        int total = listLog.size();
        int endPage = total / 10;
        if(total % 10 != 0){
            endPage++;
        }

        request.setAttribute("endPage", endPage);
        request.setAttribute("tag", page);
        request.setAttribute("listPa", listPa);
        request.getRequestDispatcher("logs.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
