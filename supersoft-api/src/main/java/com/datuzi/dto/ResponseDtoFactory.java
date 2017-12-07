package com.datuzi.dto;

import com.datuzi.enums.RespCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ResponseDtoFactory
 *
 * @author
 * @date 2017/4/11
 */
public final class ResponseDtoFactory {

    private static final Logger logger = LoggerFactory.getLogger(ResponseDtoFactory.class);

    private ResponseDtoFactory() {
    }

    public static <T> ResponseDto get(ResponseDto responseDto, T data) {
        ResponseDto<T> dto = new ResponseDto<T>();
        dto.setCode(responseDto.getCode());
        dto.setMessage(responseDto.getMessage());
        dto.setData(data);
        dto.setSuccess(responseDto.isSuccess());
        return dto;
    }

    public static <T> ResponseDto<T> toSuccess(String rspMsg){
        return toSuccess(rspMsg, null);
    }

    public static <T> ResponseDto<T> toSuccess(T data){
        return toSuccess("", "", data);
    }

    public static <T> ResponseDto<T> toSuccess(String rspMsg, T data){
        return toSuccess("", rspMsg, data);
    }

    public static <T> ResponseDto<T> toSuccess(String code, String rspMsg, T data){
        ResponseDto<T> dto = new ResponseDto<T>();
        dto.setCode(RespCode.ACK.getName());
        dto.setMessage(rspMsg);
        dto.setData(data);
        dto.setSuccess(true);
        return dto;
    }

    public static <T> ResponseDto<T> toError(String rspMsg){
        return toError(rspMsg, null);
    }

    public static <T> ResponseDto<T> toError(String rspMsg, T data){
        return toError("", rspMsg, data);
    }

    public static <T> ResponseDto<T> toError(String code, String rspMsg, T data){
        ResponseDto<T> dto = new ResponseDto<T>();
        dto.setCode(RespCode.NACK.getName());
        dto.setMessage(rspMsg);
        dto.setData(data);
        dto.setSuccess(false);
        return dto;
    }

    public static <T> ResponseDto<T> unAuthorized(String rspMsg){
        ResponseDto<T> dto = new ResponseDto<T>();
        dto.setCode(RespCode.UNAUTHORIZED.getName());
        dto.setMessage(rspMsg);
        dto.setData(null);
        dto.setSuccess(false);
        return dto;
    }
}
