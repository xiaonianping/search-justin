package com.justin.search.api.qihoo.model.report;

import java.util.List;

/**
 * 报表响应
 */
public class QihooReportResponse {

    private List<QihooReportKeyword> keywordList;

    public List<QihooReportKeyword> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<QihooReportKeyword> keywordList) {
        this.keywordList = keywordList;
    }
}
