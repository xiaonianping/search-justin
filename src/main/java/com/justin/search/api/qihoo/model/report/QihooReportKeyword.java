package com.justin.search.api.qihoo.model.report;

/**
 * 关键词报表
 */
public class QihooReportKeyword {

    /**
     * 日期
     */
    private String date;
    /**
     * 关键词报告的投放端类型
     */
    private String type;
    /**
     * 推广计划ID
     */
    private String campaignId;
    /**
     * 推广计划名称
     */
    private String campaignName;
    /**
     * 推广组ID
     */
    private String groupId;
    /**
     * 推广组名称
     */
    private String groupName;
    /**
     * 关键词ID
     */
    private String keywordId;
    /**
     * 关键词
     */
    private String keyword;
    /**
     * 展现
     */
    private String views;
    /**
     * 点击
     */
    private String clicks;
    /**
     * 消费
     */
    private String totalCost;
    /**
     * 关键词计算机排名
     */
    private String avgPosition;
    /**
     * 关键词移动排名
     */
    private String mavgPosition;
    /**
     * pc端url
     */
    private String url;
    /**
     * 移动端url
     */
    private String mobileUrl;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(String keywordId) {
        this.keywordId = keywordId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getAvgPosition() {
        return avgPosition;
    }

    public void setAvgPosition(String avgPosition) {
        this.avgPosition = avgPosition;
    }

    public String getMavgPosition() {
        return mavgPosition;
    }

    public void setMavgPosition(String mavgPosition) {
        this.mavgPosition = mavgPosition;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMobileUrl() {
        return mobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }
}
