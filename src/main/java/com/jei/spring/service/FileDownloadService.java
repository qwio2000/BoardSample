package com.jei.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownloadService extends AbstractView{
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		File file = (File)model.get("downloadFile");
        String fileOriginalName = (String)model.get("fileOriginalName");
        res.setContentType(getContentType());
        res.setContentLength((int)file.length());
        String fileName = fileOriginalName;
        String browser = getBrowser(req);
        if (browser.contains("MSIE")) {
            fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", " ");
        } else if (browser.contains("Firefox")) {
               fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        } else if (browser.contains("Opera")) {
               fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        } else if (browser.contains("Chrome")) {
               fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        res.setHeader("Content-Type", "application/octet-stream");
        res.setHeader("Pragma", "no-cache;");
        res.setHeader("Expires", "-1;");
        res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        res.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = res.getOutputStream();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fis != null){
                try{
                    fis.close();
                }catch(Exception e){}
            }
        }
        out.flush();
	}
	/**
	 * 브라우저 정보를 가져오는 메서드
	 * @param request
	 */
	private String getBrowser(HttpServletRequest request) {
        String header =request.getHeader("User-Agent");
        if (header.contains("MSIE")||header.contains("Trident/7.0")) {
               return "MSIE";
        } else if(header.contains("Chrome")) {
               return "Chrome";
        } else if(header.contains("Opera")) {
               return "Opera";
        }
        return "Firefox";
  }
}
