package com.youngplussoft.modio.common;

import javax.annotation.Resource;
import javax.naming.AuthenticationException;

import com.youngplussoft.modio.jpa.entity.Login;
import com.youngplussoft.modio.jpa.entity.User;
import com.youngplussoft.modio.jpa.repository.UserRepository;
import com.youngplussoft.modio.jpa.template.UserTemplate;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("LoginAuthenticator")
public class LoginAuthenticator implements Authenticator {

	@Resource
	UserTemplate userTemplate ;
	
	/*
	 * 로그인 인증
	 * @param id 	 로그인  id(email)
	 * @param password  로그인 암호 
	 * @return  로그인 체크 결과  true/false
	 */
	public boolean authenticate(String email, String password) throws AuthenticationException{
		Login login = new Login() ;
		login.setEmail(email);
		login.setPassword(password) ;
		
		try {
			Document user = userTemplate.findByEmail(email) ;
			if( user != null ) {
			
				return true ;
			}
			else
				return false ;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false ;
		}
	}
}
