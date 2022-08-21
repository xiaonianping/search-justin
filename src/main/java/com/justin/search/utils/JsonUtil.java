package com.justin.search.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.justin.search.model.KongLog;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

/**
 * @author ramzes_liu
 * @date 2021年08月05日
 */
public class JsonUtil {
    public static String getJsonValue(String key, JSONObject jsonObject) {
        if (ObjectUtils.isEmpty(jsonObject)) {
            return null;
        }
        Object objectValue = jsonObject.get(key);
        if (objectValue == null) {
            return null;
        }
        return objectValue.toString();
    }

    public static String getBodyData(HttpServletRequest request) throws IOException {
        StringBuilder data = new StringBuilder();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine())) {
                data.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert reader != null;
            reader.close();
        }
        return data.toString();
    }

    public static String getBodyDataByInputStream(HttpServletRequest request) throws IOException {
        ServletInputStream ris = request.getInputStream();
        StringBuilder content = new StringBuilder();
        byte[] b = new byte[1024];
        int lens = -1;
        while ((lens = ris.read(b)) > 0) {
            content.append(new String(b, 0, lens));
        }
        return content.toString();
    }


    public static KongLog buildData(String bodyData) {
        KongLog kongLog = new KongLog();
        JSONObject jsonObject = JSON.parseObject(bodyData);
        String requestStr = JsonUtil.getJsonValue("request", jsonObject);
        String clientIp = JsonUtil.getJsonValue("client_ip", jsonObject);
        String response = JsonUtil.getJsonValue("response", jsonObject);
        JSONObject requestJson = JSON.parseObject(requestStr);
        JSONObject responseJson = JSON.parseObject(response);
        String method = JsonUtil.getJsonValue("method", requestJson);
        String requestSize = JsonUtil.getJsonValue("size", requestJson);
        String body = JsonUtil.getJsonValue("body", requestJson);
        String url = JsonUtil.getJsonValue("url", requestJson);
        String querystring = JsonUtil.getJsonValue("querystring", requestJson);
        String statusCode = JsonUtil.getJsonValue("status", responseJson);
        String responseBody = JsonUtil.getJsonValue("body", responseJson);

        String startedAt = JsonUtil.getJsonValue("started_at", jsonObject);
        String upstreamUri = JsonUtil.getJsonValue("upstream_uri", jsonObject);
        String routeStr = JsonUtil.getJsonValue("route", jsonObject);
        String serviceStr = JsonUtil.getJsonValue("service", jsonObject);
        JSONObject route = JSON.parseObject(routeStr);
        JSONObject service = JSON.parseObject(serviceStr);
        String routeName = JsonUtil.getJsonValue("name", route);
        String serviceName = JsonUtil.getJsonValue("name", service);

        String headersStr = JsonUtil.getJsonValue("headers", requestJson);
        JSONObject headers = JSON.parseObject(headersStr);
        String traceId = JsonUtil.getJsonValue("kong-request-id", headers);
        String contentLength = JsonUtil.getJsonValue("content-length", headers);
        String userAgent = JsonUtil.getJsonValue("user-agent", headers);

        String consumerStr = JsonUtil.getJsonValue("consumer", jsonObject);
        JSONObject consumer = JSON.parseObject(consumerStr);
        String customId = JsonUtil.getJsonValue("custom_id", consumer);

        String latenciesStr = JsonUtil.getJsonValue("latencies", jsonObject);
        JSONObject latencies = JSON.parseObject(latenciesStr);
        String requestLatency = JsonUtil.getJsonValue("request", latencies);
        String kongLatency = JsonUtil.getJsonValue("kong", latencies);
        String proxyLatency = JsonUtil.getJsonValue("proxy", latencies);

        String triesStr = JsonUtil.getJsonValue("tries", jsonObject);
        JSONArray objects = JSONObject.parseArray(triesStr);
        if (!ObjectUtils.isEmpty(objects)) {
            int size = objects.size();
            kongLog.setRetryCount(size);
        }

        kongLog.setStartedAt(StringUtils.hasText(startedAt) ? new Date(Long.parseLong(startedAt)) : null);
        kongLog.setRouteName(routeName);
        kongLog.setServiceName(serviceName);
        kongLog.setUpstreamUri(upstreamUri);
        kongLog.setRequestSize(checkNumber(requestSize));
        kongLog.setContentLength(checkNumber(contentLength));
        kongLog.setUserAgent(userAgent);
        kongLog.setQueryString(querystring);
        kongLog.setRequestMethod(method);
        if (ObjectUtils.isEmpty(statusCode)) {
            kongLog.setStatus(0);
        } else {
            kongLog.setStatus(Integer.parseInt(statusCode));
        }
        kongLog.setRequestUrl(url);
        kongLog.setRemoteAddr(clientIp);
        kongLog.setTraceId(traceId);
        kongLog.setCreateDate(new Date());
        kongLog.setRequestBody(body);
        kongLog.setResponseBody(responseBody);
        kongLog.setCustomId(customId);
        kongLog.setKongLatency(checkNumber(kongLatency));
        kongLog.setProxyLatency(checkNumber(proxyLatency));
        kongLog.setRequestLatency(checkNumber(requestLatency));
        return kongLog;
    }


    private static Integer checkNumber(String param) {
        if (ObjectUtils.isEmpty(param)) {
            return null;
        } else {
            return Integer.parseInt(param);
        }
    }
}
