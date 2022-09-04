package com.justin.search.api.shenma.model.report;


import java.math.BigDecimal;
import java.util.Date;

public class CostRecord {
    private Long id;

    private String taskCode;

    private String keyword;

    private String channel;

    private String planCode;

    private String unitCode;

    private BigDecimal cost;

    private BigDecimal cpc;

    private Integer impression;

    private Integer click;

    private BigDecimal ctr;

    private BigDecimal cpm;

    private BigDecimal position;

    private Float conversion;

    private Float bridgeconversion;

    private Date startTime;

    private Date endTime;


    ////////////分布式计算框架更新词投放表所需参数///////////

    private BigDecimal prePrice;
    private BigDecimal currPrice;
    private Integer ruleId;
    private Integer status;

    ////////////分布式计算框架更新词投放表所需参数///////////


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode == null ? null : taskCode.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode == null ? null : planCode.trim();
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode == null ? null : unitCode.trim();
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getCpc() {
        return cpc;
    }

    public void setCpc(BigDecimal cpc) {
        this.cpc = cpc;
    }

    public Integer getImpression() {
        return impression;
    }

    public void setImpression(Integer impression) {
        this.impression = impression;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public BigDecimal getCtr() {
        return ctr;
    }

    public void setCtr(BigDecimal ctr) {
        this.ctr = ctr;
    }

    public BigDecimal getCpm() {
        return cpm;
    }

    public void setCpm(BigDecimal cpm) {
        this.cpm = cpm;
    }

    public BigDecimal getPosition() {
        return position;
    }

    public void setPosition(BigDecimal position) {
        this.position = position;
    }

    public Float getConversion() {
        return conversion;
    }

    public void setConversion(Float conversion) {
        this.conversion = conversion;
    }

    public Float getBridgeconversion() {
        return bridgeconversion;
    }

    public void setBridgeconversion(Float bridgeconversion) {
        this.bridgeconversion = bridgeconversion;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public BigDecimal getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(BigDecimal prePrice) {
        this.prePrice = prePrice;
    }

    public BigDecimal getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(BigDecimal currPrice) {
        this.currPrice = currPrice;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CostRecord{" +
                "id=" + id +
                ", taskCode='" + taskCode + '\'' +
                ", keyword='" + keyword + '\'' +
                ", channel='" + channel + '\'' +
                ", planCode='" + planCode + '\'' +
                ", unitCode='" + unitCode + '\'' +
                ", cost=" + cost +
                ", cpc=" + cpc +
                ", impression=" + impression +
                ", click=" + click +
                ", ctr=" + ctr +
                ", cpm=" + cpm +
                ", position=" + position +
                ", conversion=" + conversion +
                ", bridgeconversion=" + bridgeconversion +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", prePrice=" + prePrice +
                ", currPrice=" + currPrice +
                ", ruleId=" + ruleId +
                ", status=" + status +
                '}';
    }
}