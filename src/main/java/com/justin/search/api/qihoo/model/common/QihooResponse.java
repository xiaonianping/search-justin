package com.justin.search.api.qihoo.model.common;

import java.util.List;

public class QihooResponse {

    private List<QihooFailResponse> failures;

    public List<QihooFailResponse> getFailures() {
        return failures;
    }

    public void setFailures(List<QihooFailResponse> failures) {
        this.failures = failures;
    }
}
