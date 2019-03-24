package by.epam.kimbar.controller.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * The EncodingFilter represents encoding in UTF-8 for all pages
 *
 * @see javax.servlet.Filter;
 */
public class EncodingFilter implements Filter {
    /**
     * The variable is used for encoding storage
     */
    private static final String encoding = "utf-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /** This method implements encoding in the {@code servletResponse.setCharacterEncoding(encoding);} line */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setCharacterEncoding(encoding);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
