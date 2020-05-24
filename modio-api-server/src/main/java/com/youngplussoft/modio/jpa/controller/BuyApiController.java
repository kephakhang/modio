package com.youngplussoft.modio.jpa.controller;

import com.google.gson.Gson;
import com.mongodb.DBRef;
import com.youngplussoft.common.KeyGenerator;
import com.youngplussoft.common.Message;
import com.youngplussoft.common.Sms;
import com.youngplussoft.modio.common.FileUploadForm;
import com.youngplussoft.modio.jpa.entity.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class BuyApiController extends ApiBaseController {

	final String buyMessage = "(주)MoDiO 구매정보 입니다: 구매 번호(#ORDER_NO#) : 구매 상태(#STATUS#) : 구매 금액(#AMOUNT#)" ;


	@ApiOperation(value = "", notes = "buy `Products` **products** 는 구매 정보 값이다. 리턴 값은 Buy 결과 값이다 ", response = Buy.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/buy", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.GET)
	ResponseEntity<Object> addBuy(HttpServletRequest request,
									HttpServletResponse response,
									@RequestBody String productsStr,
									SessionStatus sessionStatus

	) {

		try {

			if( productsStr == null ) {
				throw new Exception(message.getMessage("error.buy.info.not.available")) ;
			}

			Document doc = Document.parse(productsStr) ;
			JSONArray arr = new JSONArray(doc.getString("cartItems")) ;
			Double totalPrice = doc.getDouble("totalPrice") ;
			List<Document> prodList = new ArrayList<Document>() ;

			String smsMsg = buyMessage.replaceAll("#ORDER_NO#", doc.getString("orderNo")) ;
			smsMsg = smsMsg.replaceAll("#STATUS#", "B") ;
			smsMsg = smsMsg.replaceAll("#AMOUNT#", ""+totalPrice) ;
			String storeId = null ;

			for(int i=0 ; i<arr.length() ; i++){
				Document prod = Document.parse(arr.getJSONArray(i).toString()) ;
				JSONObject goods = new JSONObject(prod.getString("goods")) ;
				if( storeId == null ){
					storeId = goods.getString("storeId") ;
				}
				DBRef ref = new DBRef("goods",	prod.getObjectId("_id")) ;
				smsMsg += "\n" + goods.getJSONObject("name").getString("kr") + ":" + prod.getInteger("count") ;
				prod.remove("goods") ;
				prod.put("goods", ref) ;
				prodList.add(prod) ;
			}

			doc.put("products", prodList) ;
			doc.put("_id", new ObjectId()) ;
			doc.put("orderNo", KeyGenerator.generateOrderNo()) ;
			doc.put("buytime", new Date()) ;
			doc.put("paytime", new Date()) ;
			doc.put("progress", "B") ;
			doc.put("storeId", storeId) ;
			doc.put("userId", this.getSessionUser(request).getId().toString()) ;
			LOGGER.debug("add Buy : " + doc.toJson()) ;

			try {
				buyTemplate.insert(doc);
				sms.send(this.getSessionUser(request).getMobile(), smsMsg) ;

				Store store = storeRepository.findById(new ObjectId(storeId)) ;
				String storeUserId = store.getUserId() ;
				User storeUser = userRepository.findById(new ObjectId(storeUserId)) ;
				sms.send(storeUser.getMobile(), smsMsg.replaceAll("구매", "주문")) ;

			}
			catch(Exception ee){
				LOGGER.error(Message.exceptionToString(ee)) ;
				throw new Exception(message.getMessage("error.buy.info.insert")) ;
			}

			return new ResponseEntity<Object>(doc.toJson(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
