package com.test.quota.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static String get(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            // 获取响应实体
            HttpEntity entity = response.getEntity();

            // 打印响应内容
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
            return null;
        }
    }

    public static String post(String url, String body) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        StringEntity stringEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            // 获取响应实体
            HttpEntity entity = response.getEntity();

            // 打印响应内容
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
            return null;
        }
    }
}
