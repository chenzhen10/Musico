package by.epam.kimbar.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        if(((HttpServletRequest)servletRequest).getSession(false).getAttribute("user") != null){

        }else {
            ((HttpServletResponse) servletResponse).sendRedirect("controller?command=go_to_authentication_page");
        }

    }

    @Override
    public void destroy() {

    }
}
