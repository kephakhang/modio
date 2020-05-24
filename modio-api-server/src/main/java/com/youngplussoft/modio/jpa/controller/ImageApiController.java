package com.youngplussoft.modio.jpa.controller;

import com.youngplussoft.common.KeyGenerator;
import com.youngplussoft.common.Message;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ImageApiController extends ApiBaseController{


    @Resource
    ServletContext context;

    @CrossOrigin
    @GetMapping("/image/download/{id}/")
    public void download(HttpServletResponse response,
            @PathVariable("id") String id) {

        Document image = imageTemplate.findById(new ObjectId(id)) ;
        String path = imageLocation + "/" + image.getString("yyyymm") + "/" + image.getString("uuid") + image.getString("ext") ;
        InputStream is = null ;
        OutputStream os = null ;
        try {
            File downloadFile = new File(path);
            is = new FileInputStream(downloadFile);

            // gets MIME type of the file
            String mimeType = context.getMimeType(path);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);

            // modifies response
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());

            // forces download
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);

            // obtains response's output stream
            os = response.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
        catch(Exception e){
            LOGGER.error(Message.exceptionToString(e)) ;
        }
        finally {
            try { if( is != null ) is.close(); } catch(Exception e){}
            try { if( os != null ) os.close(); } catch(Exception e){}
        }
    }

    @CrossOrigin
    @PostMapping(apiBasePath+"/image/upload")
    public ResponseEntity<Object> upload(
            @RequestParam("file") MultipartFile file) {

        try {

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

            //입력된 파일 명을 추출
            String filename = file.getOriginalFilename();

            //파일 이름에서 뒤에 3자리를 읽어온다(확장자 추출)
            String ext = filename.substring(filename.lastIndexOf('.'), filename.length()).toLowerCase();
            String fname = filename.substring(0, filename.lastIndexOf('.')) ;
            String uuid = null;
            String fpath = null;

            if(ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".gif") || ext.equals(".png")) {

                Document image = new Document();
                image.put("share", false);
                image.put("fname", fname);
                uuid = KeyGenerator.generateKey();
                image.put("uuid", uuid);
                image.put("ext", ext);
                image.put("yyyymm", yyyymm);
                image.put("regtime", now);
                image.put("updtime", now);

                //파일 업로드
                fpath = (imageLocation+"/"+ yyyymm + "/" + uuid);
                File dest = new File(fpath + ext) ;
                file.transferTo(dest);        //file upload

                image.put("_id", new ObjectId()) ;
                try {
                    imageTemplate.insert(image);
                }
                catch(Exception ee){
                    throw new Exception(message.getMessage("error.image.insert")) ;
                }

                return new ResponseEntity<Object>(image.toJson(), HttpStatus.OK);

            }
            else {
                throw new Exception(message.getMessage("error.image.unknown.ext")) ;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            LOGGER.error(Message.exceptionToString(e)) ;
            return new ResponseEntity<Object>(message.getError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
