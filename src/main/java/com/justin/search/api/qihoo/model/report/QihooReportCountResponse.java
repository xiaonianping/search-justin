package com.justin.search.api.qihoo.model.report;

import com.justin.search.api.qihoo.model.common.QihooResponse;

/**
 * 报告总数
 */
public class QihooReportCountResponse extends QihooResponse {

    /**
     * 报告总数
     */
    private Integer totalNumber;
    /**
     * 页数
     */
    private Integer totalPage;

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
