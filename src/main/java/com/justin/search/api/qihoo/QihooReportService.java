package com.justin.search.api.qihoo;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.justin.search.api.qihoo.model.report.QihooReportCountResponse;
import com.justin.search.api.qihoo.model.report.QihooReportKeyword;
import com.justin.search.api.qihoo.model.report.QihooReportRequest;
import com.justin.search.api.qihoo.model.report.QihooReportResponse;
import com.justin.search.api.shenma.model.report.CostRecord;
import com.justin.search.common.enums.PlatFormEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 报表服务
 */
@Service
public class QihooReportService {


    @Autowired
    private QihooAccountService qihooAccountService;

    /**
     * 关键词报告
     * <p>
     * 获取关键词报告。通过结算报表接口可以获取最晚到昨天的报表，
     * 建议获取时间为第二天的8:30，正常情况8:30之前可以保证生成。
     * 如果未生成，接口会返回70109错误码。这类接口不能保证最晚生成时间，最好有识别错误码进行重试的机制。
     * <p>
     * <p>
     * 1	type不传或type=all ，computer,mobile不会被聚合，如果在两种投放都展现则同一条物料为两条数据
     * 2	建议分日进行取数据 (既startDate=endDate)，如果取多天数据分为多次请求
     * 3	level和idList共同控制；level=account，idList不起作用；level=plan，idList为计划id；level=group idList为组id
     * 4	不建议使用idList参数，建议用户取全量数据自行进行筛选
     * 5	每页数据1000条
     *
     * @return
     */
    public QihooReportResponse keyword(PlatFormEnum platFormEnum, QihooReportRequest request) {
        String url = "https://api.e.360.cn/dianjing/report/keyword?startDate={startDate}&endDate={endDate}&level={level}&type={type}&page={page}";
        QihooReportResponse reportResponseList = qihooAccountService.httpUriRequest(platFormEnum, url, request, new ParameterizedTypeReference<QihooReportResponse>() {
        });
        return reportResponseList;
    }

    public QihooReportCountResponse keywordCount(PlatFormEnum platFormEnum, QihooReportRequest request) {
        String url = "https://api.e.360.cn/dianjing/report/keywordCount?startDate={startDate}&endDate={endDate}&level={level}&type={type}";
        QihooReportCountResponse response = qihooAccountService.httpUriRequest(platFormEnum, url, request, new ParameterizedTypeReference<QihooReportCountResponse>() {
        });
        return response;
    }


    //==========================================================================================================================

    /**
     * 获取关键词报告
     */
    public void keywordReportHandle(PlatFormEnum platFormEnum, String taskCode, Date startDate, Date endDate) {
        long betweenDay = Math.max(DateUtil.between(startDate, endDate, DateUnit.DAY), 1);
        for (long i = 0; i < betweenDay; i++) {
            DateTime offset = DateUtil.offset(startDate, DateField.DAY_OF_MONTH, (int) i);
            keywordReportHandleByDay(platFormEnum, offset, (list) -> saveReportData(platFormEnum, taskCode, startDate, endDate, list));
        }
    }

    /**
     * 获取单日报告
     *
     * @param platFormEnum
     * @param date
     * @param function
     */
    private void keywordReportHandleByDay(PlatFormEnum platFormEnum, Date date, Consumer<QihooReportResponse> function) {
        QihooReportRequest qihooReportRequest = new QihooReportRequest(date, date, "account", 1);
        QihooReportCountResponse response = keywordCount(platFormEnum, qihooReportRequest);
        if (Objects.isNull(response) || Objects.isNull(response.getTotalPage())) {
            return;
        }
        for (int i = 1; i <= response.getTotalPage(); i++) {
            qihooReportRequest.setPage(i);
            QihooReportResponse keyword = keyword(platFormEnum, qihooReportRequest);
            function.accept(keyword);
        }
    }


    /**
     * 保存报告数据
     *
     * @param platFormEnum
     * @param taskCode
     * @param startDate
     * @param endDate
     * @param responses
     */
    private void saveReportData(PlatFormEnum platFormEnum, String taskCode, Date startDate, Date endDate, QihooReportResponse responses) {
        if (Objects.isNull(responses) || CollectionUtils.isEmpty(responses.getKeywordList()) || Objects.isNull(platFormEnum)) {
            return;
        }
        List<CostRecord> costRecordList = new ArrayList<>();
        for (QihooReportKeyword report : responses.getKeywordList()) {
            CostRecord costRecord = new CostRecord();
            costRecord.setId(Long.parseLong(report.getKeywordId()));
            costRecord.setTaskCode(taskCode);
            costRecord.setChannel(platFormEnum.getName());
            costRecord.setPlanCode(report.getCampaignName());
            costRecord.setUnitCode(report.getGroupName());

            BigDecimal cost = new BigDecimal(report.getTotalCost());
            int click = Integer.parseInt(report.getClicks());
            BigDecimal cpc = new BigDecimal(0);
            if (click > 0) {
                cpc = cost.divide(new BigDecimal(click), RoundingMode.HALF_UP);
            }
            costRecord.setCost(cost);
            costRecord.setCpc(cpc);
            costRecord.setImpression(Integer.parseInt(report.getViews()));
            costRecord.setClick(click);
            //costRecord.setCtr();
            //costRecord.setCpm();
            costRecord.setPosition(new BigDecimal(report.getAvgPosition()));
            //costRecord.setConversion();
            costRecord.setStartTime(startDate);
            costRecord.setEndTime(endDate);
            costRecordList.add(costRecord);
        }
        //批量保存
        System.out.println("size:" + JSON.toJSONString(costRecordList.size()));
    }

}
