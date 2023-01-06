package vn.edu.hcmuaf.fit.filter;

import vn.edu.hcmuaf.fit.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AccFilter", urlPatterns = {"/admin/Admin","/admin/EditUser"})
public class AccFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(true);

        User auth = (User) session.getAttribute("auth");
        if(auth != null){

            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse) response).sendRedirect("/BanBanhKemSinhNhatWebProject/signin.jsp");
        }
    }
}
