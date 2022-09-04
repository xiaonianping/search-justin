package com.justin.search.api.shenma.model.report;

import java.util.List;

/**
 * @author Administrator
 */
public class ShenmaReportRequest {

    /**
     * 必填
     * 取值范围：
     * unitOfTime=0时
     * [当天-29天，当天]；
     * unitOfTime=1，2，3时
     * [当天-364天，当天]
     */
    private String startDate;
    /**
     * 结束日期	必填
     * 取值范围：
     * unitOfTime=0时
     * [当天-29天，当天]；
     * unitOfTime=1，2，3时
     * [当天-364天，当天]
     */
    private String endDate;
    /**
     * 必填
     * 0，1，2 ,3,4(小时，日报，月报，汇总,分周)
     */
    private Integer unitOfTime;
    /**
     * 必填
     * String[]	指定返回的报表中，需要包含的数据指标列
     */
    private List<String> performanceData;

    /**
     * 选填
     * 推广组过滤类型对应过滤ids
     */
    private List<Long> adGroupIds;

    /**
     * 选填
     * 推广计划过滤类型对应过滤ids
     */
    private List<Long> campaignIds;

    /**
     * 选填
     * 创意过滤类型对应过滤ids
     */
    private List<Long> creativeIds;

    /**
     * 选填
     * 关键词过滤类型对应过滤ids
     */
    private List<Long> winfoIds;

    public ShenmaReportRequest() {
    }

    public ShenmaReportRequest(String startDate, String endDate, Integer unitOfTime, List<String> performanceData) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.unitOfTime = unitOfTime;
        this.performanceData = performanceData;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getUnitOfTime() {
        return unitOfTime;
    }

    public void setUnitOfTime(Integer unitOfTime) {
        this.unitOfTime = unitOfTime;
    }

    public List<String> getPerformanceData() {
        return performanceData;
    }

    public void setPerformanceData(List<String> performanceData) {
        this.performanceData = performanceData;
    }

    public List<Long> getAdGroupIds() {
        return adGroupIds;
    }

    public void setAdGroupIds(List<Long> adGroupIds) {
        this.adGroupIds = adGroupIds;
    }

    public List<Long> getCampaignIds() {
        return campaignIds;
    }

    public void setCampaignIds(List<Long> campaignIds) {
        this.campaignIds = campaignIds;
    }

    public List<Long> getCreativeIds() {
        return creativeIds;
    }

    public void setCreativeIds(List<Long> creativeIds) {
        this.creativeIds = creativeIds;
    }

    public List<Long> getWinfoIds() {
        return winfoIds;
    }

    public void setWinfoIds(List<Long> winfoIds) {
        this.winfoIds = winfoIds;
    }
}
