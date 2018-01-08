package com.datuzi.supersoft.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author zhangjianbo
 * @date 2017/12/27
 */
public class UploadUtil {
    public static String upload(MultipartFile file,String path,Boolean showPath) throws Exception{
        String fileName=file.getOriginalFilename();
        String suffixName=fileName.substring(fileName.lastIndexOf("."));
        String fileFinalName=UUID.randomUUID().toString()+suffixName;
        File tempfile=new File(path, fileFinalName);
        tempfile.createNewFile();
        file.transferTo(tempfile);
        if(showPath!=null && showPath){
            return tempfile.getName();
        }else{
            return fileFinalName;
        }
    }
}
