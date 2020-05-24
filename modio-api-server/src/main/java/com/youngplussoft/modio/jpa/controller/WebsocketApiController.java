package com.youngplussoft.modio.jpa.controller;

import com.google.gson.Gson;
import com.youngplussoft.common.KeyGenerator;
import com.youngplussoft.common.Message;
import com.youngplussoft.modio.jpa.entity.Login;
import com.youngplussoft.modio.jpa.entity.User;
import com.youngplussoft.modio.jpa.entity.Websocket;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Controller
public class WebsocketApiController extends ApiBaseController {


	protected static final Logger LOGGER = LoggerFactory.getLogger(WebsocketApiController.class);
	private final long MOBILE_CONFIRM_TIMEOUT = 600000 ; //10분


	@ApiOperation(value = "", notes = "Get `user` **websocket** 은 사용자 정보 값이다. 리턴 값은 사용자 정보 오브젝트 이다.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = User.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = "/websocket/{devId}/", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.GET)
	ResponseEntity<Object> find(HttpServletRequest request, HttpServletResponse response,
									   SessionStatus sessionStatus,
										@ApiParam(value = "장비 ID", required = true) @PathVariable String devId
	) {

		try {
			Document ws = websocketTemplate.findByDevId(devId) ;
			if( ws == null )
				throw new Exception(message.getMessage("error.websocket.not.exist")) ;
			return new ResponseEntity<Object>(ws.toJson(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "", notes = "Remove `websocket` **websocket** 은  websocket 정보 값이다. 리턴 값은 사용자 정보 오브젝트 이다.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = User.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = "/websocket/{devId}/", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.DELETE)
	ResponseEntity<Object> remove(HttpServletRequest request, HttpServletResponse response,
								SessionStatus sessionStatus,
								@ApiParam(value = "장비 ID", required = true) @PathVariable String devId
	) {

		try {
			Document ws = websocketTemplate.findByDevId(devId) ;
			if( ws == null )
				throw new Exception(message.getMessage("error.websocket.not.exist")) ;
			else
				websocketTemplate.remove(ws.getObjectId("_id"));
			return new ResponseEntity<Object>(ws.toJson(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "", notes = "add `Websocket` **websocket** 은  websocket 정보 값이다. 리턴 값은 성공/실패 여부 판단 값이다. ", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response"),
							@ApiResponse(code = 400, message = "Request error", response = Error.class),
							@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = "/websocket", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.POST)
	ResponseEntity<Object> save(HttpServletRequest request, HttpServletResponse response,
			@ApiParam(value = "멤버 정보", required = true) @RequestBody String websocketInfoStr,
			SessionStatus sessionStatus

	) {

		try {


			Document ws = Document.parse(websocketInfoStr) ;
			try {

				Document _ws = websocketTemplate.findByDevId(ws.getString("devId")) ;
				if( _ws != null ) {
					_ws.put("socketId", _ws.getString("socketId"));
				}
				else {
					_ws = new Document() ;
					_ws.put("devId", _ws.getString("devId"));
					_ws.put("socketId", _ws.getString("socketId"));
				}

				websocketTemplate.save(_ws) ;
			}
			catch(Exception ee) {
				ee.printStackTrace();
				throw new Exception(message.getMessage("error.websocket.save")) ;
			}

			return new ResponseEntity<Object>(ws.toJson(), HttpStatus.OK);
		}
		catch(Exception e) {
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}
}

