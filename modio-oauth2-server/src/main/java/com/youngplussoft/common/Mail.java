package com.youngplussoft.common;

import org.apache.commons.codec.net.QuotedPrintableCodec;
import org.mozilla.intl.chardet.HtmlCharsetDetector;
import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;
import org.mozilla.intl.chardet.nsPSMDetector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;
import java.io.*;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@PropertySource("classpath:/application.properties")
@Component
public class Mail {
	
	/*
	@Value("${modio.email.sender}")
    public String emailSender;
	
	@Value("${modio.email.server}")
    public String emailServer;
	
	@Value("${modio.email.user}")
    public String emailUser;
	
	@Value("${modio.email.password}")
    public String emailPassword;
	
	@Value("${modio.email.protocol}")
    public String emailProtocol;
	
	@Value("${modio.email.port}")
    public String emailPort;
    
    @Value("${modio.email.starttls}")
    public String emailStarttls;

    
    //=====================================
    @Value("${modio.remail.type}")
    public String remailType;
    
	@Value("${modio.remail.receiver}")
    public String remailReceiver;
	
	@Value("${modio.remail.server}")
    public String remailServer;
	
	@Value("${modio.remail.user}")
    public String remailUser;
	
	@Value("${modio.remail.password}")
    public String remailPassword;
	
	@Value("${modio.remail.protocol}")
    public String remailProtocol;
	
	@Value("${modio.remail.port}")
    public String remailPort;
	
	@Value("${modio.remail.ssl}")
    public String remailSsl;
	
	@Value("${modio.remail.tmp}")
    public String remailTmp;

    */
    

	public final String emailSender = "contact@youngplussoft.com" ; //Request.getProperty("EMAIL_SENDER") ;
	public final String emailServer = "smtp.daum.net" ; //Request.getProperty("EMAIL_SERVER") ;
	public final String emailUser = "sunwon40" ; //Request.getProperty("EMAIL_USER") ;
	public final String emailPassword = "123yps#@!" ; //Request.getProperty("EMAIL_PASSWORD") ;
	public final String emailProtocol = "smtps" ; //Request.getProperty("EMAIL_PROTOCOL") ;
	public final String emailPort = "465" ; //Request.getProperty("EMAIL_PORT") ;
    public final String emailStarttls = "false" ;

    public final String remailType = "pop3" ;
	public final String remailServer = "smtp.blueweb.co.kr" ; //Request.getProperty("EMAIL_SERVER") ;
	public final String remailUser = "youngplussoftyun@youngplussoft.com" ; //Request.getProperty("EMAIL_USER") ;
	public final String remailPassword = "diffl2010" ; //Request.getProperty("EMAIL_PASSWORD") ;
	public final String remailPort = "587" ; //Request.getProperty("EMAIL_PORT") ;
	public final String remailSsl = "true" ;
    public final String remailTmp = "/tmp" ;

	
	
	public boolean debug = true ;
	public static Mail zoeMail = null ;
	public Date theDay = null ;
    public int beforeDays = 3 ;
    public long mb_no = 0 ;
    public MailContent mailContent ;
    public long lastTime = 0 ;
    public String lastSubjectHash ;
    
    public class MailContent {
        public String from ;
        public String subject ;
        public String body = "" ; // default 값이 필요함
        public Date sentDate ;
        public String hash ;
        public ArrayList<FileInfo> attachArr = new ArrayList<FileInfo>() ;
    }

    public class FileInfo {
        public String filename ;
        public File file ;
    }

