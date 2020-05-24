package com.youngplussoft.jpa.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.youngplussoft.common.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Controller
public class BaseController implements Filter {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	protected final String basePath = "/api/v3/";


	/*
	 *  session  값을 가져오는 함수
	 * @return session  값
	 */
	public static HttpSession session() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}
	
	/*
	 * 외부 도메인 접근 허가 필터
	 * @param servletResponse
	 * @param filterChain
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "10");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public void destroy() {
 
    }
	
	
	public static String encodeURIComponent(String s)
	  {
	    String result = null;
	 
	    try
	    {
	      result = URLEncoder.encode(s, "UTF-8")
	                 .replaceAll("\\+", "%20")
	                 .replaceAll("\\%21", "!")
	                 .replaceAll("\\%27", "'")
	                 .replaceAll("\\%28", "(")
	                 .replaceAll("\\%29", ")")
	                 .replaceAll("\\%7E", "~");
	    }
	 
	    // This exception should never occur.
	    catch (UnsupportedEncodingException e)
	    {
	      result = s;
	    }
	 
	    return result;
	  }
}
