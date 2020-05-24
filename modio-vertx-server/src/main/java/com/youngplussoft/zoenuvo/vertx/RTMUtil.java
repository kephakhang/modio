package com.youngplussoft.zoenuvo.vertx;

import java.text.DecimalFormat;
import java.util.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;

public class RTMUtil {
	    	
	  	public RTMUtil() {  }
	  
	    
	  	public static int isNumber(String num, int value1)  {
	  		int value = 0;
	    	try {
				value = Integer.valueOf(num).intValue();
			}catch (NumberFormatException e) {	value = value1;	}
			catch(Exception e){	value = value1;	}	
			
			return value;
	  	}  
	  		  	
	  	public static int isNumber(int num, int value1)  {
	  		int value = 0;
	    	try {
				value = Integer.valueOf(num).intValue();
			}catch (NumberFormatException e) {	value = value1;	}
			catch(Exception e){	value = value1;	}	
			
			return value;
	  	}  
	  	
	  	public static long isNumber(String num, long value1)  {
	  		long value = 0;
	    	try {
				value = Integer.valueOf(num).longValue();
			}catch (NumberFormatException e) {	value = value1;	}
			catch(Exception e){	value = value1;	}	
			
			return value;
	  	}  	  	
	  	
	  	public static long isNumber(int num, long value1)  {
	  		long value = 0;
	    	try {
				value = Integer.valueOf(num).longValue();
			}catch (NumberFormatException e) {	value = value1;	}
			catch(Exception e){	value = value1;	}	
			
			return value;
	  	}  	  	
	  	
	  	public static long isNumber(long num, long value1)  {
	  		long value = 0;
	    	try {
				value = Long.valueOf(num).longValue();
			}catch (NumberFormatException e) {	value = value1;	}
			catch(Exception e){	value = value1;	}	
			
			return value;
	  	}  	  	
	  	
	  	public static Double isDouble(String num, Double value1)  {
	  		Double value = 0.0;
	    	try {
				value = Double.valueOf(num).doubleValue();
			}catch (NumberFormatException e) {	value = value1;	}
			catch(Exception e){	value = value1;	}	
			return value;
	  	}  	  	

	  	public static String onlyNumber(String str) {
	  		if ( str == null ) return "";	  	  
	  	  	StringBuffer sb = new StringBuffer();
	  	  	for(int i = 0; i < str.length(); i++){
	  		  if( Character.isDigit( str.charAt(i) ) ) {
	  	    	sb.append( str.charAt(i) );
	  	    	}
	  	  	}
	  	  	return sb.toString();
	  	 } 

	  	public static String isDate(String val){
	  		String ret = isString(val,"");
	  		if( ret.equals("")){
	  			ret = "";
	  		}else{
	  			if( ret.length() >10 ){
	  				ret = ret.substring(0,10);
	  			}else{
	  				ret = "";
	  			}
	  		}
	  		return ret;
	  	}
	  	
	    // =====================================================================
	    // �־������� ���ڰ����� Ȯ��, �ƴҰ�� value1 �� ����Ʈ�� ���� 
	    // =====================================================================	  	
	  	public static String isString(String value, String value1) {
	    	try {
				if(value == null){	value = value1;	}			
			}catch(Exception e){	value = value1;	}	
	    	return value;
	  	}
	  
	    // =====================================================================
	    // �ѱ��ڸ���
	    // =====================================================================
	  	public static String hanCut(String value, int size){
			if(value == null) {		return "";		}
			if(value.length() > size){
				value = value.substring(0,size)+"...";
			}
			return value;
		}
	  
	    // =====================================================================
	    // ����ϸ� ���� : ����Ŭ�� Date ������ ��� �ڵ����� �ڿ� HH:MM:SS ���� ��������
	  	// �̰��� ����� �� �ʿ䰡 �ִ�
	    // =====================================================================
	  	public static String dateFormat(String data_s, String div) throws Exception {
	    	if(data_s != null){
	     		if(data_s.length() > 7 ){
	      			//data_s = data_s.substring(0,4)+div+data_s.substring(4,5) +div+ data_s.substring(6,7);
	     			data_s = data_s.replace(" 00:00:00","");
	      		}else{
	      			return "";  
	      		}
	      	}else{
	        	return "";  
	      	} 
	      	return data_s;  
	  	} 
	  
