package web.filter;

import domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PrivilegeFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request= (HttpServletRequest) servletRequest;

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser==null){
            request.setAttribute("msg","你未登陸,沒有訪問權限");
            request.getRequestDispatcher("/jsp/info.jsp").forward(servletRequest,servletResponse);
        }else{
            //如果存在,放行
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
