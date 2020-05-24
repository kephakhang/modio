package com.youngplussoft.modio.jpa.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.youngplussoft.common.KeyGenerator;
import com.youngplussoft.common.Message;
import io.vertx.core.json.JsonObject;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import com.youngplussoft.modio.jpa.entity.Login;
import com.youngplussoft.modio.jpa.entity.User;
import com.youngplussoft.modio.jpa.repository.UserRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Controller
public class UserApiController extends ApiBaseController {


	protected static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);
	private final long MOBILE_CONFIRM_TIMEOUT = 600000 ; //10분
	
	final String emailAuthHtml =
			"<div style='width:100%;text-align:center;margin:60px auto 0;'>\n"+
			"	<div style='width:700px;border: 1px solid #ccc!important;padding:30px;border-radius: 16px!important;'>\n"+
			"		<div style='border-left: 6px solid #ccc!important;border-color: #2196F3!important; color: #777!important;background-color: #ddffff!important;width:100%; height:50px; padding: 5px;'>\n"+
			"			<h1>\n"+
			"				[MoDiO] 회원 가입 인증 이메일 입니다. \n"+
			"			</h1>\n"+
			"		</div>\n"+
			"		<div style='width:100%; padding:10px;'>\n"+
			"			<p style='color: #777!important;'>\n"+
			"				아래 버튼을 클릭하셔서 이메일 인증을 해 주시기 바랍니다.<br>\n"+
			"				만약 이메일 인증이 정상적으로 안될 경우에는, 아래 링크를 브라우져에 복사해서 인증해 주시기 바랍니다.<br>\n"+
			"				<a href='#URL#' style='color:#025aa5;'>#URL#</a><br><br><br>\n"+
			"				<a href='#URL#' style='border-radius: 5px!important;color: #fff;background-color: #025aa5;border-color: #01549b;padding:10px;'>이메일 인증</a>\n"+
			"			</p>\n"+
			"			<br>\n"+
			"			<br>\n"+
			"			<p><a href='#HOME#' style='color:#025aa5;'>MoDiO</a></p>\n"+
			"		</div>\n"+
			"	</div>\n"+
			"</div>" ;


	@ApiOperation(value = "", notes = "Check `Login` **login** 은 로그인 정보 값이다. 리턴 값은 사용자 정보 오브젝트 이다.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = User.class),
							@ApiResponse(code = 400, message = "Request error", response = Error.class),
							@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = "/login", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.POST)
	ResponseEntity<Object> login(HttpServletResponse response,
			@ApiParam(value = "멤버 정보", required = true) @RequestBody Login login, 
			SessionStatus sessionStatus

	) {
		
		try {

			LOGGER.debug("email : " + login.getEmail());
			Document user = userTemplate.findByEmail((String)login.getEmail()) ;

			if( user == null ) {
				throw new Exception(message.getMessage("error.login.not.user"));
			}

			if( !user.getBoolean("isAvail") ) {
				throw new Exception(message.getMessage("error.login.need.auth")) ;
			}
			
			if( !KeyGenerator.password(login.getPassword()).equals(user.getString("password")) ) {
				LOGGER.debug(login.getPassword() + ":" + KeyGenerator.password(login.getPassword()) + ":" + user.getString("password")) ;
				throw new Exception(message.getMessage("error.login.wrong")) ;
			}


			user.put("accessToken", KeyGenerator.generateKey()) ;
			user.put("updtime", new Date());

			LOGGER.debug("user.id : " + user.getObjectId("_id")) ;
			LOGGER.debug("user : " + user) ;
			try {
				mongoTemplate.save(user, "user");
			}
			catch(Exception ee){
				throw new Exception(message.getMessage("error.user.insert")) ;
			}
			
			user.put("password", "");
			user.put("emailKey", "");
			user.put("mobileKey","");
			
			return new ResponseEntity<Object>(user.toJson(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}

	@ApiOperation(value = "", notes = "Check `accessToken` **user** 는 사용자 정보 값이다. 리턴 값은 사용자 정보 오브젝트 이다.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = User.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = "/accessToken", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.POST)
	ResponseEntity<Object> accessToken(HttpServletResponse response,
								 @ApiParam(value = "멤버 정보", required = true) @RequestBody String userStr,
								 SessionStatus sessionStatus

	) {

		try {

			Document user = Document.parse(userStr) ;

			LOGGER.debug("accessToken : " + user.getString("accessToken"));
			user = userTemplate.findByEmailAndAccessToken(user.getString("email"), user.getString("accessToken")) ;

			if( user == null ) {
				throw new Exception(message.getMessage("error.login.not.user"));
			}

			user.put("accessToken", KeyGenerator.generateKey()) ;
			user.put("updtime", new Date());

			LOGGER.debug("user.id : " + user.getObjectId("_id")) ;
			LOGGER.debug("user : " + user) ;
			mongoTemplate.save(user, "user") ;

			user.put("password", "");
			user.put("emailKey", "");
			user.put("mobileKey","");

			return new ResponseEntity<Object>(user.toJson(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "", notes = "Get `user` **user** 는 사용자 정보 값이다. 리턴 값은 사용자 정보 오브젝트 이다.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = User.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath + "/user", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.GET)
	ResponseEntity<Object> getUser(HttpServletRequest request, HttpServletResponse response,
									   SessionStatus sessionStatus

	) {

		try {
			Gson gson = new Gson() ;
			return new ResponseEntity<Object>(gson.toJson(this.getSessionUser(request)), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "", notes = "signup `User` **user** 는   회원 정보 값이다. 리턴 값은 성공/실패 여부 판단 값이다. ", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response"),
							@ApiResponse(code = 400, message = "Request error", response = Error.class),
							@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = "/signup", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.POST)
	ResponseEntity<Object> signup(HttpServletRequest request, HttpServletResponse response,
			@ApiParam(value = "멤버 정보", required = true) @RequestBody String userStr,
			SessionStatus sessionStatus

	) {
		
		try {

			Document user = Document.parse(userStr) ;
			user.put("password", KeyGenerator.password(user.getString("password")));

			String key = KeyGenerator.generateKey() ;
			user.put("emailKey", key) ;
			user.put("isAvail", true);
			user.put("isEmailAuth", false);
			user.put("isMobileAuth", true);
			user.put("accessToken", KeyGenerator.generateKey());
	
	
			try {
				mongoTemplate.insert(user, "user") ;
			}
			catch(Exception ee) {
				ee.printStackTrace();
				throw new Exception(message.getMessage("error.insert.user")) ;
			}
			
			String apiServer = "https//" + request.getServerName() + ":" + request.getServerPort() ;
			String url = apiServer + "/emailauth?email=" + user.getString("email") + "&emailKey=" + key ;
			String emailContent = emailAuthHtml ;
			emailContent = emailContent.replaceAll("#URL#", url).replaceAll("#HOME#", homeUrl) ;
			try {
				mail.smtpSend(user.getString("email"), user.getString("name"), "[MoDiO]회원가입 인증 이메일 입니다", emailContent) ;
			}
			catch(Exception ee){
				ee.printStackTrace();
//				userRepository.delete(user);
//				throw ee ;
			}

			return new ResponseEntity<Object>(user.toJson(), HttpStatus.OK);
		}
		catch(Exception e) {
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}

	@ApiOperation(value = "", notes = "check `emailauth` **emailKey** 는   이메일 주소 인증 값이다. 리턴 값은 성공/실패 여부 판단 값이다. ", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response"),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@GetMapping(value = "/emailauth", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" })
	public String emailAuth(
			@ApiParam(value = "멤버 정보", required = true) @RequestParam(value = "email", required = true) String email,
			@ApiParam(value = "이메일  인증 키", required = true) @RequestParam(value = "emailKey", required = true) String emailKey,
			HttpServletRequest request, Model model
	) {
		try {

			Document user = userTemplate.findByEmailAndEmailKey(email, emailKey);
			if( user == null ) {

				throw new Exception(message.getMessage("error.email.key.wrong")) ;
			}

			user.put("isEmailAuth", true);
			mongoTemplate.save(user, "user") ;

			model.addAttribute("email", email) ;

			return "email-confirm" ;

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			model.addAttribute("email", email) ;
			model.addAttribute("error", message.getError(e.getMessage())) ;
			return "code500" ;
		}
	}

	@ApiOperation(value = "", notes = "check `kakaoLogin` 리턴 값은 성공/실패 여부 판단 값이다. ", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response"),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@GetMapping(value = "/kakaoLogin", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" })
	public ResponseEntity<Object> kakaoLogin(
			HttpServletRequest request, @RequestParam(value = "code", required = true) String code
	) {
		try {

			LOGGER.debug("/kakaoLogin/ : request mapping is OK !!!");
			LOGGER.debug("code ; " + code);
			BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String buffer;
			while ((buffer = input.readLine()) != null) {
				if (builder.length() > 0) {
					builder.append("\n");
				}
				builder.append(buffer);
			}
			LOGGER.debug("body ; " + builder.toString());
			JsonObject res = null;
			if (builder.toString() == null || builder.toString().trim().equals("")) {
				res = new JsonObject("{}");
			}
			else {
				res = new JsonObject(builder.toString());
			}
			res.put("cmd", "push");
			res.put("devId", "1234567"); //MGK_IMSI
			LOGGER.debug("kakaoLogin : " + res.toString()) ;
			this.websocketClient.sendMessage(res);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(Message.exceptionToString(e));
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "", notes = "check `naverLogin` 리턴 값은 성공/실패 여부 판단 값이다. ", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response"),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@GetMapping(value = "/naverLogin", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" })
	public ResponseEntity<Object> naverLogin(
			HttpServletRequest request
	) {
		try {

			LOGGER.debug("/naverLogin/ : request mapping is OK !!!");
			//LOGGER.debug("code ; " + code);
			BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String buffer;
			while ((buffer = input.readLine()) != null) {
				if (builder.length() > 0) {
					builder.append("\n");
				}
				builder.append(buffer);
			}
			LOGGER.debug("body ; " + builder.toString());
			JsonObject res = null;
			if (builder.toString() == null || builder.toString().trim().equals("")) {
				res = new JsonObject("{}");
			}
			else {
				res = new JsonObject(builder.toString());
			}
			res.put("cmd", "push");
			res.put("devId", "1234567"); //MGK_IMSI
			LOGGER.debug("naverLogin : " + res.toString()) ;
			this.websocketClient.sendMessage(res);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(Message.exceptionToString(e));
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "", notes = "check `googleLogin` 리턴 값은 성공/실패 여부 판단 값이다. ", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response"),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@GetMapping(value = "/googleLogin", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" })
	public ResponseEntity<Object> googleLogin(
			HttpServletRequest request
	) {
		try {

			LOGGER.debug("/googleLogin/ : request mapping is OK !!!");
			//LOGGER.debug("code ; " + code);
			BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String buffer;
			while ((buffer = input.readLine()) != null) {
				if (builder.length() > 0) {
					builder.append("\n");
				}
				builder.append(buffer);
			}
			LOGGER.debug("body ; " + builder.toString());
			JsonObject res = null;
			if (builder.toString() == null || builder.toString().trim().equals("")) {
				res = new JsonObject("{}");
			}
			else {
				res = new JsonObject(builder.toString());
			}
			res.put("cmd", "push");
			res.put("devId", "1234567"); //MGK_IMSI
			LOGGER.debug("googleLogin : " + res.toString()) ;
			this.websocketClient.sendMessage(res);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(Message.exceptionToString(e));
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "", notes = "check `facebookLogin` 리턴 값은 성공/실패 여부 판단 값이다. ", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response"),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@GetMapping(value = "/facebookLogin", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" })
	public ResponseEntity<Object> facebookLogin(
			HttpServletRequest request
	) {
		try {

			LOGGER.debug("/facebookLogin/ : request mapping is OK !!!");
			//LOGGER.debug("code ; " + code);
			BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String buffer;
			while ((buffer = input.readLine()) != null) {
				if (builder.length() > 0) {
					builder.append("\n");
				}
				builder.append(buffer);
			}
			LOGGER.debug("body ; " + builder.toString());
			JsonObject res = null;
			if (builder.toString() == null || builder.toString().trim().equals("")) {
				res = new JsonObject("{}");
			}
			else {
				res = new JsonObject(builder.toString());
			}
			res.put("cmd", "push");
			res.put("devId", "1234567"); //MGK_IMSI
			LOGGER.debug("facebookLogin : " + res.toString()) ;
			this.websocketClient.sendMessage(res);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(Message.exceptionToString(e));
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

