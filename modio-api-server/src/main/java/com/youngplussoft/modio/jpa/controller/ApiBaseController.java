package com.youngplussoft.modio.jpa.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.mongodb.DB;
import com.youngplussoft.common.Mail;
import com.youngplussoft.common.Message;
import com.youngplussoft.common.Sms;
import com.youngplussoft.jpa.controller.BaseController;
import com.youngplussoft.modio.common.WebsocketClient;
import com.youngplussoft.modio.jpa.repository.*;
import com.youngplussoft.modio.jpa.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.youngplussoft.modio.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

public class ApiBaseController extends BaseController {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(ApiBaseController.class);

	protected final String apiBasePath = "/api/v3" ;

	@Resource
	protected MongoTemplate mongoTemplate;

	@Resource
	protected BuyRepository buyRepository ;

	@Resource
	protected UserRepository userRepository ;

	@Resource
	protected WebsocketRepository websocketRepository ;


	@Resource
	protected PointChargeRepository pointChargeRepository ;


	@Resource
	protected StoreRepository storeRepository ;

	@Resource
	protected GoodsRepository goodsRepository ;

	@Resource
	protected ImageRepository imageRepository ;

	@Resource
	protected StoreCategoryRepository storeCategoryRepository ;

	@Resource
	protected GoodsCategoryRepository goodsCategoryRepository ;

	@Resource
	protected StoreFrequentRepository storeFrequentRepository ;

	@Resource
	protected StoreBookmarkRepository storeBookmarkRepository ;

	@Resource
	public MobileConfirmRepository mobileConfirmRepository ;

	@Resource
	public TableBookingRepository tableBookingRepository ;

	@Resource
	protected BuyTemplate buyTemplate ;

	@Resource
	protected UserTemplate userTemplate ;

	@Resource
	protected WebsocketTemplate websocketTemplate ;

	@Resource
	protected StoreTemplate storeTemplate ;

	@Resource
	protected GoodsTemplate goodsTemplate ;

	@Resource
	protected ImageTemplate imageTemplate ;

	@Resource
	protected PointChargeTemplate pointChargeTemplate ;

	@Resource
	protected StoreCategoryTemplate storeCategoryTemplate ;

	@Resource
	protected GoodsCategoryTemplate goodsCategoryTemplate ;

	@Resource
	protected StoreFrequentTemplate storeFrequentTemplate ;

	@Resource
	protected StoreBookmarkTemplate storeBookmarkTemplate ;

	@Resource
	public MobileConfirmTemplate mobileConfirmTemplate ;

	@Resource
	public TableBookingTemplate tableBookingTemplate ;

	@Resource
	public WebsocketClient websocketClient ;

	@Resource
    protected Message message ;

	@Resource
	protected Mail mail ;

	@Resource
	protected Sms sms ;

	@Value("${modio.upload.location}")
	protected String uploadLocation;

	@Value("${modio.image.location}")
	protected String imageLocation;

	@Value("${modio.home.url}")
	protected String homeUrl;

	@Value("${modio.api.base.url}")
	protected String basePath  ;

    /*
     * 로그인된 사용자 인지 체크 
     * @return 로그인된 사용자 여부  true/false
     */
	protected boolean isUser(HttpServletRequest request) {
		
		User user = (User)request.getAttribute("loginUser") ;
		if( user != null && user.getId() != null && ! user.getId().isEmpty() )
			return true ;
		else
			return false ;
	}
	
	/*
     * 로그인 허가된 사용자 인지 체크 
     * @param email  사용자 로그인 id(email)
     * @param accessToken  사용자 로그인 접근 허가 토큰
     * @return 로그인 허가된 사용자 여부  true/false
     */
	protected boolean isUser(String email, String accessToken) {
		
		if( email == null || accessToken == null )
			return false ;
		
		try {
			User user = userRepository.findByEmail(email)  ;
					
			if( user == null ) {
				return false ;
			}
			else if( user.getAccessToken().equals(accessToken)){
				return true ;
			}	
		}
		catch(Exception e){
			e.printStackTrace();
			return false ;
		}
		return false;
	}
	
	
	/*
	 * session  에 저장된 로그인 사용자 정보
	 * @return  session  에 저장된 로그인 사용자 정보
	 */
	protected User getSessionUser(HttpServletRequest request) {

		return (User)request.getAttribute("loginUser") ;
	}
	
	/*
     * 로그인 사용사가 관리자인지 체크
     * @return 관리자 여부  true/false
     */
	protected boolean isAdmin(HttpServletRequest request) {
		
		User user = (User)request.getAttribute("loginUser") ;
		
		if( user != null && user.getId() != null && !user.getId().isEmpty() && user.getLevel() == 10 )
			return true ;
		else
			return false ;
	}
	
}
