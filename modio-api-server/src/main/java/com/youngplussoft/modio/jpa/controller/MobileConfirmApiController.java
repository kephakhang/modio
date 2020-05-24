package com.youngplussoft.modio.jpa.controller;

import com.youngplussoft.common.KeyGenerator;
import com.youngplussoft.common.Message;
import com.youngplussoft.common.Sms;
import com.youngplussoft.modio.jpa.entity.MobileConfirm;
import com.youngplussoft.modio.jpa.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Controller
public class MobileConfirmApiController extends ApiBaseController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(MobileConfirmApiController.class);
	private static final long MOBILE_CONFIRM_TIMEOUT = 600000 ; //10분
	
	private static final String emailAuthHtml =
			"<div style='width:100%;text-align:center;margin:60px auto 0;'>\n"+
			"	<div style='width:700px;border: 1px solid #ccc!important;padding:30px;border-radius: 16px!important;'>\n"+
			"		<div style='border-left: 6px solid #ccc!important;border-color: #2196F3!important; color: #777!important;background-color: #ddffff!important;width:100%; height:50px; padding: 5px;'>\n"+
			"			<h1>\n"+
			"				[ZoeNUVO:Bonchowi] 회원 가입 인증 이메일 입니다. \n"+
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
			"			<p><a href='#HOME#' style='color:#025aa5;'>ZoeNUVO:Bonchowi</a></p>\n"+
			"		</div>\n"+
			"	</div>\n"+
			"</div>" ;

	private static final String smsText = "ZoeNUVO:Bonchowi:인증번호발송 - \"인증번호:#NUM#\"" ;


	public  String randomSend(String mobile){

		String randStr = KeyGenerator.generateMobileAuth() ;
		if( Sms.send(mobile, smsText.replaceAll("#NUM#", randStr)) ) {

			MobileConfirm mc = null;
			try {
				mc = mobileConfirmRepository.findByOne(mobile);
				if( mc != null ){
					mc.setAuthcode(randStr);
					mc.setUpdtime(new Date());
					mobileConfirmRepository.save(mc) ;
					if( mc == null )
						throw new Exception("cannot save mobileConfirm data : " + mc.getId() + ":" + mc.getAuthcode()) ;
				}
				else {
					mc = new MobileConfirm() ;
					mc.setId(mobile);
					mc.setAuthcode(randStr);
					mc.setUpdtime(new Date());
					mobileConfirmRepository.save(mc) ;
					throw new Exception("cannot save mobileConfirm data : " + mc.getId() + ":" + mc.getAuthcode()) ;
				}

				return randStr ;
			} catch (Exception e) {

				LOGGER.error(Message.exceptionToString(e)) ;
				return null ;
			}
		}
		else {
			return null ;
		}

	}

	@ApiOperation(value = "", notes = "Check `mobile` & Send authcode **mobile** 은 핸드폰 번호 입니다. 리턴 값은 성공여부 입니다", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response"),
							@ApiResponse(code = 400, message = "Request error", response = Error.class),
							@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@PostMapping(value = "/mobile/{mobile}/",  produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Object> add(HttpServletResponse response,
			@ApiParam(value = "모바일 번호", required = true) @PathVariable("mobile") String mobile,
			SessionStatus sessionStatus

	) {
		
		try {

			LOGGER.debug("mobile : " + mobile);
			Document user = userTemplate.findByMobile(mobile) ;

			if( user != null ) {
				throw new Exception(message.getMessage("error.mobile.already.used", mobile));
			}

			String authcode = null ;
			if( (authcode=randomSend(mobile)) != null ) {

				LOGGER.debug("mobile, authcode : " + mobile + "," + authcode) ;
				return new ResponseEntity<Object>("{\"success\":true}", HttpStatus.OK);
			}
			else {
				throw new Exception(message.getMessage("error.sms.send"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}

	@ApiOperation(value = "", notes = "Check `mobile` & Send authcode **mobile** 은 핸드폰 번호 입니다. 리턴 값은 성공여부 입니다", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response"),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@GetMapping(value = "/mobile/{mobile}/",  produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Object> save(HttpServletResponse response,
							   @ApiParam(value = "모바일 번호", required = true) @PathVariable("mobile") String mobile,
							   SessionStatus sessionStatus

	) {

		try {

			LOGGER.debug("mobile : " + mobile);
			User user = userRepository.findByMobile(mobile) ;

			if( user == null ) {
				throw new Exception(message.getMessage("error.mobile.is.not.in.user", mobile));
		}

			String authcode = null ;
			if( (authcode=randomSend(mobile)) != null ) {

				LOGGER.debug("mobile, authcode : " + mobile + "," + authcode) ;
				return new ResponseEntity<Object>("{\"success\":true}", HttpStatus.OK);
			}
			else {
				throw new Exception(message.getMessage("error.sms.send"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@ApiOperation(value = "", notes = "confirm `Confirm Number with User's input` **mc** 는  모바일인증확인 오브젝트이다. 리턴 값은 성공/실패 여부 판단 값이다. ", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response"),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@PostMapping(value = "/mobile/confirm", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Object> mobileConfirm(HttpServletRequest request, HttpServletResponse response,
										 @ApiParam(required = true) @RequestBody String mcStr,
										 SessionStatus sessionStatus

	) {

		try {

			Document mc = Document.parse(mcStr) ;
			Document mc2 = mobileConfirmTemplate.findByOne(mc.getString("_id")) ;

			if( mc2 == null ){
				LOGGER.error("cannot find mobileConfirm data : " +  mc.getString("_id")) ;
			}

			if( mc2 != null && mc.getString("authcode").equals(mc2.getString("authcode")) &&
					(new Date().getTime() - mc2.getDate("updtime").getTime()) < MOBILE_CONFIRM_TIMEOUT ) {

				return new ResponseEntity<Object>("{\"success\":true}", HttpStatus.OK);
			}
			else {
				throw new Exception(message.getMessage("error.sms.confirm")) ;
			}
		}
		catch(Exception e) {
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

