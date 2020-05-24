package com.youngplussoft.modio.jpa.controller;

import com.google.gson.Gson;
import com.youngplussoft.common.KeyGenerator;
import com.youngplussoft.common.Message;
import com.youngplussoft.common.Sms;
import com.youngplussoft.modio.common.FileUploadForm;
import com.youngplussoft.modio.jpa.entity.PointCharge;
import com.youngplussoft.modio.jpa.entity.Store;
import com.youngplussoft.modio.jpa.entity.User;
import com.youngplussoft.modio.pay.Toss;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PointApiController extends ApiBaseController {




	@ApiOperation(value = "", notes = "get `PointCharge` **point** 는 상품 구매 포인트 값이다. 리턴 값은 PointCharge 이다. ", response = PointCharge.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/point/{orderNo}/", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.GET)
	ResponseEntity<Object> getPointCharge(HttpServletResponse response,
									@ApiParam(value = "상점 ID", required = true) @PathVariable String orderNo,
									SessionStatus sessionStatus
	) {

		try {

			if( orderNo == null ) {
				throw new Exception(message.getMessage("error.point.charge.oderno.empty")) ;
			}

			Query query = new Query();
			query.addCriteria(Criteria.where("orderNo").is(orderNo));

			PointCharge pointCharge = pointChargeRepository.findByOrderNo(orderNo) ;
			if( pointCharge == null  ){
				throw new Exception(message.getMessage("error.point.charge.not.available")) ;
			}

			JSONObject json = new JSONObject() ;
			Gson gson = new Gson() ;

			return new ResponseEntity<Object>(gson.toJson(pointCharge), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@ApiOperation(value = "", notes = "add `PointCharge` **point** 는 상품 정보 값이다. 리턴 값은 성공/실패 여부 판단 값이다. ", response = PointCharge.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = "/modio/paygate/toss/result/{orderNo}/", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.POST)
	ResponseEntity<Object> tossResult(HttpServletRequest request, HttpServletResponse response,
									      @ApiParam(value = "포인트 충전 주문정보", required = true) @PathVariable String orderNo,
										  @ApiParam(value = "결제 상태", required = true) @RequestParam String status,
									  	@ApiParam(value = "결제 토큰", required = true) @RequestParam String payToken,
									  	@ApiParam(value = "결제 시각", required = true) @RequestParam String paidTs,
									  	@ApiParam(value = "메타 정보", required = true) @RequestParam String metadata,
										  SessionStatus sessionStatus

	) {

		try {

			LOGGER.debug("toss/result : orderNo : " + orderNo ) ;
			LOGGER.debug("toss/result : status : " + status) ;
			LOGGER.debug("toss/result : payToken : " + payToken) ;
			LOGGER.debug("toss/result : paidTs : " + paidTs) ;
			LOGGER.debug("toss/result : metadata : " + metadata) ;
			PointCharge pointCharge = pointChargeRepository.findByOrderNo(orderNo);
			pointCharge.setStatus(status);
			if (status.equals("PAY_SUCCESS")) {
				User user = userRepository.findById(new ObjectId(pointCharge.getUserId()));
				user.setPoint(user.getPoint() + pointCharge.getAmount());
				userRepository.save(user);

				User tossUser = userRepository.findById(new ObjectId(pointCharge.getUserId())) ;
				Object objs[] = { "PAY_SUCCESS", ((Object)pointCharge.getAmount()) } ;
				Sms.send(tossUser.getMobile(), message.getMessage("point.toss.pay.result", objs)) ;
			} else if (status.equals("REFUND_SUCCESS")) {
				User user = userRepository.findById(new ObjectId(pointCharge.getUserId()));
				user.setPoint(user.getPoint() - pointCharge.getAmount());
				userRepository.save(user);

				User tossUser = userRepository.findById(new ObjectId(pointCharge.getUserId())) ;
				Object objs[] = { "REFUND_SUCCESS", ((Object)pointCharge.getAmount()) } ;
				Sms.send(tossUser.getMobile(), message.getMessage("point.toss.pay.result", objs)) ;

			} else if (status.equals("PAY_CANCEL")) {
				User user = userRepository.findById(new ObjectId(pointCharge.getUserId()));
				user.setPoint(user.getPoint() - pointCharge.getAmount());
				userRepository.save(user);

				User tossUser = userRepository.findById(new ObjectId(pointCharge.getUserId())) ;
				Object objs[] = { "PAY_CANCEL", ((Object)pointCharge.getAmount()) } ;
				Sms.send(tossUser.getMobile(), message.getMessage("point.toss.pay.result", objs)) ;
			}
			pointChargeRepository.save(pointCharge);
			return new ResponseEntity<Object>("{\"status\" : \"Success\"}", HttpStatus.OK);
		}

		catch(Exception e){
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@ApiOperation(value = "", notes = "add `PointCharge` **point** 는 상품 정보 값이다. 리턴 값은 성공/실패 여부 판단 값이다. ", response = PointCharge.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
							@ApiResponse(code = 400, message = "Request error", response = Error.class),
							@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/point/add", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.POST)
	ResponseEntity<Object> addPointCharge(HttpServletRequest request, HttpServletResponse response,
										  @ApiParam(value = "포인트 충전 정보", required = true) @RequestBody String pointChargeStr,
										  SessionStatus sessionStatus

	) {
		
		try {

			LOGGER.debug("addPointCharge : " + pointChargeStr); ;

			Document pointCharge = Document.parse(pointChargeStr) ;

			pointCharge.put("orderNo", KeyGenerator.generateOrderNo()) ;
			LOGGER.debug("orderNo : " + pointCharge.getString("orderNo")) ;

			pointCharge.put("userId", this.getSessionUser(request).getId()) ;
			pointCharge.put("autoExecute", true) ;
			pointCharge.put("cashReceipt", true) ;
			if( pointCharge.getString("paygate") != null  &&
				pointCharge.getString("paygate").equals(Toss.name) ){


				JSONObject res = Toss.toss(pointCharge, message, this.getSessionUser(request)) ;
				try {
					pointCharge.put("amount", new Double(pointCharge.getInteger("amount"))) ;
					pointCharge.put("_id", new ObjectId()) ;
					pointChargeTemplate.insert(pointCharge);
					return new ResponseEntity<Object>(res.toString(), HttpStatus.OK);
				}
				catch(Exception e){
					LOGGER.error(Message.exceptionToString(e)) ;
					throw new Exception(message.getMessage("error.point.charge.insert")) ;
				}
			}
			else {
				throw new Exception(message.getMessage("error.paygate.unknown")) ;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	
	@ApiOperation(value = "", notes = "update `PointCharge` **point** 는 상품 정보 값이다. 리턴 값은 성공/실패 여부 판단 값이다. ", response = PointCharge.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = PointCharge.class),
							@ApiResponse(code = 400, message = "Request error", response = Error.class),
							@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/point/update", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.PUT)
	ResponseEntity<Object> updatePointCharge(HttpServletResponse response,
			@ApiParam(value = "상품 정보", required = true) @RequestBody String pointStr,
			@ModelAttribute("uploadForm") FileUploadForm uploadForm, 	
			SessionStatus sessionStatus

	) {
		
		try {

			Document point = Document.parse(pointStr) ;
			if( point.get("_id") == null ){
				throw new Exception(message.getMessage("error.point.update")) ;
			}

			if( point.getString("storeId") == null ) {
				throw new Exception(message.getMessage("error.point.store.not.available")) ;
			}


			Document store = storeTemplate.findById(new ObjectId(point.getString("storeId") )) ;
			if( store == null ){
				throw new Exception(message.getMessage("error.point.store.not.available")) ;
			}

			ObjectId categoryId = (ObjectId)((Document)point.get("category")).get("$id") ;
			Document category = pointChargeTemplate.findById(categoryId) ;
			
			if( category == null ) {
				throw new Exception(message.getMessage("error.point.category.not.available")) ;
			}
		
			DateFormat df = new SimpleDateFormat("yyyyMM");
			Date now = new Date() ;
	        String yyyymm = df.format(now);
			
	        //임시 원본 파일저장부분에 해당월명의 폴더가 없을 경우 생성해줌.
	        File yyyymmDir = new File(imageLocation+"/"+ yyyymm) ;
			
			if( yyyymmDir.exists() && yyyymmDir.isFile() ) {
				yyyymmDir.delete() ;
				yyyymmDir.mkdir();
		    }
		    else if( !yyyymmDir.exists() ) {
		    	yyyymmDir.mkdir();
		    }
			
			List<MultipartFile> files = null ;

			if( uploadForm != null )
				files = uploadForm.getFiles();
			
			if( files != null ) {
				for (MultipartFile file : files) {

					//입력된 파일 명을 추출
					String filename = file.getOriginalFilename();

					//파일 이름에서 뒤에 3자리를 읽어온다(확장자 추출)
					String ext = filename.substring(filename.lastIndexOf('.'), filename.length()).toLowerCase();
					String fname = filename.substring(0, filename.lastIndexOf('.'));
					String uuid = null;
					String fpath = null;

					if (ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".gif") || ext.equals(".png")) {

						Document image = new Document();
						image.put("share", false);
						image.put("fname", fname);
						uuid = KeyGenerator.generateKey();
						image.put("uuid", uuid);
						image.put("ext", ext);
						image.put("yyyymm", yyyymm);
						image.put("regtime", now);
						image.put("updtime", now);


						//파일 업로드
						fpath = (imageLocation + "/" + yyyymm + "/" + uuid);
						File dest = new File(fpath + ext);
						file.transferTo(dest);        //file upload

						ObjectId imgId = new ObjectId() ;
						image.put("_id", imgId) ;

						try {
							imageTemplate.insert(image);
						}
						catch(Exception ee){
							throw new Exception(message.getMessage("error.image.insert"));
						}

						point.put("image", imgId.toString());
					} else {
						throw new Exception(message.getMessage("error.image.unknown.ext"));
					}

					break; // 이미지는 하나만 등록
				}
			}
			try {
				pointTemplate.save(point);
			}
			catch(Exception ee){
				throw new Exception(message.getMessage("error.point.update")) ;
			}
	    	return new ResponseEntity<Object>(point.toJson(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	*/
}