	    // =====================================================================
	    // ��-��-���� ������ ����
	    // =====================================================================
	   	public static String dateFormat2(String data_s) throws Exception {
	    	if(data_s != null){
				if(data_s.length() > 13 ){
	      			data_s = data_s.substring(0,4) +"-"+ data_s.substring(5,6) +"."+ data_s.substring(6,8)+" "+data_s.substring(8,10)+":"+ data_s.substring(10,12)+":"+data_s.substring(12);
	      		}else{
	      			return "";  
	      		}	
	      	} else{
	        	return "";  
	      	} 	      
	      	return data_s;  
	  	} 
	   	
        // =====================================================================
	   	// ���糯¥��ȯ
        // =====================================================================
	    public static String getCurrentDateTime(){
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy�� MM�� dd��  HH�� mm�� ss��");
	        Date date = new Date(System.currentTimeMillis());
	        return dateFormat.format(date);
	    }
        public static String getCurrentYearMonthDay(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy�� MM�� dd��");
            Date date = new Date(System.currentTimeMillis());
            return dateFormat.format(date);
        }	    
	    public static String getBaseTime(){
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        Date date = new Date(System.currentTimeMillis());
	        return dateFormat.format(date);
	    }   
	    public static String getSendTime(){
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date date = new Date(System.currentTimeMillis());
	        return dateFormat.format(date);
	    }     
        public static String getCMSCurrentDate(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date(System.currentTimeMillis());
            return dateFormat.format(date);
        }	   
        public static String getCurrentDashDate(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            return dateFormat.format(date);
        }	        
        public static String getSCJDate(String ty, String prefix){
        	String ret = "";
        	GregorianCalendar gc = new GregorianCalendar ( );
        	int y=gc.get(Calendar.YEAR)-1983;
        	int m=gc.get(Calendar.MONTH)+1 ;
        	int d=gc.get(Calendar.DATE);
        	int h=gc.get(Calendar.HOUR_OF_DAY);
        	int i=gc.get(Calendar.MINUTE);
        	int s=gc.get(Calendar.SECOND);
        	
        	String yy = String.format("%04d", y);
        	String mm = String.format("%02d", m);
        	String dd = String.format("%02d", d);
        	String hh = String.format("%02d", h);
        	String ii = String.format("%02d", i);
        	String ss = String.format("%02d", s);
        	
        	if(ty.equals("")){
        		ret = prefix+yy+"-"+mm+"-"+dd;
        	}else if(ty.equals("ymd")){
        		ret = prefix+yy+mm+dd;
        	}else if(ty.equals("ymdhis")){
        		ret = prefix+yy+"-"+mm+"-"+dd+" "+hh+":"+ii+":"+ss;
        	}
        	return ret;
        }
        
        public static long getUnixTime(){
        	return System.currentTimeMillis()/1000L;
        }
        
        public static String getWeek(int y, int m, int d, String retTy){		// E, H, S
        	String ret = "";
            String ch_week = "";

            Calendar c= Calendar.getInstance();
            m = m-1;
            c.clear();
            c.set(y, m, d); 
            switch(c.get(c.DAY_OF_WEEK)) {
               case Calendar.SUNDAY:
                  ch_week = "�Ͽ���";
                  ret = "SUN";
                  break;
               case Calendar.MONDAY:
                  ch_week = "������";
                  ret = "MON";
                  break;
               case Calendar.TUESDAY:
                  ch_week = "ȭ����";
                  ret = "TUE";
                  break;
               case Calendar.WEDNESDAY:
                  ch_week = "������";
                  ret = "WED";
                  break;
               case Calendar.THURSDAY:
                  ch_week = "�����";
                  ret = "THU";
                  break;
               case Calendar.FRIDAY:
                  ch_week = "�ݿ���";
                  ret = "FRI";
                  break;
               case Calendar.SATURDAY:
                  ch_week = "�����";
                  ret = "SAT";
                  break;
            }    
            if(retTy.equals("H")){
            	return ch_week;
            }else if(retTy.equals("S")){
            	return ch_week.replace("����", "");
            }else{
            	return ret;
            }
        }
        
        
	    
	    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    // ���ϰ���
	    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    public static String getExtension(String fileStr){
	        return fileStr.substring(fileStr.lastIndexOf(".")+1,fileStr.length());
	    }
	   	
	    
	    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	   	// �����ð���
	   	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    // =====================================================================
	    // ����¡
	    // =====================================================================
	  	public static int totalPage(int max_num,  int list_num ) {
	  		int page_cnt = (int)(max_num / list_num);
	        if (page_cnt == 0) page_cnt = 1;
	    	else if (page_cnt > 0 && (max_num % list_num) > 0) page_cnt = page_cnt + 1;
	    	else if (page_cnt > 0 && (max_num % list_num) ==0) page_cnt = page_cnt;
	  	
	  		return page_cnt;
	  	}  
		  
