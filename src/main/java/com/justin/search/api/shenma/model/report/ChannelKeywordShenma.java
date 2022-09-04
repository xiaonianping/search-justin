package com.justin.search.api.shenma.model.report;

import java.math.BigDecimal;
import java.util.Date;

public class ChannelKeywordShenma {

    private Long id;
    private String channel;

    private String plan;
    private Long planId;

    private String unit;
    private Long unitId;

    private String keyword;
    private Long keywordId;

    /**
     * 展现
     */
    private Integer amount;

    /**
     * 点击
     */
    private Integer click;

    /**
     * 消费
     */
    private BigDecimal cost;

    /**
     * 点击率
     */
    private Double ctr;

    /**
     * 平均点击价格
     */
    private BigDecimal avgClickPrice;

    /**
     * 平均排名
     */
    private Double avgRanking;

    /**
     * 时间
     */
    private Date date;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endTime;

    public ChannelKeywordShenma() {
    }

    public ChannelKeywordShenma(String channel, String plan, Long planId, String unit, Long unitId, String keyword, Long keywordId,
                                Integer amount, Integer click, BigDecimal cost, Double ctr, BigDecimal avgClickPrice, Double avgRanking,
                                Date date, Date startDate, Date endTime) {
        this.channel = channel;
        this.plan = plan;
        this.planId = planId;
        this.unit = unit;
        this.unitId = unitId;
        this.keyword = keyword;
        this.keywordId = keywordId;
        this.amount = amount;
        this.click = click;
        this.cost = cost;
        this.ctr = ctr;
        this.avgClickPrice = avgClickPrice;
        this.avgRanking = avgRanking;
        this.date = date;
        this.startDate = startDate;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Double getCtr() {
        return ctr;
    }

    public void setCtr(Double ctr) {
        this.ctr = ctr;
    }

    public BigDecimal getAvgClickPrice() {
        return avgClickPrice;
    }

    public void setAvgClickPrice(BigDecimal avgClickPrice) {
        this.avgClickPrice = avgClickPrice;
    }

    public Double getAvgRanking() {
        return avgRanking;
    }

    public void setAvgRanking(Double avgRanking) {
        this.avgRanking = avgRanking;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}