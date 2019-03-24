package by.epam.kimbar.controller.filter;


import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final String encoding = "utf-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setCharacterEncoding(encoding);

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