	    // =====================================================================
	    // �ѱ����ڵ�
	    // =====================================================================
	  	public static String hanEncode(String src) {
	    	if(src == null) {   return "";   }
	        String result = null;
	    	try {
	      		result = URLEncoder.encode(ksc(src));
	    	}catch(Exception e) {
	      		result = null;
	    	}
	    	return result;  
	  	}
	  
	    // =====================================================================
	    // �ѱ۵��ڵ�
	    // =====================================================================
	  	public static String hanDecode(String src) {
			if(src == null) {  return "";   }
	        String result = null;
	    	try {
	      		result = han(URLDecoder.decode(src));
	    	}catch(Exception e) {
	      		result = null;
	    	}
	    	return result;    
	  	}
	  
	  	// KSC ================================================
	  	public static String han(String str)  {
	    	if(str == null) {  return new String("");    }
	        try{  
	      		str = new String(str.getBytes("8859_1"),"KSC5601");
	    	}catch(Exception e){  }
	    	return isString(str,"");
	  	} 

		// ISO ================================================
	  	public static String ksc(String str)  {
	    	if(str == null) { return new String("");  }
	        try{
	      		str = new String(str.getBytes("KSC5601"),"8859_1");
	    	}catch(Exception e){    }
	    	return str;
	  	}  
        // UTF ================================================
        public static String utf(String str)  {
            if(str == null) { return new String("");  }
            try{
                str = new String(str.getBytes("8859_1"),"UTF-8");
            }catch(Exception e){    }
            return str;
        }  	  	
         	
	  
	  	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	  	// ��Ű/���ǰ���
	  	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    // =====================================================================
	    // URL �Ľ�
	    // =====================================================================
	  	public static String paramToString(String param) {
			if(param == null) return "";
			param = param.replace('&', '|');
			param = param.replace('=', '*');
			return param;
	  	}
		
	  	public static String stringToParam(String str) {
			if(str == null) return "";
			str = str.replace('|', '&');
			str = str.replace('*', '=');
			return str;
	  	}

	    // =====================================================================
	    // 3�ڸ����� �޸����
	    // =====================================================================
	  	public static String numberFormat(int value){
	  	  String result = new DecimalFormat("###,###,###,###,###").format(value);
	  	  if(result.equals("0")){
	  		 result = "0";  
	  	  }
	  	  return result;
	  	}	
	  	
	  	public static String numberFormat(String value){
	  	    int new_val = isNumber(Integer.parseInt(value), 0);
	  		String result = numberFormat(new_val);
	  		return result;
	  	}

	  	public static String numberFormat2(long value){
		  	  String result = new DecimalFormat("###,###,###,###,###").format(value);
		  	  return result;
		}		  	
	  	
	  	public static String numberFormat(long value){
		  	  String result = new DecimalFormat("###,###,###,###,###").format(value);
		  	  if(result.equals("0")){
		  		 result = "0";  
		  	  }
		  	  return result;
		}		  	

	    // =====================================================================
	    // Email �ּ� üũ
	    // =====================================================================
       public static boolean emailCheck(String email) {
         if (email==null) return false;
         boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
         return b;
       }

       
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//  ��¥���� �޼ҵ�
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    // =====================================================================
	    // ��¥��
	    // =====================================================================  
	  	public static String compareDate(String s_d,String f_d,String to_day){
			String full_s_day=s_d.substring(0,4) + s_d.substring(4,6) + s_d.substring(6,8);
			String full_e_day=f_d.substring(0,4) + f_d.substring(4,6) + f_d.substring(6,8);
			String full_to_day=to_day.substring(0,4) + to_day.substring(4,6) + to_day.substring(6,8);
		
			int full_sday=Integer.parseInt(full_s_day);
			int full_eday=Integer.parseInt(full_e_day);
			int full_today=Integer.parseInt(full_to_day);
		
			String str = "";
			if ((full_today>=full_sday) && (full_today<=full_eday))	{
				return "????";
			}else if(full_today>full_eday)	{
				return "~??";
			}else if(full_today<full_eday)	{
				return "??d";
			}else	{
				return "";
			}
		}
	  	
