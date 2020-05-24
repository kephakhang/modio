package com.youngplussoft.modio.jpa.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mongodb.DBRef;
import com.youngplussoft.common.KeyGenerator;
import com.youngplussoft.common.Message;
import com.youngplussoft.modio.jpa.entity.*;
import com.youngplussoft.jpa.entity.Error;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.youngplussoft.modio.common.FileUploadForm;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Controller
public class StoreApiController extends ApiBaseController {



	public void deleteImageFile(Document image){

		String fpath = (imageLocation+"/"+ image.getString("yyyymm") + "/" + image.getString("uuid"));
		File dest = new File(fpath + image.getString("ext")) ;
		try {
			dest.delete() ;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@ApiOperation(value = "", notes = "add Store **store** 는 상점 정보 값이다. 리턴 값은 성공/실패 여부 판단 값이다. ", response = Store.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
							@ApiResponse(code = 400, message = "Request error", response = Error.class),
							@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/store/add", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.POST)
	ResponseEntity<Object> addStore(HttpServletRequest request, HttpServletResponse response,
			@ApiParam(value = "상점 정보", required = true) @RequestBody String storeStr,
			@ModelAttribute("uploadForm") FileUploadForm uploadForm, 	
			SessionStatus sessionStatus

	) {
		
		try {


			LOGGER.debug("addStore : " + storeStr) ;

			Document store = Document.parse(storeStr) ;
			
			if(store.getString("userId") == null || ! store.getString("userId").equals(this.getSessionUser(request).getId()) ) {
				throw new Exception(message.getMessage("error.store.user.not.available")) ;
			}

			ObjectId categoryId = (ObjectId)((Document)store.get("category")).get("$id") ;
			LOGGER.debug("categoryId : " + categoryId) ;
			Document category = mongoTemplate.findById(categoryId, Document.class, "storeCategory") ;
			
			if( category == null ) {
				throw new Exception(message.getMessage("error.store.category.not.available")) ;
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

			if( store.get("images") == null ) {
				store.put("images", new ArrayList<String>()) ;
			}

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
						((List<String>)store.get("images")).add(imgId.toString());
					} else {
						throw new Exception(message.getMessage("error.image.unknown.ext"));
					}
				}
			}

			store.put("_id", new ObjectId()) ;
			try {
				storeTemplate.insert(store);
			}
			catch(Exception ee){
				throw new Exception(message.getMessage("error.store.insert"));
			}
			LOGGER.debug("addStore : new id : " + store.get("_id")) ;
			return new ResponseEntity<Object>(store.toJson(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "", notes = "update Store **store** 는 상점 정보 값이다. 리턴 값은 성공/실패 여부 판단 값이다. ", response = Store.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
							@ApiResponse(code = 400, message = "Request error", response = Error.class),
							@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/store/update", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.PUT)
	ResponseEntity<Object> updateStore(HttpServletRequest request, HttpServletResponse response,
			@ApiParam(value = "상점 정보", required = true) @RequestBody String storeStr,
			@ModelAttribute("uploadForm") FileUploadForm uploadForm, 	
			SessionStatus sessionStatus

	) {
		
		try {

			Document store = Document.parse(storeStr) ;
			if(store.getString("userId") == null || ! store.getString("userId").equals(this.getSessionUser(request).getId()) ) {
				throw new Exception(message.getMessage("error.store.user.not.available")) ;
			}

			ObjectId categoryId = (ObjectId)((DBRef)store.get("category")).getId() ;
			Document category = storeCategoryTemplate.findById(categoryId) ;

			if( category == null ) {
				throw new Exception(message.getMessage("error.store.category.not.available")) ;
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
				uploadForm.getFiles();


			Document _store = storeTemplate.findById((ObjectId)store.get("_id")) ;

			if( _store == null ) {
				throw new Exception(message.getMessage("error.store.not.exist")) ;
			}

			store.put("images", _store.get("images")) ;

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


						//파일 업로드
						fpath = (imageLocation + "/" + yyyymm + "/" + uuid);
						File dest = new File(fpath + ext);
						file.transferTo(dest);        //file upload

						boolean found = false;
						for (int i = 0; i < ((List<String>)store.get("image")).size(); i++) {
							String imgId = (String)((List<String>)store.get("image")).get(i);
							Document e = imageRepository.findById(new ObjectId(imgId));
							if (e.getString("fname").equals(image.getString("fname")) && e.getString("uuid").equals("")) {
								this.deleteImageFile(e);
								image.put("_id", e.getObjectId("_id"));
								try {
									imageTemplate.save(image);
								}
								catch(Exception ee){
									throw new Exception(message.getMessage("error.image.insert"));
								}
								found = true;
								break;
							}
						}

						if (!found) {
							image.put("_id", new ObjectId()) ;
							try {
								imageTemplate.insert(image);
							}
							catch(Exception ee){
								throw new Exception(message.getMessage("error.image.insert"));
							}
							((List<String>)store.get("images")).add(image.getObjectId("_id").toString());
						}
					} else {
						throw new Exception(message.getMessage("error.image.unknown.ext"));
					}
				}
			}

			try {
				storeTemplate.save(store);
			}
			catch(Exception ee){
				throw new Exception(message.getMessage("error.store.update")) ;
			}

			Gson gson = new Gson() ;
	    	return new ResponseEntity<Object>(gson.toJson(store), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "", notes = "around list Store **List<store>** 는 상점 정보 리스트 값이다 리턴 값은 성공/실패 여부 판단 값이다. ", response = Store.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/store/around/{pageno}/", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.POST)
	ResponseEntity<Object> aroundStore(HttpServletResponse response,
									 @ApiParam(value = "페이지 번호", required = true) @PathVariable("pageno") Integer pageno,
									@ApiParam(value = "사용자 위치(GPS)", required = true) @RequestBody String position,
									SessionStatus sessionStatus

	) {

		try {


			JSONObject jsonPos = new JSONObject(position) ;
			Point pos = new Point(jsonPos.getDouble("x"), jsonPos.getDouble("y")) ;


			LOGGER.debug("store/around/" + pageno) ;
			LOGGER.debug("position : "+ pos) ;

			Distance dist = new Distance(100, Metrics.KILOMETERS) ;

			Page<Store> page = null ;

			if( pos.getX() < Double.MIN_VALUE && pos.getY() < Double.MIN_VALUE ){
				PageRequest pageReq = PageRequest.of(pageno, 10, new Sort(Sort.Direction.DESC, "updtime"));
				page = storeRepository.findAll(pageReq) ;
			}
			else {
				PageRequest pageReq = PageRequest.of(pageno, 10, new Sort(Sort.Direction.DESC, "updtime"));
				page = storeRepository.findByPositionNear(pos, dist, pageReq);
				if( page == null ) // 주위에 찾는 상점이 없을 때...
					page = storeRepository.findAll(pageReq) ;
			}

			if( page == null ) {
				JSONObject json = new JSONObject() ;
				json.put("aroundList", new JSONArray() )  ;
				LOGGER.debug("json : " + json.toString()) ;
				return new ResponseEntity<Object>(json.toString(), HttpStatus.OK) ;
			}
			else {
				JSONObject json = new JSONObject() ;
				JSONArray arr = new JSONArray() ;
				Gson gson = new Gson() ;
				for(Store store : page.getContent()){
					arr.put(new JSONObject(gson.toJson(store))) ;
				}
				json.put("aroundList", arr)  ;
				LOGGER.debug("json : " + json.toString()) ;
				return new ResponseEntity<Object>(json.toString(), HttpStatus.OK) ;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "", notes = "my list Store **List<store>** 는 상점 정보 리스트 값이다 리턴 값은 성공/실패 여부 판단 값이다. ", response = Store.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/store/user/{pageno}/", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.POST)
	ResponseEntity<Object> myStore(HttpServletRequest request, HttpServletResponse response,
									   @ApiParam(value = "페이지 번호", required = true) @PathVariable("pageno") Integer pageno,
									   @ApiParam(value = "사용자 위치(GPS)", required = true) @RequestBody String position,
									   SessionStatus sessionStatus

	) {

		try {

			LOGGER.debug("store/user/" + pageno) ;
			LOGGER.debug("position : " + position) ;


			JSONObject jsonPos = new JSONObject(position) ;
			Point pos = new Point(jsonPos.getDouble("x"), jsonPos.getDouble("y")) ;
			Distance dist = new Distance(1000, Metrics.KILOMETERS) ;

			Page<Store> page1 = null ;
			if( pos.getX() < Double.MIN_VALUE && pos.getY() < Double.MIN_VALUE ){
				PageRequest pageReq = PageRequest.of(pageno, 10, new Sort(Sort.Direction.DESC, "updtime"));
				page1 = storeRepository.findAll(pageReq) ;
			}
			else {
				PageRequest pageReq = PageRequest.of(pageno, 10, new Sort(Sort.Direction.DESC, "updtime"));
				page1 = storeRepository.findByPositionNear(pos, dist, pageReq);
				if( page1 == null ) // 주위에 찾는 상점이 없을 때...
					page1 = storeRepository.findAll(pageReq) ;
			}

			PageRequest pageReq = PageRequest.of(pageno, 10, new Sort(Sort.Direction.DESC, "updtime"));
			User user = this.getSessionUser(request)  ;
			LOGGER.debug("sessionUser : " + user.getId() + ":" + user.getEmail() + ":" + user.getMobile());

			Page<StoreBookmark> page2 = storeBookmarkRepository.findByUserId(user.getId(), pageReq);

			pageReq = PageRequest.of(pageno, 10, new Sort(Sort.Direction.DESC, "updtime"));
			Page<StoreFrequent> page3 = storeFrequentRepository.findByUserId(user.getId(), pageReq);


			JSONObject json = new JSONObject() ;
			if( page1 == null ) {

				json.put("aroundList", new JSONArray() )  ;
			}
			else {
				JSONArray arr = new JSONArray() ;
				Gson gson = new Gson() ;
				for(Store store : page1.getContent()){
					arr.put(new JSONObject(gson.toJson(store))) ;
				}
				json.put("aroundList", arr)  ;
			}

			if( page2 == null ) {

				json.put("bookmarkList", new JSONArray() )  ;
			}
			else {
				JSONArray arr = new JSONArray() ;
				Gson gson = new Gson() ;
				for(StoreBookmark h : page2.getContent()){
					Store store = (Store)h.getStore() ;
					if( store == null ){
						continue ;
					}
					LOGGER.debug("Store : " + gson.toJson(store)) ;
					System.out.println("Store : " + gson.toJson(store)) ;
					arr.put(new JSONObject(gson.toJson(store))) ;
				}
				json.put("bookmarkList", arr)  ;
			}

			if( page3 == null ) {

				json.put("frequentList", new JSONArray() )  ;
			}
			else {
				JSONArray arr = new JSONArray() ;
				Gson gson = new Gson() ;
				for(StoreFrequent h : page3.getContent()){
					Store store = h.getStore() ;
					if( store == null ){
						continue ;
					}
					LOGGER.debug("Store : " + gson.toJson(store)) ;
					System.out.println("Store : " + gson.toJson(store)) ;
					arr.put(new JSONObject(gson.toJson(store))) ;
				}
				json.put("frequentList", arr)  ;
			}

			LOGGER.debug("json : " + json.toString()) ;
			return new ResponseEntity<Object>(json.toString(), HttpStatus.OK) ;
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@ApiOperation(value = "", notes = "my list Store **List<store>** 는 상점 정보 리스트 값이다 리턴 값은 성공/실패 여부 판단 값이다. ", response = Store.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/store/bookmark/{pageno}/", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.POST)
	ResponseEntity<Object> myBookmarkStore(HttpServletRequest request, HttpServletResponse response,
								   @ApiParam(value = "페이지 번호", required = true) @PathVariable("pageno") Integer pageno,
								   SessionStatus sessionStatus

	) {

		try {

			LOGGER.debug("store/bookmark/" + pageno + "/") ;
			User user = this.getSessionUser(request) ;
			PageRequest pageReq = PageRequest.of(pageno, 10, new Sort(Sort.Direction.DESC, "updtime"));
			Page<StoreBookmark> page2 = storeBookmarkRepository.findByUserId(user.getId(), pageReq);

			JSONObject json = new JSONObject() ;
			if( page2 == null ) {

				json.put("bookmarkList", new JSONArray() )  ;
			}
			else {
				JSONArray arr = new JSONArray() ;
				Gson gson = new Gson() ;
				for(StoreBookmark h : page2.getContent()){
					Store store = (Store)h.getStore() ;
					if( store == null ){
						continue ;
					}
					LOGGER.debug("Store : " + gson.toJson(store)) ;
					System.out.println("Store : " + gson.toJson(store)) ;
					arr.put(new JSONObject(gson.toJson(store))) ;
				}
				json.put("bookmarkList", arr)  ;
			}
			LOGGER.debug("json : " + json.toString()) ;
			return new ResponseEntity<Object>(json.toString(), HttpStatus.OK) ;
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@ApiOperation(value = "", notes = "my list Store **List<store>** 는 상점 정보 리스트 값이다 리턴 값은 성공/실패 여부 판단 값이다. ", response = Store.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/store/frequent/{pageno}/", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.POST)
	ResponseEntity<Object> myFrequentStore(HttpServletRequest request, HttpServletResponse response,
								   @ApiParam(value = "페이지 번호", required = true) @PathVariable("pageno") Integer pageno,
								   SessionStatus sessionStatus

	) {

		try {

			LOGGER.debug("store/frequent/" + pageno + "/") ;
			User user = this.getSessionUser(request) ;
			PageRequest pageReq = PageRequest.of(pageno, 10, new Sort(Sort.Direction.DESC, "updtime"));
			Page<StoreFrequent> page3 = storeFrequentRepository.findByUserId(user.getId(), pageReq);

			JSONObject json = new JSONObject() ;

			if( page3 == null ) {

				json.put("frequentList", new JSONArray() )  ;
			}
			else {
				JSONArray arr = new JSONArray() ;
				Gson gson = new Gson() ;
				for(StoreFrequent h : page3.getContent()){
					Store store = h.getStore() ;
					if( store == null ){
						continue ;
					}
					LOGGER.debug("Store : " + gson.toJson(store)) ;
					System.out.println("Store : " + gson.toJson(store)) ;
					arr.put(new JSONObject(gson.toJson(store))) ;
				}
				json.put("frequentList", arr)  ;
			}

			LOGGER.debug("json : " + json.toString()) ;
			return new ResponseEntity<Object>(json.toString(), HttpStatus.OK) ;
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "", notes = "search Store **List<store>** 는 상점 정보 리스트 값이다 리턴 값은 성공/실패 여부 판단 값이다. ", response = Store.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/store/search/{pageno}/", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.GET)
	ResponseEntity<Object> myFrequentStore(HttpServletRequest request, HttpServletResponse response,
										   @ApiParam(value = "언어", required = true) @RequestParam("lang") String lang,
										   @ApiParam(value = "검색어", required = true) @RequestParam("term") String term,
										   @ApiParam(value = "페이지 번호", required = true) @PathVariable("pageno") Integer pageno,
										   SessionStatus sessionStatus

	) {

		try {

			LOGGER.debug("store/search/" + pageno + "/?lang=" + lang + "&term=" + term) ;
			User user = this.getSessionUser(request) ;
			PageRequest pageReq = PageRequest.of(pageno, 10);
			Page<Store> page = storeTemplate.findByName(lang, term, pageReq);

			JSONObject json = new JSONObject() ;

			if( page == null ) {

				json.put("searchList", new JSONArray() )  ;
			}
			else {
				JSONArray arr = new JSONArray() ;
				Gson gson = new Gson() ;
				for(Store store : page.getContent()){
					LOGGER.debug("Store : " + gson.toJson(store)) ;
					System.out.println("Store : " + gson.toJson(store)) ;
					arr.put(new JSONObject(gson.toJson(store))) ;
				}
				json.put("searchList", arr)  ;
			}

			LOGGER.debug("json : " + json.toString()) ;
			return new ResponseEntity<Object>(json.toString(), HttpStatus.OK) ;
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "", notes = "search Store **List<store>** 는 상점 정보 리스트 값이다 리턴 값은 성공/실패 여부 판단 값이다. ", response = Store.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Responseful response", response = Store.class),
			@ApiResponse(code = 400, message = "Request error", response = Error.class),
			@ApiResponse(code = 500, message = "Internal error", response = Error.class) })
	@RequestMapping(value = apiBasePath+"/store/table/bookings", produces = { "application/json;charset=UTF-8", "text/html;charset=UTF-8" }, method = RequestMethod.GET)
	ResponseEntity<Object> getStoreTableBookings(HttpServletRequest request, HttpServletResponse response,
												 @ApiParam(value = "날짜", required = true) @RequestParam("storeId") String storeId,
										   @ApiParam(value = "날짜", required = true) @RequestParam("date") String dateStr,
										    SessionStatus sessionStatus

	) {

		try {

			LOGGER.debug("store/table/bookings") ;
			LOGGER.debug("storeId" + storeId) ;
			LOGGER.debug("date" + dateStr) ;

			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr) ;

			Document doc = tableBookingTemplate.findByStoreIdAndDate(storeId, date) ;

			JSONObject json = new JSONObject() ;

			//ToDo...

			LOGGER.debug("json : " + json.toString()) ;
			return new ResponseEntity<Object>(json.toString(), HttpStatus.OK) ;
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(Message.exceptionToString(e)) ;
			return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}




	public static void main(String[] args){
		System.out.println(new Distance(100, Metrics.KILOMETERS).getMetric()) ;
	}
}
