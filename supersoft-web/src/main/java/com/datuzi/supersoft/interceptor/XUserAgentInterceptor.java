package com.datuzi.supersoft.interceptor;

import com.datuzi.supersoft.utils.Base64Util;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

/**
 * @author zhangjianbo
 * @date 2017/5/10
 */
public class XUserAgentInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        requestWrapper.getHeaders().add("Authorization","Basic "+ Base64Util.getBase64("serviceuser:123456"));
        return execution.execute(request, body);
    }
}
