package com.youngplussoft.common;

import com.youngplussoft.jpa.entity.Token;
import com.youngplussoft.jpa.repository.TokenRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor extends HandlerInterceptorAdapter {

	protected static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

	final int ADMIN_LEVEL = 10 ;

	final String baseUri = "/api/v3" ;

	@Resource
	private TokenRepository tokenRepository ;
	
	@Resource
	private Message message ;

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		LOGGER.debug("Request URL::" + request.getRequestURL().toString()
				+ ":: Start Time=" + System.currentTimeMillis());
		request.setAttribute("startTime", startTime);
		//if returned false, we need to make sure 'response' is sent
		
		
		try {

			String uri = request.getRequestURI() ;

			LOGGER.debug("Request in : " +  uri);

			if( request.getMethod().toUpperCase().equals("OPTIONS") ) {

				if( uri.startsWith("/login") ||
						uri.startsWith("/signup") ||
						uri.startsWith("/mobile") ||
						uri.startsWith("/oauth2") ||
						uri.startsWith("/admin") ||
						uri.startsWith(this.baseUri) ) {

					response.reset();
					response.setHeader("Access-Control-Allow-Origin", "*");
					response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
					response.setHeader("Access-Control-Max-Age", "10");
					response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
					response.setContentType("application/json; charset=utf-8");
					response.setCharacterEncoding("utf-8");
					response.setStatus(HttpServletResponse.SC_OK);
					response.getWriter().write("{\"success\":true}") ;
					response.getWriter().flush();
				}
				else {
					response.reset();
					response.setHeader("Access-Control-Allow-Origin", "*");
					response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
					response.setHeader("Access-Control-Max-Age", "10");
					response.setContentType("application/json; charset=utf-8");
					response.setCharacterEncoding("utf-8");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.getWriter().write(message.getError("Invalid Uri !!!").toString()) ;
					response.getWriter().flush();
				}
				return false ;
			}
			else if( ! uri.startsWith("/oauth2/") &&  ! uri.startsWith("/emailauth") &&
					 ! uri.startsWith("/kakaoLogin") &&
					 ! uri.startsWith("/image/download") && ! uri.startsWith("/paygate/")  ){ // Authentication 인증필요  API

				//MGK_IMSI
//				if( uri.indexOf("swagger") >= 0 )
//					return true ;

				String authorization = request.getHeader("Authorization") ;
				if( authorization == null ) {
					authorization = request.getHeader("authorization") ;
					if( authorization == null ) {
						throw new Exception(message.getMessage("error.oauth2.auth.needed"));
					}
				}
				
				System.out.println(authorization);
				
				String keys[] = authorization.replaceFirst("Bearer ", "").split(":");

				if( authorization.startsWith("Bearer ") ) {

					Token token = tokenRepository.findByToken(keys[0]) ;
					LOGGER.debug("token : ", token.getToken());
					if( token == null ) {
						throw new Exception(message.getMessage("error.oauth2.token.invalid")) ;
					}
				}
				else {
					throw new Exception(message.getMessage("error.oauth2.bearer.invalid")) ;
				}

				//toss to ApiServer
			}
			else { //  /oauth2/  api pass
				
			}
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			response.reset();
			response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
	        response.setHeader("Access-Control-Max-Age", "10");
	        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
			response.setContentType("application/json; charset=utf-8");
			response.setCharacterEncoding("utf-8");
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write(message.getError(e.getMessage()).toString()) ;
			response.getWriter().flush();
			return false ;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Request URL::" + request.getRequestURL().toString()
				+ " Sent to Handler :: Current Time=" + System.currentTimeMillis());
		//we can add attributes in the modelAndView and use that in the view page
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		System.out.println("Request URL::" + request.getRequestURL().toString()
				+ ":: End Time=" + System.currentTimeMillis());
		System.out.println("Request URL::" + request.getRequestURL().toString()
				+ ":: Time Taken=" + (System.currentTimeMillis() - startTime));
	}

}
