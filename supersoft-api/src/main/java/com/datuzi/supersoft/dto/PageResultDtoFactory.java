package com.datuzi.supersoft.dto;

import com.datuzi.supersoft.enums.RespCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ResponseDtoFactory
 *
 * @author
 * @date 2017/4/11
 */
public final class PageResultDtoFactory {

    private static final Logger logger = LoggerFactory.getLogger(PageResultDtoFactory.class);

    private PageResultDtoFactory() {
    }

    public static <T> PageResultDto get(PageResultDto responseDto, T data) {
        PageResultDto<T> dto = new PageResultDto<T>();
        dto.setCode(responseDto.getCode());
        dto.setMsg(responseDto.getMsg());
        dto.setData(data);
        return dto;
    }

    public static <T> PageResultDto<T> toSuccess(String rspMsg){
        return toSuccess(rspMsg, null, 0L);
    }

    public static <T> PageResultDto<T> toSuccess(T data,long count){
        return toSuccess("", "", data,count);
    }

    public static <T> PageResultDto<T> toSuccess(String rspMsg, T data, long count){
        return toSuccess("", rspMsg, data,count);
    }

    public static <T> PageResultDto<T> toSuccess(String code, String rspMsg, T data,long count){
        PageResultDto<T> dto = new PageResultDto<T>();
        dto.setCode(RespCode.ACK.getName());
        dto.setCount(count);
        dto.setMsg(rspMsg);
        dto.setData(data);
        return dto;
    }

    public static <T> PageResultDto<T> toError(String rspMsg){
        return toError(rspMsg, null);
    }

    public static <T> PageResultDto<T> toError(String rspMsg, T data){
        return toError("", rspMsg, data);
    }

    public static <T> PageResultDto<T> toError(String code, String rspMsg, T data){
        PageResultDto<T> dto = new PageResultDto<T>();
        dto.setCode(RespCode.NACK.getName());
        dto.setMsg(rspMsg);
        dto.setData(data);
        return dto;
    }
}
