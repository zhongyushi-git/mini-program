package com.zys.miniprogramservive.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhongyushi
 * @date 2020/7/21 0021
 * @dec 使用apache的http协议发送请求
 */
@Component
public class HttpClient {

    private ConnectionConfig connectionConfig = ConnectionConfig.custom().setBufferSize(4128).build();
    private CloseableHttpClient httpClient = HttpClients.custom().setDefaultConnectionConfig(connectionConfig).build();

    /**
     * get请求,返回JSONObject
     */
    public JSONObject get(String url) {
        try {
            RequestBuilder builder = RequestBuilder.get(url);
            HttpUriRequest request = builder.build();
            return httpClient.execute(request, getResponseHandler());
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("msg", e.getMessage());
            return error;
        }
    }

    /**
     * post请求-返回JSONObject
     */
    public JSONObject post(String url, String json) {
        try {
            HttpPost httpPost = new HttpPost(url);
            //设置请求体
            if (json != null && json != "") {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(json, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            return httpClient.execute(httpPost, getResponseHandler());
        } catch (Exception e) {
            JSONObject error = new JSONObject();
            error.put("msg", e.getMessage());
            e.printStackTrace();
            return error;
        }
    }

    /**
     * 指定响应返回类型=JSONObject
     */
    private ResponseHandler<JSONObject> getResponseHandler() {
        ResponseHandler<JSONObject> handler = new ResponseHandler<JSONObject>() {
            public JSONObject handleResponse(HttpResponse response) throws IOException {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String json = EntityUtils.toString(entity, "UTF-8");
                    return JSON.parseObject(json);
                }
                return null;
            }
        };
        return handler;
    }
}
