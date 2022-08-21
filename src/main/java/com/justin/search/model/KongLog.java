package com.justin.search.model;

import java.util.Date;

public class KongLog {
    private String id;

    private String traceId;

    private String requestUrl;

    private String remoteAddr;

    private String requestMethod;

    private String queryString;

    private Integer status;

    private Date createDate;

    private String userAgent;

    private String routeName;

    private String serviceName;

    private String upstreamUri;

    private Integer requestSize;

    private Integer contentLength;

    private Date startedAt;

    private String requestBody;

    private String responseBody;

    private Integer retryCount;

    private String customId;

    private Integer proxyLatency;

    private Integer kongLatency;

    private Integer requestLatency;

    private String logMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUpstreamUri() {
        return upstreamUri;
    }

    public void setUpstreamUri(String upstreamUri) {
        this.upstreamUri = upstreamUri;
    }

    public Integer getRequestSize() {
        return requestSize;
    }

    public void setRequestSize(Integer requestSize) {
        this.requestSize = requestSize;
    }

    public Integer getContentLength() {
        return contentLength;
    }

    public void setContentLength(Integer contentLength) {
        this.contentLength = contentLength;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public Integer getProxyLatency() {
        return proxyLatency;
    }

    public void setProxyLatency(Integer proxyLatency) {
        this.proxyLatency = proxyLatency;
    }

    public Integer getKongLatency() {
        return kongLatency;
    }

    public void setKongLatency(Integer kongLatency) {
        this.kongLatency = kongLatency;
    }

    public Integer getRequestLatency() {
        return requestLatency;
    }

    public void setRequestLatency(Integer requestLatency) {
        this.requestLatency = requestLatency;
    }

    public KongLog() {
    }

    public KongLog(
            String id,
            String traceId,
            String requestUrl,
            String remoteAddr,
            String requestMethod,
            String queryString,
            Integer status,
            Date createDate,
            String userAgent,
            String routeName,
            String serviceName,
            String upstreamUri,
            Integer requestSize,
            Integer contentLength,
            Date startedAt,
            String requestBody,
            String responseBody,
            Integer retryCount,
            String customId,
            Integer proxyLatency,
            Integer kongLatency,
            Integer requestLatency,
            String logMessage) {
        this.id = id;
        this.traceId = traceId;
        this.requestUrl = requestUrl;
        this.remoteAddr = remoteAddr;
        this.requestMethod = requestMethod;
        this.queryString = queryString;
        this.status = status;
        this.createDate = createDate;
        this.userAgent = userAgent;
        this.routeName = routeName;
        this.serviceName = serviceName;
        this.upstreamUri = upstreamUri;
        this.requestSize = requestSize;
        this.contentLength = contentLength;
        this.startedAt = startedAt;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
        this.retryCount = retryCount;
        this.customId = customId;
        this.proxyLatency = proxyLatency;
        this.kongLatency = kongLatency;
        this.requestLatency = requestLatency;
        this.logMessage = logMessage;
    }

    @Override
    public String toString() {
        return "KongLog{"
                + "id="
                + id
                + ", traceId='"
                + traceId
                + '\''
                + ", requestUrl='"
                + requestUrl
                + '\''
                + ", remoteAddr='"
                + remoteAddr
                + '\''
                + ", requestMethod='"
                + requestMethod
                + '\''
                + ", queryString='"
                + queryString
                + '\''
                + ", status="
                + status
                + ", createDate="
                + createDate
                + ", userAgent='"
                + userAgent
                + '\''
                + ", routeName='"
                + routeName
                + '\''
                + ", serviceName='"
                + serviceName
                + '\''
                + ", upstreamUri='"
                + upstreamUri
                + '\''
                + ", requestSize="
                + requestSize
                + ", contentLength="
                + contentLength
                + ", startedAt="
                + startedAt
                + ", requestBody='"
                + requestBody
                + '\''
                + ", responseBody='"
                + responseBody
                + '\''
                + ", retryCount="
                + retryCount
                + ", customId='"
                + customId
                + '\''
                + ", proxyLatency="
                + proxyLatency
                + ", kongLatency="
                + kongLatency
                + ", requestLatency="
                + requestLatency
                + ", logMessage='"
                + logMessage
                + '\''
                + '}';
    }
}