		// ======================================================================
		// �ش���� ��������¥ ���ϱ�
		// ======================================================================
		public static String getLastDay(int intYear, int intMonth) { 
			Calendar cal = new GregorianCalendar(); 
			cal.setLenient(false); 
			cal.set(intYear, intMonth - 1, 1); 
			return Integer.toString(cal.getActualMaximum(GregorianCalendar.DATE)); 
		} 	
		
		// ======================================================================
		// ���ر��ϱ�
		// ======================================================================
		public static int getCurrentYear(){
            GregorianCalendar today = new GregorianCalendar();
            return today.get(today.YEAR);
		}
		
		// ======================================================================
		// �̹��� ���ϱ�
		// ======================================================================
		public static int getCurrentMonth(){
            GregorianCalendar today = new GregorianCalendar(); 
            return today.get(today.MONTH)+1;           
		}	
		
        // ======================================================================
        // �̹��� ���ϱ� - 02
        // ======================================================================
        public static String getCurrentMonth2(){
            String r = "01";
            GregorianCalendar today = new GregorianCalendar(); 
            int ret = today.get(today.MONTH)+1;
            if(ret<10){
                r = "0"+Integer.toString(ret);
            }else{
                r = Integer.toString(ret);
            }
            return r;
        }   		
		
		// ======================================================================
		// ���ñ��ϱ�
		// ======================================================================
		public static int getCurrentDay(){
            GregorianCalendar today = new GregorianCalendar(); 
            return today.get(today.DATE);  			
		}		
		
        // ======================================================================
		// ������ ù��/��������
        // ======================================================================
		public static String getCurrentFirstDay(String isLast){
		    if(isLast.equals("first")){
		        return Integer.toString(getCurrentYear())+"-01-01";
		    }else{
                return Integer.toString(getCurrentYear())+"-12-31";		        
		    }
		}
		
		
		// ======================================================================
		// ���ݳ���� ���� n ����/���� ��-���� ���ϱ�
		// JDK 1.4 ������ String.format�� �������� �������� 0x�� ��¥�� �������
		// ���� �Ʒ��� ���� ����� ����Ѵ�.
		// ======================================================================
		public static String getTermYearMonth(int prev_month){
		    GregorianCalendar today = new GregorianCalendar();
		    today.add(Calendar.MONTH,prev_month); 
		    int year  = today.get(today.YEAR);                      
		    int month = today.get(today.MONTH)+1;                         
		   
		    String end_date = Integer.toString(year)+"-";
		    if(month<10){
		       end_date += "0"+Integer.toString(month);
		    }else{ 
		       end_date += Integer.toString(month);
		    }
		    return end_date;
		}
		
        // ======================================================================
		// ������ȸ�� ��¥ ��������
        // ======================================================================		
        public static String getPeriodEndDe(int Y, int M, int D){
            Calendar today = Calendar.getInstance();
            today.set(Y, M, D);
            today.add(Calendar.YEAR,0);
            today.add(Calendar.MONTH,0);
            today.add(Calendar.DATE, -1);
            
            int year  = today.get(today.YEAR);                      
            int month = today.get(today.MONTH);
            int day   = today.get(today.DATE);
            if(month==0){
                month = 12;
            }
           
            String end_date = Integer.toString(year)+"-";
            if(month<10){
               end_date += "0"+Integer.toString(month);
            }else{ 
               end_date += Integer.toString(month);
            }
            if(day<10){
                end_date += "-0"+Integer.toString(day);
             }else{ 
                end_date += "-"+Integer.toString(day);
             }
            return end_date;
        }		
		
