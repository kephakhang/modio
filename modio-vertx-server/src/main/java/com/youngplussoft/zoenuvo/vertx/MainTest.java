package com.youngplussoft.zoenuvo.vertx;

import java.io.Console;

public class MainTest {
	
	
	public static void freeze() throws InterruptedException {
	    Object obj = new Object();
	    synchronized (obj) {
	        obj.wait();
	    }
	 }

	  public static void main(String[] args)  {
		  
			try {
				  RTMGatewayServer rtm = new RTMGatewayServer();
				  freeze() ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }	  
}