    private javax.mail.search.ReceivedDateTerm ReceivedDateTerm(int GE, Date theDay) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	class MyAuthenticator extends Authenticator {
		PasswordAuthentication pa;

		public MyAuthenticator() {
			pa = new PasswordAuthentication(Mail.getInstance().emailUser, Mail.getInstance().emailPassword);
			// 여러분들이 사용하고 있는 smtp server의 아이디와 패스워드를 입력한다.
		}
		
		public MyAuthenticator(String user, String password) {
			pa = new PasswordAuthentication(user, password);
			// 여러분들이 사용하고 있는 smtp server의 아이디와 패스워드를 입력한다.
		}

		// 아래의 메소드는 시스템측에서 사용하는 메소드이다.
		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
		}
	}
	
	/*
	 * Mail 객체  static  인스탄스 생성
	 * @return Mail 객체  static  인스탄스
	 */
	public static Mail getInstance() {
		
		
		synchronized(Object.class){
			if( zoeMail == null ) 
				zoeMail = new Mail() ;
		}
		
		return zoeMail ;
	}
	
	/*
	 * 이메일 송신 함수
	 * @param email		수신자 이메일 주
	 * @param name		수신자 이름
	 * @param title		이메일 제목
	 * @param message	이메일 메세지
	 * @throws Exception  에러 발생 시  Exception  발생
	 */
	public void smtpSend(String email, String name, String title, String message) throws Exception {

		String to = email;
		String from = emailSender;
		String host = emailServer;
		//String filename = args[3];
		String msgText1 = message;
		String subject = title;
		
		// create some properties and get the default Session
		Properties props = System.getProperties();
		props.put("mail.smtp.host", emailServer);
		props.put("mail.smtp.auth", "true");
		//props.put("mail.transport.protocol", emailProtocol);
		props.put("mail.smtp.port", emailPort);
		props.put("mail.smtp.user", emailUser);
		props.put("mail.smtp.password", emailPassword);
		props.put("mail.smtp.starttls.enable",emailStarttls);
		
		if( emailProtocol.equals("smtps") ) {
			props.put("mail.transport.protocol", emailProtocol);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory") ;
			props.put("mail.smtp.socketFactory.fallback", "false") ;
		}
		else if( emailStarttls.equals("true") ) {
			props.put("mail.smtp.ssl.trust", emailServer);
		}
		Authenticator authenticator = new MyAuthenticator();
		
		Session session = Session.getInstance(props, authenticator);
		//Session session = Session.getDefaultInstance(props, null);
		session.setDebug(debug);
		
	    // create a message
	    MimeMessage msg = new MimeMessage(session);
	    msg.setFrom(new InternetAddress(from, "MoDiO Inc.", "utf-8"));
	    InternetAddress[] address = {new InternetAddress(to, name, "utf-8")};
	    msg.setRecipients(javax.mail.Message.RecipientType.TO, address);
	    //msg.setSubject(subject, "utf-8");
	    msg.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
	    msg.setText(msgText1, "utf-8", "html");


	    // create and fill the first message part
	    // // MimeBodyPart mbp1 = new MimeBodyPart();
	    // // mbp1.setText(msgText1, "utf-8", "html");

	    // create the second message part
	    //MimeBodyPart mbp2 = new MimeBodyPart();

	    // attach the file to the message
	    //mbp2.attachFile(filename);

	    /*
	     * Use the following approach instead of the above line if
	     * you want to control the MIME type of the attached file.
	     * Normally you should never need to do this.
	     *
	    FileDataSource fds = new FileDataSource(filename) {
		public String getContentType() {
		    return "application/octet-stream";
		}
	    };
	    mbp2.setDataHandler(new DataHandler(fds));
	    mbp2.setFileName(fds.getName());
	     */

	    // create the Multipart and add its parts to it
	    // // Multipart mp = new MimeMultipart();
	    // // mp.addBodyPart(mbp1);
	    //mp.addBodyPart(mbp2);

	    // add the Multipart to the message
	    //msg.setContent(mp);

	    // set the Date: header
	    msg.setSentDate(new Date());

	    /*
	     * If you want to control the Content-Transfer-Encoding
	     * of the attached file, do the following.  Normally you
	     * should never need to do this.
	     *
	    msg.saveChanges();
	    mbp2.setHeader("Content-Transfer-Encoding", "base64");
	     */

	    // send the message
	    Transport.send(msg);
	}
	
	/*
	public void imapSend(String email, String name, String title, String message) {

		String to = email;
		String from = emailSender;
		String host = emailServer;
		//String filename = args[3];
		String msgText1 = message;
		String subject = title;
		
		// create some properties and get the default Session
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "imap");
		props.put("mail.imap.port", emailPort);
		//props.put("mail.smtp.host", host);
		//props.put("mail.smtp.auth", "true");

		
		//Authenticator authenticator = new MyAuthenticator();
		
		Session session = Session.getInstance(props, null);
		session.setDebug(debug);
		
		
		try {
			
			Store store = session.getStore("imaps") ;
			store.connect(emailSender, emailPassword) ;
			Folder inbox = store.getFolder("INBOX");
			
		    // create a message
		    MimeMessage msg = new MimeMessage(session);
		    msg.setContent(message, "text/html; charset=\"utf-8\"");
		    msg.setFrom(new InternetAddress(from, "CopyPrism", "utf-8"));
		    InternetAddress[] address = {new InternetAddress(to, name, "utf-8")};
		    msg.setRecipients(Message.RecipientType.TO, address);
		    msg.setSubject(subject);

		    // create and fill the first message part
		    MimeBodyPart mbp1 = new MimeBodyPart();
		    mbp1.setText(msgText1);

		    // create the second message part
		    //MimeBodyPart mbp2 = new MimeBodyPart();

		    // attach the file to the message
		    //mbp2.attachFile(filename);

		    /*
		     * Use the following approach instead of the above line if
		     * you want to control the MIME type of the attached file.
		     * Normally you should never need to do this.
		     *
		    FileDataSource fds = new FileDataSource(filename) {
			public String getContentType() {
			    return "application/octet-stream";
			}
		    };
		    mbp2.setDataHandler(new DataHandler(fds));
		    mbp2.setFileName(fds.getName());
		     * /

		    // create the Multipart and add its parts to it
		    Multipart mp = new MimeMultipart();
		    mp.addBodyPart(mbp1);
		    //mp.addBodyPart(mbp2);

		    // add the Multipart to the message
		    msg.setContent(mp);

		    // set the Date: header
		    msg.setSentDate(new Date());

		    /*
		     * If you want to control the Content-Transfer-Encoding
		     * of the attached file, do the following.  Normally you
		     * should never need to do this.
		     *
		    msg.saveChanges();
		    mbp2.setHeader("Content-Transfer-Encoding", "base64");
		     * /

		    // send the message
		    Transport.send(msg);
		    
		} catch (MessagingException mex) {
		    mex.printStackTrace();
		    Exception ex = null;
		    if ((ex = mex.getNextException()) != null) {
			ex.printStackTrace();
		    }
		} catch (Exception ioex) {
		    ioex.printStackTrace();
		}
	}
	*/
	
	/*
	 *  pop3  메일 수신 함수 
	 * @param hostname	pop3  메일 수신 서버 호스트
	 * @param port		pop3  메일 수신  port
	 * @param user		pop3  메일 수신 인증 아이디 
	 * @param password	pop3  메일 수신 인증 암호
	 * @param ssl		pop3  메일  ssl  보안 여부
	 * @return 수신된 메일 곤텐츠 어레이
	 */
	public ArrayList<MailContent> pop3Receive(String hostname, String port, String user, String password, String ssl) {

        Folder folder = null;
        Store store = null;
        ArrayList<MailContent> mailContentArr = new ArrayList<MailContent>() ;

        try {
            Properties props = System.getProperties();
            props.put("mail.store.protocol", "pop3");
            props.put("mail.pop3.host", hostname) ;
            props.put("mail.pop3.user", user) ;
            props.put("mail.pop3.password", password) ;
            props.put("mail.pop3.auth", "true") ;
            props.put("mail.pop3.port", ( port!=null && port.length()>0 ) ? port:"995");
            if( ssl != null && ssl.equals("ssl") )
                props.setProperty("mail.pop3.ssl.enable", "true");

            Authenticator authenticator = new MyAuthenticator(user,password);
            Session session = Session.getDefaultInstance(props, authenticator);
            // session.setDebug(true);
            store = session.getStore("pop3");
            store.connect();
            folder = store.getFolder("Inbox");
            /* Others GMail folders :
             * [Gmail]/All Mail   This folder contains all of your Gmail messages.
             * [Gmail]/Drafts     Your drafts.
             * [Gmail]/Sent Mail  Messages you sent to other people.
             * [Gmail]/Spam       Messages marked as spam.
             * [Gmail]/Starred    Starred messages.
             * [Gmail]/Trash      Messages deleted from Gmail.
             */
            folder.open(Folder.READ_WRITE);

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -beforeDays);
            theDay = new Date(cal.getTimeInMillis()) ;

            javax.mail.Message[] messages = folder.getMessages() ;
            if( folder.getUnreadMessageCount() == 0 )
                return mailContentArr ;

            /* Use a suitable FetchProfile    * /
            FetchProfile fp = new FetchProfile();
            fp.add(FetchProfile.Item.ENVELOPE);
            fp.add(FetchProfile.Item.CONTENT_INFO);
            folder.fetch(messages, fp);
            */

            System.out.println("No of Messages : " + folder.getMessageCount());
            System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());

            for (int i = messages.length-1 ; i >= 0 ; i--) {
                System.out.println("MESSAGE #" + (i + 1) + ":");
                javax.mail.Message msg = messages[i];

                 //if we don''t want to fetch messages already processed

                 if (msg.getSentDate().getTime() < theDay.getTime() ) {
                     //System.out.println(msg.getReceivedDate().toString()) ;
                	//System.out.println(msg.getSentDate().toString()) ;
                     break ;
                 }

                String subject = msg.getSubject();

                String from = "unknown";
                if (msg.getReplyTo().length >= 1) {
                    from = msg.getReplyTo()[0].toString();
                } else if (msg.getFrom().length >= 1) {
                    from = msg.getFrom()[0].toString();
                }


                if( msg.getSentDate().getTime() == lastTime &&
                    makeMD5(from + subject).equals(lastSubjectHash) ) {
                    break ;
                }

                if (msg.isSet(Flags.Flag.SEEN)) {
                   continue ;
                }


                this.mailContent = new MailContent() ;
                this.mailContent.sentDate = msg.getSentDate() ;
                this.mailContent.hash = makeMD5(from + subject) ;

                if( from.startsWith("=?") ) {

                    from = decodeToUtf8(from) ;
                }


                if( subject.startsWith("=?") )
                    subject = decodeToUtf8(subject) ;

                System.out.println("Saving ... " + subject + " " + from);


                this.mailContent.from = from ;
                this.mailContent.subject = subject ;


                saveParts(msg);
                msg.setFlag(Flags.Flag.SEEN, true);
                // to delete the message
                // msg.setFlag(Flags.Flag.DELETED, true);

                mailContentArr.add(this.mailContent) ;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if (folder != null) {
                try { folder.close(true); } catch(Exception e){}
            }
            if (store != null) {
                try { store.close(); } catch(Exception e){}
            }

            return mailContentArr ;
        }
    }

          
	/*
	 *  imap 이메일 수신 함수
	 * @param hostname	imap 이메일 수신 호스트
	 * @param port		imap 이메일 수신 포트
	 * @param user		imap 이메일 수신 인증 아이디
	 * @param password	imap 이메일 수신 인증 암호
	 * @param ssl		imap 이메일 수신 ssl 보안 여부
	 * @return
	 */
    public ArrayList<MailContent> imapReceive(String hostname, String port, String user, String password, String ssl){

        Folder folder = null;
        Store store = null;
        ArrayList<MailContent> mailContentArr = new ArrayList<MailContent>() ;

        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imap");
            props.put("mail.imap.host", hostname) ;
            props.put("mail.imap.user", user) ;
            props.put("mail.imap.password", password) ;
            props.setProperty("mail.imap.auth", "true");
            props.setProperty("mail.imap.port", ( port!=null && port.length()>0 ) ? port:"993");
            if( ssl != null && ssl.equals("ssl") )
                props.setProperty("mail.imap.ssl.enable", "true");

            Authenticator authenticator = new MyAuthenticator(user, password);
            Session session = Session.getDefaultInstance(props, authenticator);
            // session.setDebug(true);
            store = session.getStore("imap");
            store.connect();
            folder = store.getFolder("Inbox");
            /* Others GMail folders :
             * [Gmail]/All Mail   This folder contains all of your Gmail messages.
             * [Gmail]/Drafts     Your drafts.
             * [Gmail]/Sent Mail  Messages you sent to other people.
             * [Gmail]/Spam       Messages marked as spam.
             * [Gmail]/Starred    Starred messages.
             * [Gmail]/Trash      Messages deleted from Gmail.
             */
            folder.open(Folder.READ_WRITE);

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -beforeDays);
            theDay = new Date(cal.getTimeInMillis()) ;

            if( ! folder.hasNewMessages() )
                return mailContentArr ;

            //Message messages[] = folder.search(searchCondition);

            FlagTerm fterm = new FlagTerm(new Flags(Flag.SEEN), false) ;
            //ReceivedDateTerm rterm = ReceivedDateTerm( ReceivedDateTerm.GE, theDay) ;
            javax.mail.Message messages[] = folder.search(fterm);

            /* Use a suitable FetchProfile    */
            FetchProfile fp = new FetchProfile();
            fp.add(FetchProfile.Item.ENVELOPE);
            //fp.add(FetchProfile.Item.CONTENT_INFO);
            fp.add(FetchProfile.Item.FLAGS);
            folder.fetch(messages, fp);

            System.out.println("No of Messages : " + folder.getMessageCount());
            System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
            for (int i = messages.length-1 ; i >= 0 ; i--) {
                System.out.println("MESSAGE #" + (i + 1) + ":");
                javax.mail.Message msg = messages[i];

                if (msg.getReceivedDate().getTime() < theDay.getTime() ) {
                    break ;
                }
                
                String subject = msg.getSubject();

                String from = "unknown";
                if (msg.getReplyTo().length >= 1) {
                    from = msg.getReplyTo()[0].toString();
                } else if (msg.getFrom().length >= 1) {
                    from = msg.getFrom()[0].toString();
                }

                if( msg.getSentDate().getTime() == lastTime &&
                    makeMD5(from + subject).equals(lastSubjectHash) ) {
                    break ;
                }

                if (msg.isSet(Flags.Flag.SEEN)) {
                   continue ;
                }

                this.mailContent = new MailContent() ;
                this.mailContent.hash = makeMD5(from + subject) ;
                this.mailContent.sentDate =  msg.getSentDate() ;

                if( from.startsWith("=?") ) {

                    from = decodeToUtf8(from) ;
                }


                if( subject.startsWith("=?") )
                    subject = decodeToUtf8(subject) ;

                System.out.println("Saving ... " + subject + " " + from);

                this.mailContent.from = from ;
                this.mailContent.subject = subject ;

                saveParts(msg);
                msg.setFlag(Flags.Flag.SEEN, true);
                // to delete the message
                // msg.setFlag(Flags.Flag.DELETED, true);

                mailContentArr.add(this.mailContent) ;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if (folder != null) {
                try { folder.close(true); } catch(Exception e){}
            }
            if (store != null) {
                try { store.close(); } catch(Exception e){}
            }

            return mailContentArr ;
        }
    }
    
    /*
     * 아규먼트 스트링을  UTF8  형식으로 변환
     * @param str   입력 아규먼트 스트링
     * @return	 변환된  UTF8  스트링
     */
    public String decodeToUtf8(String str) {

        String utf8 = "" ;

        Pattern p = Pattern.compile("(=\\?)(.*?)(\\?=)");
        // 입력 문자열과 함께 매쳐 클래스 생성
        Matcher m = p.matcher(str);

        StringBuffer sb = new StringBuffer();
        boolean result = m.find();
        int last = 0 ;
        // 패턴과 일치하는 문자열을 ‘dog’으로 교체해가며 새로운 문자열을 만든다.
        while (result) {
          String pattern = m.group() ;
          utf8 += decode7bitStrToUtf8(pattern) ;
          last = m.end() ;
          result = m.find();
        }

        utf8 += str.substring(last) ;

        return utf8 ;
    }
   
    /*
     * 7bit  스트링을  utf8  스트링으로 변환 
     * @param str  7bit  스트링
     * @return  변환된  utf8 스트링
     */
    public String decode7bitStrToUtf8(String str) {

        str = str.substring(2, str.length() - 2);
        String utf8 = str ;
        try {

            //extension = extension.replaceAll("=+\\?=*", "") ;
            String[] fields = str.split("\\?");

            if (fields == null || fields.length != 3) {
                return str;
            }

            if (fields[1].equals("B")) {
                sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
                utf8 = new String(decoder.decodeBuffer(fields[2]), fields[0]);
            }
            else if (fields[1].equals("Q")) {
                utf8 = new String(QuotedPrintableCodec.decodeQuotedPrintable(fields[2].getBytes()), fields[0]);
            }
        }
        catch(Exception e) {
          	e.printStackTrace();
        }
        finally {      
            return utf8;
        }
    }
    
    
    /*
     * 수신한 이메일의  Body Part  저장(첨부파일 포함)
     * @param  part  수신한 이메일의  MimeBodyPart
     * @return  정상 저장 여부  true/false
     */
    public boolean saveBody(MimeBodyPart part) {

        String extension = "" ;
        String filename = remailTmp + UUID.randomUUID() ;

        try {

            if( Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition()) ){
                //  Try to get the name of the attachment
                //extension = part.getDataHandler().getName();
                extension = part.getFileName() ;
                if( extension == null || extension.trim().length() == 0 )
                    return false ;

                if( extension.startsWith("=?") ) {

                    if( (extension = decodeToUtf8(extension)) == null )
                        return false ;
                }

                filename = filename + "." + extension;
                System.out.println("... " + filename);



                FileInfo fileInfo = new FileInfo() ;
                fileInfo.filename = extension ;
                fileInfo.file = new File(filename) ;

                part.saveFile(fileInfo.file);

                this.mailContent.attachArr.add(fileInfo) ;

            }
            else if (part.isMimeType("text/plain")) {
                extension = "txt";
                Object obj = part.getContent() ;
                if( obj instanceof String ) {
                    String charset = null ;
                    String body = decodeUniversial(part) ;
                    System.out.println("[body]" + body);
                    this.mailContent.body = "\n" + body ;
                }
            }
            else if (part.isMimeType("text/html")) {
                extension = "html";
                Object obj = part.getContent() ;
                if( obj instanceof String ) {
                    String body = decodeUniversial(part) ;
                    body = body.replaceAll("<meta([^>])*charset([^>])*>",
                                           "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">") ;
                    body = body.replaceAll("<META([^>])*charset([^>])*>",
                                           "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">") ;
                    body = body.replaceAll("<Meta([^>])*charset([^>])*>",
                                           "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">") ;
                    System.out.println("[body]" + body);
                    this.mailContent.body = "\n" + body ;
                }
            }
            else if( part.getContent() instanceof String ) {
                String charset = null ;
                String body = decodeUniversial(part) ;
                System.out.println("[body]" + body);
                this.mailContent.body = "\n" + body ;
            }
            else {
                return false ;
            }
        }
        catch(Exception ee){
            ee.printStackTrace();
            return false ;
        }

        return true;
    }
    
    /*
     * 수신한 이메일의 바디를  Universial  스트링으로로 변환
     * @param part 수신한 이메일의  MimeBodyPart
     * @return 이메일의 바디를 변환한 Universial  스트링 
     * @throws Exception
     */
    public String decodeUniversial(MimeBodyPart part) throws Exception{

        String body = null ;
        File tmpFile = new File("/Users/mgkaki/Temp/JavaMail/" + UUID.randomUUID() + ".txt") ;
        part.saveFile(tmpFile);

        InputStream is = new FileInputStream(tmpFile) ;
        byte[] buf = new byte[1024] ;
        ByteArrayOutputStream bo = new ByteArrayOutputStream() ;

        try {
            int ret = 0 ;
            int total = 0 ;
            while( (ret=is.read(buf)) > 0 ) {

                bo.write(buf, 0, ret);
                total += ret ;
            }
            is.close() ;
            is = null ;
            tmpFile.delete() ;
            bo.flush();

            nsDetector det = new nsDetector(nsPSMDetector.ALL) ;

            det.Init(new nsICharsetDetectionObserver() {
                    public void Notify(String charset) {
                        HtmlCharsetDetector.found = true ;
                        System.out.println("CHARSET = " + charset);
                    }
            });

            System.out.println("detecting start");
            det.DoIt(bo.toByteArray(),total, false) ;
            det.DataEnd();
            System.out.println("detecting end");

            String[] charsets = det.getProbableCharsets() ;

            if (! det.getProbableCharsets()[0].equals("nomatch") &&
                ! det.getProbableCharsets()[0].equals("UTF-8") ){

                String charset = det.getProbableCharsets()[0] ;
                for(int i=0 ; det.getProbableCharsets()!=null &&  i<det.getProbableCharsets().length ; i++){
                	if( det.getProbableCharsets()[i].equals("UTF-8") ) {
                        charset = "UTF-8" ;
                        break ;
                    }
                	else if( det.getProbableCharsets()[i].equals("EUC-KR") ) {
                        charset = "EUC-KR" ;
                        break ;
                    }
                }
                System.out.println("charset : " + charset);

                body = new String(bo.toByteArray(),0, total, charset) ;

                    System.out.println("[body] : " + body);


            }
            else {
                body = new String(bo.toByteArray(),0, total, "utf-8") ;
            }
        }
        finally {
            if( is != null ) is.close() ;
            if( bo != null ) bo.close() ;
            return body ;
        }     
    }
    
    /*
     *  Universial  스트링으로 변환된 이메일 바디 스트링을 원래의 스트링으로 복원
     * @param body Universial  스트링
     * @return  Universail 스트링을 복원(decoding)한 원 스트링
     */
    public String decodeUniversial(String body) {

        nsDetector det = new nsDetector(nsPSMDetector.ALL) ;

        det.Init(new nsICharsetDetectionObserver() {
                public void Notify(String charset) {
                    HtmlCharsetDetector.found = true ;
                    System.out.println("CHARSET = " + charset);
                }
        });

        System.out.println("detecting start");
        det.DoIt(body.getBytes(),body.getBytes().length, false) ;
        det.DataEnd();
        System.out.println("detecting end");

        String[] charsets = det.getProbableCharsets() ;

        if (! det.getProbableCharsets()[0].equals("nomatch") &&
            ! det.getProbableCharsets()[0].equals("UTF-8") ){

            String charset = det.getProbableCharsets()[0] ;

            for(int i=0 ; det.getProbableCharsets()!=null &&  i<det.getProbableCharsets().length ; i++){
                if( det.getProbableCharsets()[i].equals("EUC-KR") ) {
                    charset = "EUC-KR" ;
                    break ;
                }
                else if( det.getProbableCharsets()[i].equals("UTF-8") ) {
                    charset = "UTF-8" ;
                    break ;
                }
            }

            System.out.println("charset : " + charset);

            try {
                body = new String(body.getBytes(charset), "utf-8") ;

                System.out.println("[body] : " + body);
            }
            catch(Exception e) {
                return null ;
            }
        }

        return body ;
    }
   
    
    /*
     * 수신한 이메일 메세지를 MimeBodyPart  형식으로 저장
     */
    public void saveParts(Object message) throws Exception {

        try {
            Object content = null ;

            if( message instanceof Message )
                content = ((javax.mail.Message)message).getContent() ;
            else
                content = message ;

            if (content instanceof Multipart) {

                Multipart multi = ((Multipart) content);
                int parts = multi.getCount();

                for (int j = 0; j < parts; ++j) {

                    MimeBodyPart part = (MimeBodyPart) multi.getBodyPart(j);

                    if (part.getContent() instanceof Message) {
                        // if Message, do some recursion...
                        saveParts(part.getContent());
                    }
                    else if (part.getContent() instanceof Multipart) {
                        // if part-within-a-part, do some recursion...
                        saveParts(part.getContent());
                    } else {

                        saveBody(part) ;
                    }
                }
            }
            else if( content instanceof String ) {
                String filename = "/Users/mgkaki/Temp/JavaMail/" + UUID.randomUUID() ;
                String body = content.toString() ;
                body = decodeUniversial(body) ;
                this.mailContent.body += body ;
            }
        }
        finally {

        }
    }
    
    /*
     *  make md5 code
     *  @param  strKey   입력 아규먼트 스트링
     *  @return  md5 암호코드로 변환된 스트링
     */
    public static String makeMD5(final String strKey) {
        byte [] bDigest = null;
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(strKey.getBytes());
            bDigest = md.digest();
            String strCode = new BigInteger(1, bDigest).toString(16);
            return strCode ;
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null ;
        }
    }
    
    /*
     *  파일의 내용을 md5  해쉬 값으로 변환
     *  @param   file   입력 파일 아규먼트
     *  @return  md5 암호코드로 변환된 스트링
     */
    public static String makeMD5(File file) {

        InputStream is = null ;
        DigestInputStream dis = null ;
        try {
            byte[] buf = new byte[8096] ;
            MessageDigest md = MessageDigest.getInstance("MD5");
            is = new FileInputStream(file) ;
            dis = new DigestInputStream(is, md);
            /* Read stream to EOF as normal... */
            while( dis.read(buf) > 0 ) {
             
            }
            
            dis.close();
            is.close();
            
            byte [] bDigest = md.digest();
            String strCode = new BigInteger(1, bDigest).toString(16);
            return strCode ;
        }
        catch(Exception e) {

            if( dis != null )  try { dis.close(); } catch(Exception ee){} ;
            if( is  != null )  try { is.close() ;} catch(Exception ee){} ;
            return null ;
        }
    }
    
    /*
     * 마지막 수신한 이메일 이후의 최근 이메일을 수신하는 함수
     * @mailLastTime  마지막 이메일 수신 시각 
     * @mailLastHash  마지막 수신 이메일 해쉬 값
     * @return  수신한 최근 이메일 콘텐츠 리스트
     */
	public ArrayList<MailContent> fetch(long mailLastTime, String mailLastHash) {

		ArrayList<MailContent> mailContentArr = null ;
        try {

            this.beforeDays = 3 ;

            
            //Mail mail = new Mail() ;
            if( remailType.equals("imap") ) {

                this.lastTime = mailLastTime ;
                this.lastSubjectHash = mailLastHash ;
                mailContentArr = this.imapReceive(remailServer, remailPort, remailUser, remailPassword, remailSsl) ;
            }
            else if( remailType.equals("pop3") ) {

                this.lastTime = mailLastTime  ;
                this.lastSubjectHash = mailLastHash ;
                mailContentArr = this.pop3Receive(remailServer, remailPort, remailUser, remailPassword, remailSsl) ;
            }

            if( mailContentArr!=null && mailContentArr.size()>0) {
            	this.lastTime = mailContentArr.get(0).sentDate.getTime() ;
            	this.lastSubjectHash = mailContentArr.get(0).hash ;
                //setLastMail(mailContentArr.get(0).sentDate.getTime(), mailContentArr.get(0).hash) ;
                
                /////###insertMailContentToDB(user.id, mailContentArr, check) ;
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
        	return mailContentArr ;
        }
    }
	
	public static void main(String[] argv) {
		
		BufferedReader br = null ;
		try {
			File file = new File(argv[0]) ;
			if( ! file.exists() ) {
				System.out.println("전송할 메일 입력 파일이 존재하지 않습니다.") ;
				return ;
			}
			
			br = new BufferedReader(new FileReader(file)) ;
			String receiverEmail = "mgkaki@youngplussoft.com" ;
			String receiverName = "강명구" ;
			String emailTitle = "Test" ;
			String emailContent = "" ;
			
			boolean isContent = false ;
			String buf = null ;
			while( (buf = br.readLine()) != null ) {
				
				emailContent += buf + "\n" ;
				
				/*
				if( isContent ) {
					emailContent += buf + "\n" ;
				}
				else {
					String fields[] = buf.split(":") ;
					if( !(fields.length == 1 && fields[0].trim().equals("emailContent") ) &&
						 fields.length < 2 )
						continue ;
					
					if( fields[0].trim().equals("receiverEmail") ) {
						receiverEmail = buf.substring(fields[0].length() + 1).trim() ;
					}
					else if( fields[0].trim().equals("receiverName") ) {
						receiverName = buf.substring(fields[0].length() + 1).trim() ;
					}
					else if( fields[0].trim().equals("emailTitle") ) {
						emailTitle = buf.substring(fields[0].length() + 1).trim() ;
					}
					else if( fields[0].trim().equals("emailContent") ) {
						emailContent = buf.substring(fields[0].length() + 1) ;
						isContent = true ;
					}					
				}
				*/
			}
			
			br.close() ;
			br = null ;
			
			if( receiverEmail == null || emailContent == null ) {
				System.out.println("Email 메세지의 수신자 주소 또는 내용이 없습니다") ;
				return ;
			}
				
			Mail.getInstance().smtpSend(receiverEmail, receiverName, emailTitle, emailContent) ;	
			
			if( argv.length > 1 && argv[1].equals("-removeFile") )
				file.delete() ;
		}
		catch(Exception e) {
			e.printStackTrace() ;
			if( br != null ) try { br.close(); } catch(Exception ee) {} 
		}
	}
}
