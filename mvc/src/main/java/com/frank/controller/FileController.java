package com.frank.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("file")
@Controller
public class FileController {

    @RequestMapping("/downloadByResponseEntity")
    public ResponseEntity<byte[]> fileDownload(HttpSession session) throws IOException {
        ServletContext servletContext = session.getServletContext();

        String path = "/static/img/pic010.png";

        String realPath = servletContext.getRealPath(path);

        InputStream is = new FileInputStream(new File(realPath));

        byte[] bytes = new byte[is.available()];

        is.read(bytes);

        MultiValueMap<String, String> headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment;filename=1.png");

        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);

        is.close();

        return response;
    };

    @RequestMapping("/downloadByResponse")
    public void fileDownload(HttpSession session, HttpServletResponse response) throws IOException {
        ServletContext servletContext = session.getServletContext();

        String path = "/static/img/pic010.png";

        String realPath = servletContext.getRealPath(path);

        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "attachment;filename=2.png");

        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(realPath));
                BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());) {
            IOUtils.copy(is, os);
        }

    }
    
    @RequestMapping(value="/fileUPload",method = RequestMethod.POST)
    public String fileUpLoad(MultipartFile photo,HttpSession session) throws IllegalStateException, IOException {
    	
        //獲取上傳的文件的文件名
        String fileName = photo.getOriginalFilename();
        //處理文件重名問題
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + hzName;
        //獲取服務器中photo目錄的路徑
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("photo/1/1/");
        File file = new File(photoPath);
        if(!file.exists()){
            file.mkdirs();//可以建立多層目錄
            file.mkdir();//只能建立一層目錄
        }
        String finalPath = photoPath + File.separator + fileName;
        //實現上傳功能
        photo.transferTo(new File(finalPath));
    	
    	return "success";
    }
}
