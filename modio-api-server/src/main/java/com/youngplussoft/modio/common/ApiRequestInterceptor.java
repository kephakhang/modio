package com.youngplussoft.modio.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.youngplussoft.common.Message;
import com.youngplussoft.common.RequestInterceptor;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import com.youngplussoft.modio.jpa.entity.User;
import com.youngplussoft.modio.jpa.repository.UserRepository;
import io.vertx.core.json.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ApiRequestInterceptor extends RequestInterceptor {

	protected static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ApiRequestInterceptor.class);

	final int ADMIN_LEVEL = 10 ;

	final String baseUri = "/api/v3" ;
	
	@Resource
	UserRepository userRepository ;

	@Resource
	Message message ;

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		LOGGER.debug("Request URL::" + request.getRequestURL().toString()
				+ ":: Start Time=" + System.currentTimeMillis());
		request.setAttribute("startTime", startTime);
		//if returned false, we need to make sure 'response' is sent
		
		
		try {

			if( ! super.preHandle(request, response, handler) )
				return false ;

			String uri = request.getRequestURI() ;

			//MGK_IMSI
			if( uri.indexOf("swagger") >= 0 )
				return true ;


			if( uri.equals("/emailauth") ){
				LOGGER.debug("/emailauth : request mapping is OK !!!") ;
				return true ;
			}
			else if( uri.startsWith("/image/download") ){
				LOGGER.debug("/image/download : request mapping is OK !!!") ;
				return true ;
			}
			else if( uri.startsWith("/paygate/") ){
				LOGGER.debug("/paygate/ : request mapping is OK !!!") ;
				LOGGER.debug("query ; " + request.getQueryString());
				BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
				StringBuilder builder = new StringBuilder();
				String buffer;
				while ((buffer = input.readLine()) != null) {
					if (builder.length() > 0) {
						builder.append("\n");
					}
					builder.append(buffer);
				}
				LOGGER.debug("body ; " +  builder.toString());
				return true ;
			}
			else if( uri.startsWith("/kakaoLogin") ){
				LOGGER.debug("/kakaoLogin : request mapping is OK !!!") ;
				return true ;
			}
			else if( uri.startsWith("/naverLogin") ){
				LOGGER.debug("/naverLogin : request mapping is OK !!!") ;
				return true ;
			}
			else if( uri.startsWith("/googleLogin") ){
				LOGGER.debug("/googleLogin : request mapping is OK !!!") ;
				return true ;
			}
			else if( uri.startsWith("/facebookLogin") ){
				LOGGER.debug("/facebookLogin : request mapping is OK !!!") ;
				return true ;
			}
			else if( uri.startsWith("/websocket/") ){
				LOGGER.debug("Request in : " +  uri);
				String authorization = request.getHeader("Authorization") ;
				if( authorization == null ) {
					authorization = request.getHeader("authorization") ;
					if( authorization == null ) {
						throw new Exception(message.getMessage("error.oauth2.auth.needed"));
					}
				}
				else if( !authorization.replaceFirst("Bearer ", "").equals(WebsocketClient.WS_SERVER_AUTHORIZATION) ) {
					throw new Exception(message.getMessage("error.oauth2.auth.needed"));
				}
			}

			LOGGER.debug("Request in : " +  uri);


			String authorization = request.getHeader("Authorization") ;
			if( authorization == null ) {
				authorization = request.getHeader("authorization") ;
				if( authorization == null ) {
					throw new Exception(message.getMessage("error.oauth2.auth.needed"));
				}
			}


			String keys[] = authorization.replaceFirst("Bearer ", "").split(":");


			if( uri.startsWith("/api/") && keys.length==2 ) { // User AccessToken 인증필요  API

				User user = userRepository.findByAccessToken(keys[1]) ;
				if( user == null ) {
					throw new Exception(message.getMessage("error.login.token.invalid")) ;
				}

				request.setAttribute("loginUser", user);

				LOGGER.debug(uri + " : Authorization Success : " + user.getEmail());
			}
			else if( uri.startsWith("/admin/") && keys.length==2 ) { // Admin User AccessToken 인증필요  API

				User user = userRepository.findByAccessToken(keys[1]) ;
				if( user == null ) {
					throw new Exception(message.getMessage("error.login.token.invalid")) ;
				}
				else if( user.getLevel() < ADMIN_LEVEL ) {
					throw new Exception(message.getMessage("error.login.not.admin.user")) ;
				}

				request.setAttribute("loginUser", user);

				LOGGER.debug(uri + " : Admin Authorization Success : " + user.getEmail());
			}
			else if( uri.startsWith("/login") || uri.startsWith("/signup") || uri.startsWith("/accessToken") ||
					 uri.startsWith("/mobile" ) ){
				//AccessToken  체크가 불필요한  API
			}
			else {
				throw new Exception(message.getMessage("error.unknown.api.uri",  uri)) ;
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
