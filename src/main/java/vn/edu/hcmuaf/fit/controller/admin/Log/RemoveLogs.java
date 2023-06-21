package vn.edu.hcmuaf.fit.controller.admin.Log;

import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@WebServlet(name = "RemoveLogs", value = "/admin/RemoveLogs")
public class RemoveLogs extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String all  = request.getParameter("all");
        if(all.equals("0")) {
            String listId = request.getParameter("ids");
            String[] idArr = listId.split(",");
            LogService.removeListLog(idArr);
        }else{
            List<Log> listLog = LogService.getListLog();
            String fromDate = request.getParameter("from");
            String toDate= request.getParameter("to");
            String level = request.getParameter("level");
            String user = request.getParameter("user");
            String content = request.getParameter("content");
            String date = request.getParameter("date");


            if(!content.equals("Tất cả")){
                listLog = LogService.findByContent(content, listLog);
            }
            if(!level.equals("0")){
                listLog = LogService.findByLevel(Integer.parseInt(level), listLog);

            }
            if(!user.equals("Tất cả")){
                listLog= LogService.findByUserName(user, listLog);
            }
            if(!date.equals("0")){
                listLog = LogService.findByDate(date, listLog);

            }

            if (!fromDate.equals("0") || !toDate.equals("0") && date.equals("0")) {
                listLog = LogService.findByDate(fromDate, toDate, listLog);

            }
            String[] idArr = new String[listLog.size()];
            for(int i=0; i<listLog.size();i++){
                idArr[i] = listLog.get(i).getId()+"";
            }
            LogService.removeListLog(idArr);
            request.getRequestDispatcher("ListLog").forward(request,response);
        }
    }

}
