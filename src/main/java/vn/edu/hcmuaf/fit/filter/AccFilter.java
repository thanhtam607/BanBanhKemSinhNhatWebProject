package vn.edu.hcmuaf.fit.filter;

import vn.edu.hcmuaf.fit.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AccFilter", urlPatterns = {"/admin/ListReceipt_Admin", "/admin/ListReceipt_full_Admin","/admin/EditUser","/admin/ListProduct_Admin","/admin/ListCustomer"})
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
        if(auth != null && auth.checkRole()){
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse) response).sendRedirect("../blank_page.jsp");
//            ((HttpServletResponse) response).sendRedirect("/signin.jsp");
        }
    }
}
