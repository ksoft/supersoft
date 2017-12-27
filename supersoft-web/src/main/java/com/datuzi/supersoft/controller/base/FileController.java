package com.datuzi.supersoft.controller.base;

import com.datuzi.supersoft.config.ServerConfig;
import com.datuzi.supersoft.dto.ResponseDto;
import com.datuzi.supersoft.dto.ResponseDtoFactory;
import com.datuzi.supersoft.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangjianbo
 * @date 2017/12/27
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private ServerConfig config;

    /**
     * 修改
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<String> upload(MultipartFile file) {
        try {
            String path = "D:\\photos\\";
            String fileName = UploadUtil.upload(file, path);
            return ResponseDtoFactory.toSuccess("上传成功",config.getStaticPath()+fileName);
        }catch (Exception e){
            return ResponseDtoFactory.toError("上传失败");
        }
    }
}
