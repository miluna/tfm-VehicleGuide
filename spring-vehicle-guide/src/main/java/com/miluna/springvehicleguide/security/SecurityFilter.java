package com.miluna.springvehicleguide.security;

import com.miluna.springvehicleguide.models.Error;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(value = "SecurityFilter")
public class SecurityFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(SecurityFilter.class);

    @Autowired
    @Qualifier(value = "UserDetails")
    private UserDetailsImpl userDetails;

    public SecurityFilter(){}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String header = req.getHeader("Authorization");
            String token = getToken(header);

            if (header.startsWith("Basic ")) {
                LOG.debug("Detected Basic Authorization");

            } else if (header.startsWith("Bearer ")) {
                LOG.debug("Detected Jwt Authorization");
            }

        }catch (Exception e) {
            Error err = new Error(e.getMessage());

            HttpServletResponse res = (HttpServletResponse) response;
            res.setStatus(401);
            PrintWriter out = res.getWriter();
            out.print(err);
            out.flush();
        }
    }

    @Override
    public void destroy() {

    }

    private String getToken(String header) throws Exception {
        Exception e = new Exception("Unauthorized");

        if (header != null) {
            if (header.startsWith("Bearer ") || header.startsWith("Basic ")){

                String[] tokens = header.split(" ");
                return tokens[tokens.length - 1];
            }
            throw e;
        }
        throw e;
    }
}
