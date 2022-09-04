package com.justin.search.api.shenma.model.report;

public class ShenmaDownloadFileRequest {

    /**
     * 必填
     * 指定的下载任务ID
     */
    private Long taskId;

    /**
     * true-推广报表
     * false-分析类报表，
     * 默认为true
     * 分析类报告包含：
     * APP层级数据报告，受众分析报告，账户安心赔付消费数据报告，
     * 超级巡量数据报告，建议开启超级巡量报告
     */
    private Boolean adReport;

    public ShenmaDownloadFileRequest() {
    }

    public ShenmaDownloadFileRequest(Long taskId, Boolean adReport) {
        this.taskId = taskId;
        this.adReport = adReport;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Boolean getAdReport() {
        return adReport;
    }

    public void setAdReport(Boolean adReport) {
        this.adReport = adReport;
    }
}
