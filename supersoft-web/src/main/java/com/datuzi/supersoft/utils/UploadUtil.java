package com.datuzi.supersoft.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author zhangjianbo
 * @date 2017/12/27
 */
public class UploadUtil {
    public static String upload(MultipartFile file,String path) throws Exception{
        String fileName=file.getOriginalFilename();
        String suffixName=fileName.substring(fileName.lastIndexOf("."));
        File tempfile=new File(path, UUID.randomUUID().toString()+suffixName);
        tempfile.createNewFile();
        file.transferTo(tempfile);
        return tempfile.getName();
    }
}
