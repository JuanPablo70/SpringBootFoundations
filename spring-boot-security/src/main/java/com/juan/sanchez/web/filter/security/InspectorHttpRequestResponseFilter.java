package com.juan.sanchez.web.filter.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component("inspectorFilter")
class InspectorHttpRequestResponseFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		System.out.println("InspectorHttpRequestResponseFilter");
		System.out.println(" HttpServletRequest");
		System.out.println("  ContextPath  :" + httpRequest.getContextPath());
		System.out.println("  RequestURL   :" + httpRequest.getRequestURL().toString());
		System.out.println("  RequestURI   :" + httpRequest.getRequestURI());
		System.out.println("  Method       :" + httpRequest.getMethod());
		System.out.println("  ContentType  :" + httpRequest.getContentType());
		System.out.println("  RemoteUser   :" + httpRequest.getRemoteUser());
		System.out.println("  Protocol     :" + httpRequest.getProtocol());
		if(httpRequest.getUserPrincipal() != null) {
			System.out.println("  UserPrincipal:" + httpRequest.getUserPrincipal());
			System.out.println("  UserPrincipal:" + httpRequest.getUserPrincipal().getName());
		}
		chain.doFilter(request, response);
		System.out.println(" HttpServletResponse");
		System.out.println("  Status       :" + httpResponse.getStatus());
		System.out.println("  ContentType  :" + httpResponse.getContentType());
	}

}