		// ======================================================================
		// �γ�¥���� ���̱��ϱ� : �ϰ�ǥ���� ���
		// ======================================================================
		public static int getDateDiffer( int nYear1, int nMonth1, int nDate1, int nYear2, int nMonth2, int nDate2 ){
			Calendar cal = Calendar.getInstance ( );
			int nTotalDate1 = 0, nTotalDate2 = 0, nDiffOfYear = 0, nDiffOfDay = 0;

			if ( nYear1 > nYear2 ){
					for ( int i = nYear2; i < nYear1; i++ ){
						  cal.set ( i, 12, 0 );
						  nDiffOfYear += cal.get ( Calendar.DAY_OF_YEAR );
					}
					nTotalDate1 += nDiffOfYear;
			}else if( nYear1 < nYear2 ){
					for( int i = nYear1; i < nYear2; i++ ){
						 cal.set ( i, 12, 0 );
						 nDiffOfYear += cal.get ( Calendar.DAY_OF_YEAR );
					}
					nTotalDate2 += nDiffOfYear;
			}

			cal.set ( nYear1, nMonth1-1, nDate1 );
			nDiffOfDay = cal.get ( Calendar.DAY_OF_YEAR );
			nTotalDate1 += nDiffOfDay;

			cal.set ( nYear2, nMonth2-1, nDate2 );
			nDiffOfDay = cal.get ( Calendar.DAY_OF_YEAR );
			nTotalDate2 += nDiffOfDay;

			return nTotalDate2-nTotalDate1;
		} 
		
		// ======================================================================
		// �Է��� ����  n������ ��¥���ϱ�
		// ======================================================================
		public static String getTermDate(String indate, int num){
	        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
	        Calendar c = Calendar.getInstance ( );
	        
	        int s_year  = Integer.parseInt( indate.substring(0,4) );
	        int s_month = Integer.parseInt( indate.substring(5,7) );
	        int s_day   = Integer.parseInt( indate.substring(8,10));
	        
            c.clear();
            c.set( s_year, s_month-1, s_day + num );
            Date d = c.getTime ();
            String thedate   = sdf.format ( d );
            
            return thedate;
		}
			
		
		// ======================================================================
		// �Էµ� �γ�¥������ ��¥���� ����Ʈ���ϱ�
		// ���� �ΰ��� ���̰��������(�����ϰ� �������� �������ϰ�쿡��)
		// �����ϸ� �־ ������
		// ======================================================================
		public static List<String> getTermDateList(String start_date, String end_date){
			List<String> list = new ArrayList();
			
	        int s_year  = Integer.parseInt( start_date.substring(0,4) );
	        int s_month = Integer.parseInt( start_date.substring(5,7) );
	        int s_day   = Integer.parseInt( start_date.substring(8,10));
	        
	        int e_year  = Integer.parseInt( end_date.substring(0,4) );
	        int e_month = Integer.parseInt( end_date.substring(5,7) );
	        int e_day   = Integer.parseInt( end_date.substring(8,10));
	        
	        int num = getDateDiffer(s_year, s_month, s_day, e_year, e_month, e_day);
        
	        if(num==0){
	        	list.add(start_date);
	        }else{
				String new_date = "";	        	
	        	for(int i=0; i<=num; i++){
	        		new_date = getTermDate(start_date, i);
	        		list.add(new_date);
	        	}
	        }
			return list;
		}			
		
		
		// ----------------------------------------------------------------------
		// ��ǥ��ȣ ������ֱ�
		// ----------------------------------------------------------------------
		public static String MakeActNo(String reg_date, String act_no){
			String result = reg_date.replaceAll("-", "")+"-";
			if(act_no.length()>1){
				result += act_no;
			}else{
				result += "0"+act_no;
			}			
			return result;
		}
		
        // ----------------------------------------------------------------------
		// 6�ڸ��� ������� �⵵, �� ������ ��ȯ
        // ----------------------------------------------------------------------		
		public static String levyYm2Text(String levyYm){
	        String yy = "";
	        String mm = "";
	        String ret = "�Ⱓ����";
	        levyYm = isString(levyYm,"");
	        if(!levyYm.equals("")){
	            if(levyYm.length()==6){
	                yy = levyYm.substring(0,4);
	                mm = levyYm.substring(4,6);                
	                levyYm = yy+"�� "+mm+"��";
	            }else{
	                levyYm = "�Ⱓ����";
	            }
	        }else{
	            yy = "";
	            mm = "";
	            levyYm = "�Ⱓ����";            
	        }
	        return ret;
		}
	
