package com.waa.PropertyManagment.filter;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SomeFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("BEFORE THE FILTER CHAIN");
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
