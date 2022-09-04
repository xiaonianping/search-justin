package com.justin.search.api.shenma.model.common;

/**
 * @author Administrator
 */
public class ShenmaResponse<T> {

    private ShenmaResponseHeader header;

    private T body;

    public ShenmaResponse() {
    }

    public ShenmaResponse(ShenmaResponseHeader header, T body) {
        this.header = header;
        this.body = body;
    }

    public ShenmaResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ShenmaResponseHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