        // ----------------------------------------------------------------------
        // 10������ �ݿø�ó�� : round
        // ----------------------------------------------------------------------
        public static long round(double amt){
            long abs = Math.round(amt);
            long ret = abs;
            if(abs>0){
                String tNum    = Long.toString(abs);
                String lastNum = tNum.substring(tNum.length()-1, tNum.length());
                long lastAmt    = isNumber(lastNum, 0);
                long sprAmt     = 10-lastAmt;
                
                if(lastAmt>=5){
                    ret = abs+sprAmt;
                }else{
                    ret = abs-lastAmt;
                }                
            }
            return ret;
        }		
        
        // ----------------------------------------------------------------------
		// 10������ �ø�ó�� : CEIL
        // ----------------------------------------------------------------------
		public static long ceil(double amt){
            long abs = (long)Math.ceil(amt);
            long ret = abs;
            if(abs>0){
                String tNum     = Long.toString(abs);
                String lastNum  = tNum.substring(tNum.length()-1, tNum.length());
                long lastAmt    = isNumber(lastNum, 0);
                long sprAmt     = 10-lastAmt;
                
                if(lastAmt>0){
                    ret = abs+sprAmt;
                }                
            }
            return ret;
		}
		
        // ----------------------------------------------------------------------
		// 10������ ����ó�� : FLOOR
        // ----------------------------------------------------------------------
        public static long floor(double amt){
            long abs = (long)Math.floor(amt);
            long ret = abs;
            if(abs>0){
                String tNum     = Long.toString(abs);
                String lastNum  = tNum.substring(tNum.length()-1, tNum.length());
                long lastAmt    = isNumber(lastNum, 0);
               
                ret = abs-lastAmt;
            }
            return ret;
        }		
		
		
		// **********************************************************************
		// Mime���
		// **********************************************************************
	    public static String getMime(String m){
	    	String ret = "text/html";
	    	if(m.equals("json")){			ret = "application/json;charset=utf-8";    		
	    	}else if(m.equals("xml")){		ret = "application/xml;charset=utf-8";   
	    	}else if(m.equals("script")){   ret = "application/x-javascript; charset=utf-8";   
	    	}else if(m.equals("html")){     ret = "text/html; charset=utf-8";
	    	}
	    	return ret;
	    }		

		
		// **********************************************************************
		// MAPó��
        // **********************************************************************
        // ----------------------------------------------------------------------
	    // HttpQueryString �� Map���� �ٲ㼭 �������ش�. 
        // ----------------------------------------------------------------------
	    public static void QueryToMap(Map<String, String> map, String qs) throws UnsupportedEncodingException {          
	        if( qs == null ) return;
	        String[] nvs = qs.split( "&" );
	        for( String nv : nvs ) {
	            String[] parts = nv.split( "=" );
	            String key = parts[0];
	            String val = null;
	            if( parts.length > 1 ) val = parts[1];
	            if( val != null ) {
	                try {
	                    val = URLDecoder.decode( val, "UTF-8" );
	                } catch( UnsupportedEncodingException ex ) {
	                    throw new RuntimeException( ex );
	                }
	            }
	            map.put( key, val );
	        }
	    }     
	    
        // ----------------------------------------------------------------------
	    // Map2Select
        // ----------------------------------------------------------------------
	    public static String Map2Select(Map<String,String> _map, String selVal){
	        String optionHtml = "";
	        Iterator<String> iter = _map.keySet().iterator();
	        
	        while (iter.hasNext()){
	            String nKey  = iter.next();
	            String nValue= _map.get(nKey);
	                        
	            if (nKey.equals(selVal)){
	                optionHtml += "<option value="+nKey+" selected>"+nValue+"</option>";
	            }else{
	                optionHtml += "<option value="+nKey+">"+nValue+"</option>";                
	            }
	        }
	        return optionHtml;
	    }
	    
        // ----------------------------------------------------------------------
	    // MapValue
        // ----------------------------------------------------------------------
	    public static String MapValue(Map<String,String> _map, String selKeyVal){
	        String ret = "";
	        Iterator<String> iter = _map.keySet().iterator();
	        
	        while (iter.hasNext()){
	            String nKey  = iter.next();
	            String nValue= _map.get(nKey);
	                        
	            if (nKey.equals(selKeyVal)){
	                ret = nValue;
	            }
	        }
	        return ret;
	    }	    
}
