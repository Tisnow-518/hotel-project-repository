package com.abcd.hotel.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * 文件上传工具类
 */
@Slf4j
public class FileUtils {

    public static final String UPLOAD_PATH = "d:\\hotelupload";

    /**
     * 上传文件存储
     */
    public static String uploadFile(MultipartFile file) throws Exception{

        //获得文件后缀
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);

        //生成uuid文件名
        String newFileName = UUID.randomUUID().toString().replace("-","")+"."+ suffix;

        log.info("upload file new name: "+newFileName);

        File destFile = new File(UPLOAD_PATH, newFileName);
        file.transferTo(destFile);

        String url = "/uploads/"+newFileName;

        log.info("upload file url: "+url);

        return url;

    }

    public static boolean removeFile(String fileName) throws Exception{

        File file = new File(UPLOAD_PATH+"\\"+fileName);
        boolean result = file.delete();

        return result;

    }

}
