package vn.edu.hcmuaf.fit.controller.admin.Log;

import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "LogFilter", value = "/admin/LogFilter")
public class LogFilter  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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

        String i = "";
        PrintWriter out = response.getWriter();
        for(Log log: listPa){
            if(log.getLevel()==1){
                i = "<i class=\"bi bi-check-circle-fill text-success\"></i>\n";
            }else if(log.getLevel()==2){
               i= "   <i class=\"fas fa-exclamation-triangle text-warning\"></i>\n" ;
            }
            else{
                i=  " <i class=\"fas fa-skull-crossbones text-danger\"></i>\n";
            }
            out.println("<tr >\n" +
                    "                      <td>"+log.getCreateAt()+"</td>\n" +
                    "                      <td class=\"level-log\">\n" + i+
                    "                      </td>\n" +
                    "                      <td>"+log.getUser()+"</td>\n" +
                    "                      <td>"+log.getContent()+"</td>\n" +
                    "                      <td>"+log.getSrc()+"</td>\n" +
                    "                      <td class=\"check\" >\n" +
                    "                        <input class=\"form-check-input\" type=\"checkbox\" name=\"check\" value=\"<%=log.getId()%>\"></td>\n" +
                    "                      <td>\n" +
                    "                    </tr>");
        }


        request.setAttribute("listPa", listPa);

    }

}
