package com.youngplussoft.zoenuvo.vertx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.*;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * WebSocket Server
 * 
 * 1) NimbusClient --> Node, Topology Control
 * 2) RTM Data Transfer
 * 
 * 
 * UI -------> RTMGatewayServer -----> KafkaProduce ------> DSS ----->  RTMGatewayServer  ------> UI
 * 
 * @author litu
 *
 */
public class RTMGatewayServer {

	private Vertx vertx = Vertx.vertx() ;
	private Hashtable<String,JsonObject> _wsHash = new Hashtable<String,JsonObject>();
	private Hashtable<String,String> _devHash = new Hashtable<String,String>();
	public Logger logger = LoggerFactory.getLogger(this.getClass());
		
	public RTMGatewayServer(){

		System.setProperty(
				"vertx.logger-delegate-factory-class-name",
				"io.vertx.core.logging.Log4JLogDelegateFactory"
		) ;

		initRTMServer();
	}
	
	public void initRTMServer(){	
		logger.info("initRTMServer");
		final EventBus eb = vertx.eventBus();		
	    vertx.createHttpServer().websocketHandler(new Handler<ServerWebSocket>() {
			@Override
			public void handle(final ServerWebSocket ws) {
				logger.info("Client Connected...");
				System.out.println("Client Connected...") ;
				ws.handler(new Handler<Buffer>() {
					@Override
					public void handle(final Buffer data) {

						logger.info("jsonDate : "+data.toString());
						System.out.println("jsonDate : "+data.toString());
						JsonObject job = new JsonObject(data.toString());
						String cmd  = RTMUtil.isString(job.getString("cmd"),"");
						String devId  = RTMUtil.isString(job.getString("devId"),"");
						

						
						if(cmd != null){
							if( cmd.equals("join") ) {
									JoinWsClient(ws,job);
							}
							else if( cmd.equals("push") && devId != null ) {
							   					
							    	System.out.print("push\n");
							    	pushMessage(devId, job) ;
							        
							}
							else if( cmd.equals("broadcast") ) {
							       					
							    	System.out.print("broadcast\n");
							    	broadCastMessage(job);
							}
							else {
									//ws.reject();
							}		
						}
					}
				});		
				
				ws.closeHandler(new Handler<Void>() {
					@Override
					public void handle(final Void event) {
						logger.info("Client Disconnected..");
						System.out.println("Client Disconnected..");
						RemoveWsClient(ws.textHandlerID());
					}
				});				
			}
		}).listen(RTMSettings.WS_PORT);	
	}	
		
	
	/**
	 * return WebSocket Client List
	 * @return
	 */
	public Hashtable<String, JsonObject> getClientList(){
		return this._wsHash;
	}

	public void pushMessage(String devId, JsonObject vo){

		try {
			logger.info("push message to client : " + vo.getString("devId"));
			String socketId = _devHash.get(devId) ;
			JsonObject _d = this._wsHash.get(socketId);
			ServerWebSocket _ws = (ServerWebSocket)_d.getValue("ws");
			logger.info("message body : " + _ws.textHandlerID() + " : " + vo.toString());
			System.out.println("message body : " + _ws.textHandlerID() + " : " + vo.toString());
			_ws.write(vo.toBuffer());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * BroadCast Message
	 * @param vo
	 * @return
	 */
	public int broadCastMessage(JsonObject vo){
		int sendCount = 0;
		if(vo != null){
			Iterator<String> iter = this._wsHash.keySet().iterator() ;
			while( iter.hasNext() ){
				String key = iter.next() ;
				try {
					JsonObject _d = this._wsHash.get(key);
					ServerWebSocket _ws = (ServerWebSocket)_d.getValue("ws");
					logger.info("BroadCast to UI : " + _ws.textHandlerID() + " : " + vo.toString());
					System.out.println("BroadCast to UI : " + _ws.textHandlerID() + " : " + vo.toString());
					_ws.write(vo.toBuffer());
					sendCount++;
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return sendCount;
	}
	
	
	/**
	 * Join Websocket Client
	 * @param ws
	 */
	public void JoinWsClient(ServerWebSocket ws, JsonObject job){
		logger.info("JonWsClient : "+ws.textHandlerID());
		System.out.println("JonWsClient : "+ws.textHandlerID()) ;
		String devId = job.getString("devId") ;
		String socketId = ws.textHandlerID();
		job.put("socketId", socketId) ;
		job.put("ws", ws) ;
		this._wsHash.put(socketId,job);
		this._devHash.put(devId, socketId) ;
	}

	
	/**
	 * Remove Websocket Client
	 * @param socketId
	 */
	public void RemoveWsClient(String socketId){

		try {
			JsonObject job = this._wsHash.remove(socketId);
			String devId = job.getString("devId");
			_devHash.remove(devId);
		}
		catch(Exception e){
			System.out.println("close socket without join object : " + socketId) ;
		}
	}


	public JsonObject requestToApiServer(String method, String urlStr, String body){

		HttpsURLConnection conn = null ;
		try {
			URL url = new URL(urlStr);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod(method);
			conn.setRequestProperty("Authorization", new BASE64Encoder().encode(("Bearer " + RTMSettings.WS_SERVER_AUTHORIZATION).getBytes()));
			conn.setRequestProperty("Content-Type", "application/json");
			if( body != null ){
				OutputStream output = conn.getOutputStream();
				output.write(body.getBytes()) ;
				output.flush() ;
				output.close() ;
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String res = "", line = null ;
			while( (line=in.readLine()) != null ){
				res += line ;
			}

			JsonObject json = new JsonObject(res) ;
			in.close() ;

			return json ;
		}
		catch(Exception e){
			e.printStackTrace();
			return null ;
		}
		finally {
			if( conn != null )
				try { conn.disconnect(); } catch(Exception ee){}
		}
	}
}
