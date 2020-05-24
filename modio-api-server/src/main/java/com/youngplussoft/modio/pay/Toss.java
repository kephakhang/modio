package com.youngplussoft.modio.pay;

import com.youngplussoft.common.KeyGenerator;
import com.youngplussoft.common.Message;
import com.youngplussoft.modio.jpa.entity.User;
import org.bson.Document;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Toss {

    protected static final Logger LOGGER = LoggerFactory.getLogger(Toss.class);
    public static final String name = "Toss" ;
    private static final String apiHostUrl = "https://youngplussoft.com:8444" ;
    private static String apiKey ;

    static {
        apiKey = KeyGenerator.getTossKey(false) ;
    }

    public static JSONObject toss(Document pointCharge, Message message, User user) throws Exception {

        JSONObject json = new JSONObject() ;
        json.put("orderNo", pointCharge.getString("orderNo")) ;
        json.put("amount", pointCharge.getInteger("amount")) ;
        json.put("productDesc", "모디오 커피 구매 쿠폰 충전") ;
        json.put("userPhone", user.getMobile()) ;
        json.put("partnerId", user.getId()) ;
        try {
            JSONObject res = Toss.createPayment(json);
            if( res.getInt("code") == 0 ){

                    return res ;
            }
            else {
                throw new Exception(message.getMessage("error.point.toss.create.payment")) ;
            }
        }
        catch(Exception e){
            LOGGER.error(Message.exceptionToString(e)) ;
            throw new Exception(message.getMessage("error.point.toss.create.payment")) ;
        }
    }

    //{"code":0,"checkoutPage":"https://toss.im/tosspay/order/orderWait?payToken=test_token1234567890a&retUrl=http://YOUR-SITE.COM","payToken":"test_token1234567890a"}
    public static JSONObject createPayment(JSONObject jsonBody) throws Exception {
        URL url = null;
        URLConnection connection = null;
        BufferedOutputStream bos = null ;
        BufferedReader br = null ;
        StringBuilder responseBody = new StringBuilder();
        try {
            url = new URL("https://toss.im/tosspay/api/v1/payments");
            connection = url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            /*
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("orderNo", "2015072012211");
            jsonBody.put("amount", 10000);
            jsonBody.put("productDesc", "테스트 결제");
            jsonBody.put("apiKey", "sk_test_apikey1234567890a");
            jsonBody.put("expiredTime", "2015-07-20 16:21:00");
            jsonBody.put("resultCallback", "https://myshop.com/toss/result.php");
            */

            jsonBody.put("resultCallback", apiHostUrl + "/modio/paygate/toss/result/" + jsonBody.getString("orderNo") + "/");
            jsonBody.put("cashReceipt", true) ;
            jsonBody.put("checkoutType", "both") ;
            jsonBody.put("arsAuthSkippable", "Y") ;
            jsonBody.put("apiKey", apiKey) ;
            jsonBody.put("autoExecute", true) ;
            jsonBody.put("metadata", "{\"service\" : \"MoDiO\"}") ;

            bos = new BufferedOutputStream(connection.getOutputStream());
            bos.write(jsonBody.toString().getBytes());
            bos.flush();
            bos.close();
            bos = null ;

            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                responseBody.append(line);
            }
            br.close();
            br = null ;
            LOGGER.debug(responseBody.toString()) ;
            return new JSONObject(responseBody.toString()) ;
        } catch (Exception e) {
           LOGGER.error(Message.exceptionToString(e)) ;
            throw e ;
        }
        finally{
            try{ if(bos != null ) bos.close(); } catch(Exception e){}
            try{ if(br != null ) br.close(); } catch(Exception e){}
        }
    }

    public static JSONObject checkPayment(String  orderNo) throws Exception {
        URL url = null;
        URLConnection connection = null;
        BufferedOutputStream bos = null ;
        BufferedReader br = null ;
        StringBuilder responseBody = new StringBuilder();
        try {
            url = new URL("https://toss.im/tosspay/api/v1/status");
            connection = url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            JSONObject jsonBody = new JSONObject() ;

            jsonBody.put("resultCallback", apiHostUrl + "/modio/toss/result");
            jsonBody.put("orderNo", true) ;


            bos = new BufferedOutputStream(connection.getOutputStream());
            bos.write(jsonBody.toString().getBytes());
            bos.flush();
            bos.close();
            bos = null ;

            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                responseBody.append(line);
            }
            br.close();
            br = null ;
            return new JSONObject(responseBody.toString()) ;
        } catch (Exception e) {
            throw e ;
        }
        finally{
            try{ if(bos != null ) bos.close(); } catch(Exception e){}
            try{ if(br != null ) br.close(); } catch(Exception e){}
        }
    }

    public static void main(String[] args){

        JSONObject json = new JSONObject() ;
        json.put("orderNo", "20180502111111") ;
        json.put("amount", 10000) ;
        json.put("productDesc", "모디오 커피 구매 쿠폰 충전") ;
        json.put("userPhone", "01099902251") ;
        try {
            Toss.createPayment(json);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
