package com.justin.search.api.qihoo.model.report;

import java.util.Date;
import java.util.List;

/**
 * 报表请求
 */
public class QihooReportRequest {

    /**
     * 必须
     * 起始日期，最大跨度90天
     */
    private Date startDate;

    /**
     * 必须
     * 截至日期
     */
    private Date endDate;

    /**
     * 必须
     * 物料层级，account代表账户级别，plan代表计划级别，group代表组级别
     */
    private String level;

    /**
     * 选择关键词报告的投放端类型，允许为空或不包含该参数，
     * 若不带该参数默认为all。
     * all 表示查询全部投放端数据，
     * mobile 表示移动，
     * computer 表示计算机
     */
    private String type;

    /**
     * 如果是账户不填，其他层级需要对应的计划id或组id，使用json格式，如[1000001,20000001]
     */
    private List<String> idList;

    /**
     * 返回结果的页码，默认为1
     */
    private Integer page;

    public QihooReportRequest() {
    }

    public QihooReportRequest(Date startDate, Date endDate, String level, Integer page) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.level = level;
        this.page = page;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
