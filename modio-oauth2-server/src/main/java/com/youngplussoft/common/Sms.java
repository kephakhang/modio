package com.youngplussoft.common;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.JSONObject;

import javax.annotation.Resource;
import java.util.HashMap;

public class Sms {
    private static final String api_key = "NCSZH5RUGZWER3YK" ;
    private static final String api_secret = "ZDWLHTKN9T6FG3NGQOQ8EUJSNHTDLQAL" ;
    private static final String sender = "024172251" ;


    public static boolean send(String mobile, String text){

        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", mobile);
        params.put("from", sender);
        params.put("type", "SMS");
        params.put("text", text);
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString()) ;
            if( obj != null && ((Integer)obj.get("success_count")) > 0 ) {

                return true;
            }
            else {
                return false;
            }
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
            return false ;
        }
    }

    public static void main(String[] args){

        Sms.send("821023232551", "MoDiO:인증번호발송\r\n인증번호:1234567") ;
    }
}
