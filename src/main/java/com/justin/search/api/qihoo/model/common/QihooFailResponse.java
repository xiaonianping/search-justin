package com.justin.search.api.qihoo.model.common;

/**
 * 奇虎失败响应
 *
 * @author justin_xiao
 */
public class QihooFailResponse {

    private Integer code;
    private String description;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
