package com.youngplussoft.common;

import com.youngplussoft.jpa.entity.Error;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.io.StringWriter;

@Service
public class Message {

	private String defaultLocale = "ko_KR" ;

	@Resource(name="messageSourceAccessor")
	public MessageSourceAccessor msAcc ;

	/*
     *  메세지 추출
     * @param  code 조회할 메세지 코드
     * @return 조회한 실제 메세지
     */
	public String getMessage(String code) {
		return msAcc.getMessage(code, defaultLocale) ;
	}

	/*
	 *  메세지 추출
	 * @param  code 조회할 메세지 코드 
	 * @param  objs 메세지에 삽입되는 아규먼트 리스트
	 * @return 조회한 실제 메세지
	 */
	public String getMessage(String code, Object[] objs) {
		return msAcc.getMessage(code, objs, defaultLocale) ;
	}

	/*
	 *  메세지 추출
	 * @param	code 조회할 메세지 코드 
	 * @param	locale 메세지 locale  정보
	 * @return 조회한 실제 메세지
	 */
	public String getMessage(String code, String locale) {
		return msAcc.getMessage(code, locale) ;
	}

	/*
	 *  메세지 추출
	 * @param	code 조회할 메세지 코드 
	 * @param	objs   메세지에 삽입되는 아규먼트 리스트
	 * @param	locale 메세지 locale  정보
	 * @return	조회한 실제 메세지
	 */
	public String getMessage(String code, Object objs[], String locale) {
		return msAcc.getMessage(code, objs, locale) ;
	}

	/*
	 *  에러 메세지로 부터 Error 객체 생성
	 * @param	message  에러 메세지 
	 * @return	Error  메세지 객체 
	 */
	public Error getError(String message) {

		Error err = new Error() ;
		if( message == null ){
			err.setErrcode(0L);
			err.setMessage("Unknown Internal Error!!!");
			return err ;
		}

		String fields[]= message.split(":::") ;
		try {
			if( fields.length ==2 ) {
				err.setErrcode(Long.parseLong(fields[0]));
				err.setMessage(fields[1]);
				err.setRawMessage(fields[1]);
			}
			else {
				err.setErrcode(500L);
				err.setMessage(this.getMessage("error.internal.error"));
				err.setRawMessage(message);
			}
		}
		catch(Exception e){
			err.setErrcode(0L);
			err.setMessage(message);
		}
		finally {
			return err ;
		}
	}

	/*
	 *  에러 메세지로 부터 Error 객체 생성
	 * @param	message  에러 메세지 
	 * @return	Error  메세지 객체 
	 */
	public Error getErrorByCode(String code) {

		Error err = new Error() ;
		String message = msAcc.getMessage(code, defaultLocale) ;
		if( message == null ){
			err.setErrcode(0L);
			err.setMessage("Unknown Internal Error!!!");
			return err ;
		}

		String fields[]= message.split(":::") ;
		try {
			if( fields.length ==2 ) {
				err.setErrcode(Long.parseLong(fields[0]));
				err.setMessage(fields[1]);
				err.setRawMessage(fields[1]);
			}
			else {
				err.setErrcode(500L);
				err.setMessage(this.getMessage("error.internal.error"));
				err.setRawMessage(message);
			}
		}
		catch(Exception e){
			err.setErrcode(0L);
			err.setMessage(message);
		}
		finally {
			return err ;
		}
	}

	public static String exceptionToString(Exception e){
		StringWriter sw = new StringWriter() ;
		PrintWriter pw = new PrintWriter(sw) ;
		e.printStackTrace(pw);
		return sw.toString() ;
	}
}
