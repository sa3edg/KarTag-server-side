package com.kartag.common.filters;

import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.kartag.server.model.SystemUser;


import java.io.IOException;

public class SecurityFilter implements Filter, SecurityHelper {
	/*
	 * configuration properties file
	 */
//    private Properties config = new Properties();
	
	private String loginAction = null;
	
	private String frontControllerURI = null;
	
	private String onError = null;
	
	private String restrictedUserUrl = null;
	
	private String accessDenied = null;
	
	
    public void init(FilterConfig filterConfig) throws ServletException {
    	loginAction = filterConfig.getInitParameter("loginAction");
    	frontControllerURI = filterConfig.getInitParameter("frontCOntrollerURI");
    	onError = filterConfig.getInitParameter("onError");
    	restrictedUserUrl = filterConfig.getInitParameter("userRestrictedResourceUrl");
    	accessDenied = filterConfig.getInitParameter("accessDeniedUrl");
    	
    }

    public void destroy() {
		//release resources obtained in init here
    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        
    	HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if(getOriginalUri(request).equals(loginAction) || getOriginalUri(request).equals(frontControllerURI))
        {
           chain.doFilter(request, response);
        }
        else
        {
           //ensure that the session is created if not present already
           HttpSession session = request.getSession(false);
           SystemUser user = null;
           if(session != null)
           {
              user = (SystemUser)session.getAttribute("USER");
           }
           //if userId is not present in session, get it from request/header and store it

           if(user == null) { //if the security helper is invalid
               //create and store a security helper implementation
               //in session by supplying userId.
        	   RequestDispatcher dispatch = request.getRequestDispatcher(onError);  
        	   dispatch.forward(request, response);  
		   }
           else
           {
        	   chain.doFilter(request, response);
           }
        }

        //handle user roles here
//        String requestUri = request.getRequestURI(); //obtain the requested URI
//		boolean accessAllowed = isAccessAllowed(requestUri);
//		if(accessAllowed) {
//			chain.doFilter(request, response);
//		}
//		else{
//			response.sendRedirect(accessDenied);
//		}
    }
    
    private String getOriginalUri(HttpServletRequest request) {
        return request.getServletPath();
    }

	@Override
	public boolean isAccessAllowed(String uri) {
		// TODO Auto-generated method stub
		if(uri.startsWith(restrictedUserUrl))
			return false;
		return true;
	}

	@Override
	public boolean isViewableField(String page, String attribute) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEditableField(String page, String attribute) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void refreshCache() {
		// TODO Auto-generated method stub
		
	}
}
