package com.youngplussoft.modio.jpa.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBRef;
import com.youngplussoft.common.KeyGenerator;
import com.youngplussoft.common.Message;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.youngplussoft.modio.common.FileUploadForm;


import com.youngplussoft.modio.jpa.entity.Goods;
import com.youngplussoft.modio.jpa.entity.GoodsCategory;
import com.youngplussoft.modio.jpa.entity.Image;
import com.youngplussoft.modio.jpa.entity.Store;
import com.youngplussoft.jpa.controller.BaseController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class GoodsApiController extends ApiBaseController {




	@ApiOperation(value = "", notes = "get `Goods` **goods** 는 상품 정보 값이다. 리턴 값은 goods List 이다. ", response = Store.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/goods/{storeId}/", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.GET)
	ResponseEntity<Object> getGoods(HttpServletResponse response,
									@ApiParam(value = "상점 ID", required = true) @PathVariable String storeId,
									SessionStatus sessionStatus

	) {

		try {

			if( storeId == null ) {
				throw new Exception(message.getMessage("error.goods.store.not.available")) ;
			}

			Query query = new Query();
			query.addCriteria(Criteria.where("storeId").is(storeId));

			List<Goods> goodsList = goodsRepository.findByStoreId(storeId) ;
			if( goodsList == null || goodsList.size() == 0 ){
				throw new Exception(message.getMessage("error.goods.store.not.available")) ;
			}

			JSONObject json = new JSONObject() ;
			Gson gson = new Gson() ;

			return new ResponseEntity<Object>(gson.toJson(goodsList), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "", notes = "add `Goods` **goods** 는 상품 정보 값이다. 리턴 값은 성공/실패 여부 판단 값이다. ", response = Store.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
							@ApiResponse(code = 400, message = "Request error", response = Error.class),
							@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/goods/add", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.POST)
	ResponseEntity<Object> addGoods(HttpServletResponse response,
			@ApiParam(value = "상품 정보", required = true) @RequestBody String goodsStr,
			@ModelAttribute("uploadForm") FileUploadForm uploadForm, 	
			SessionStatus sessionStatus

	) {
		
		try {

			LOGGER.debug("addGoods : " + goodsStr); ;

			Document goods = Document.parse(goodsStr) ;
			LOGGER.debug("goods : " + goods.toJson()) ;

			String storeId = goods.getString("storeId") ;
			LOGGER.debug("storeId : " + storeId) ;

			if( storeId == null ) {
				throw new Exception(message.getMessage("error.goods.store.not.available")) ;
			}
			
			Document store = mongoTemplate.findById(new ObjectId(storeId), Document.class, "store") ;
			if( store == null ){
				throw new Exception(message.getMessage("error.goods.store.not.available")) ;
			}

			ObjectId categoryId = (ObjectId)((Document)goods.get("category")).get("$id");
			LOGGER.debug("categoryId : " + categoryId.toString()) ;
			Document category = mongoTemplate.findById(categoryId, Document.class, "goodsCategory") ;
			
			if( category == null ) {
				throw new Exception(message.getMessage("error.goods.category.not.available")) ;
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
						mongoTemplate.insert(image, "image");
						if (image == null) {
							throw new Exception(message.getMessage("error.image.insert"));
						}

						goods.put("image", imgId.toString());
						break;
					} else {
						throw new Exception(message.getMessage("error.image.unknown.ext"));
					}
				}
			}

			goods.put("_id", new ObjectId()) ;
			mongoTemplate.insert(goods, "goods") ;
			LOGGER.debug("addGoods : id : " + goods.get("_id")) ;
			if( goods == null ) {
				throw new Exception(message.getMessage("error.goods.insert")) ;
			}
	    	return new ResponseEntity<Object>(goods.toJson(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "", notes = "update `Goods` **goods** 는 상품 정보 값이다. 리턴 값은 성공/실패 여부 판단 값이다. ", response = Goods.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Goods.class),
							@ApiResponse(code = 400, message = "Request error", response = Error.class),
							@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/goods/update", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.PUT)
	ResponseEntity<Object> updateGoods(HttpServletResponse response,
			@ApiParam(value = "상품 정보", required = true) @RequestBody String goodsStr,
			@ModelAttribute("uploadForm") FileUploadForm uploadForm, 	
			SessionStatus sessionStatus

	) {
		
		try {

			Document goods = Document.parse(goodsStr) ;
			if( goods.get("_id") == null ){
				throw new Exception(message.getMessage("error.goods.update")) ;
			}

			if( goods.getString("storeId") == null ) {
				throw new Exception(message.getMessage("error.goods.store.not.available")) ;
			}


			Document store = storeTemplate.findById(new ObjectId(goods.getString("storeId") )) ;
			if( store == null ){
				throw new Exception(message.getMessage("error.goods.store.not.available")) ;
			}

			ObjectId categoryId = (ObjectId)((Document)goods.get("category")).get("$id") ;
			Document category = goodsCategoryTemplate.findById(categoryId) ;
			
			if( category == null ) {
				throw new Exception(message.getMessage("error.goods.category.not.available")) ;
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

						goods.put("image", imgId.toString());
					} else {
						throw new Exception(message.getMessage("error.image.unknown.ext"));
					}

					break; // 이미지는 하나만 등록
				}
			}
			try {
				goodsTemplate.save(goods);
			}
			catch(Exception ee){
				throw new Exception(message.getMessage("error.goods.update")) ;
			}
	    	return new ResponseEntity<Object>(goods.toJson(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
