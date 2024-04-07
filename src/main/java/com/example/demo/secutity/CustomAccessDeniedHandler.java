package com.example.demo.secutity;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // Check the request URL or any other criteria to determine which access denied page to show
        String deniedPage = determineDeniedPage(request.getRequestURI());

        // Forward to the appropriate access denied page
        response.sendRedirect(deniedPage);
	}
        
        private String determineDeniedPage(String requestURI) {
            // Logic to determine which access denied page to show based on the request URI
        	if(requestURI.startsWith("/add-clients")) {
                return "/access-denied-clients"; // first access denied page
            }
        	if(requestURI.startsWith("/projects-add")) {
        		return "/access-denied-projects"; // first access denied page
        	}
        	if(requestURI.startsWith("/emp-department-add")) {
        		return "/access-denied-employees"; // first access denied page
        	}
            else{
            	return "/access-denied"; // Default access denied page
            }

	}

}
