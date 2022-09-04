package com.justin.search.api.shenma.model.common;

import java.util.List;

/**
 * @author Administrator
 */
public class ShenmaResponseHeader {

    /**
     * 接口提示
     */
    private String desc;

    /**
     * 接口状态
     */
    private Integer status;

    /**
     * 错误提示
     */
    private List<ErrorCode> failures;

    /**
     * 总配额
     */
    private Integer quota;

    /**
     * 剩余配额
     */
    private Integer leftQuota;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getLeftQuota() {
        return leftQuota;
    }

    public void setLeftQuota(Integer leftQuota) {
        this.leftQuota = leftQuota;
    }

    public List<ErrorCode> getFailures() {
        return failures;
    }

    public void setFailures(List<ErrorCode> failures) {
        this.failures = failures;
    }

    public static class ErrorCode {

        private Integer code;
        private String message;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
