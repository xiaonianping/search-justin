package com.justin.search.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.justin.search.mapper.KongLogMapper;
import com.justin.search.model.KongLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class KongLogService {

    @Autowired
    private KongLogMapper kongLogMapper;

    @Async
    public void saveRequestLog(HttpServletRequest request) {
        String bodyData = null;
        try {
            bodyData = getBodyData(request);
            KongLog kongLog = buildData(bodyData);
            kongLog.setLogMessage(bodyData);
            kongLogMapper.insertSelective(kongLog);
        } catch (Exception e) {
            log.error("body:{},error:{}", bodyData, e.getMessage(), e);
        }
    }

    private KongLog buildData(String bodyData) {
        KongLog kongLog = new KongLog();
        JSONObject jsonObject = JSON.parseObject(bodyData);
        String requestStr = getJsonValue("request", jsonObject);
        String clientIp = getJsonValue("client_ip", jsonObject);
        String response = getJsonValue("response", jsonObject);
        JSONObject requestJson = JSON.parseObject(requestStr);
        JSONObject responseJson = JSON.parseObject(response);
        String method = getJsonValue("method", requestJson);
        String requestSize = getJsonValue("size", requestJson);
        String body = getJsonValue("body", requestJson);
        String url = getJsonValue("url", requestJson);
        String querystring = getJsonValue("querystring", requestJson);
        String statusCode = getJsonValue("status", responseJson);
        String responseBody = getJsonValue("body", responseJson);

        String startedAt = getJsonValue("started_at", jsonObject);
        String upstreamUri = getJsonValue("upstream_uri", jsonObject);
        String routeStr = getJsonValue("route", jsonObject);
        String serviceStr = getJsonValue("service", jsonObject);
        JSONObject route = JSON.parseObject(routeStr);
        JSONObject service = JSON.parseObject(serviceStr);
        String routeName = getJsonValue("name", route);
        String serviceName = getJsonValue("name", service);

        String headersStr = getJsonValue("headers", requestJson);
        JSONObject headers = JSON.parseObject(headersStr);
        String traceId = getJsonValue("kong-request-id", headers);
        String contentLength = getJsonValue("content-length", headers);
        String userAgent = getJsonValue("user-agent", headers);

        String consumerStr = getJsonValue("consumer", jsonObject);
        JSONObject consumer = JSON.parseObject(consumerStr);
        String customId = getJsonValue("custom_id", consumer);

        String latenciesStr = getJsonValue("latencies", jsonObject);
        JSONObject latencies = JSON.parseObject(latenciesStr);
        String requestLatency = getJsonValue("request", latencies);
        String kongLatency = getJsonValue("kong", latencies);
        String proxyLatency = getJsonValue("proxy", latencies);

        String triesStr = getJsonValue("tries", jsonObject);
        JSONArray objects = JSONObject.parseArray(triesStr);
        if (!ObjectUtils.isEmpty(objects)) {
            int size = objects.size();
            kongLog.setRetryCount(size);
        }
        if (StringUtils.hasText(startedAt)) {
            kongLog.setStartedAt(new Date(Long.parseLong(startedAt)));
        }
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
        kongLog.setId(UUID.randomUUID().toString());
        return kongLog;
    }

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
            log.error(e.getMessage(), e);
        } finally {
            assert reader != null;
            reader.close();
        }
        return data.toString();
    }

    private Integer checkNumber(String param) {
        if (ObjectUtils.isEmpty(param)) {
            return null;
        } else {
            return Integer.parseInt(param);
        }
    }
}
