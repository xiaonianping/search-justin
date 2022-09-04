package com.justin.search.api.shenma.model.common;

public class ShenmaRequest<T> {

    private ShenmaRequestHeader header;

    private T body;

    public ShenmaRequest() {
    }

    public ShenmaRequest(ShenmaRequestHeader header, T body) {
        this.header = header;
        this.body = body;
    }

    public ShenmaRequestHeader getHeader() {
        return header;
    }

    public void setHeader(ShenmaRequestHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
