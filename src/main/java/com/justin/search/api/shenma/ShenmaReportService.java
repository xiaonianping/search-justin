package com.justin.search.api.shenma;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.justin.search.api.shenma.model.common.ShenmaResponse;
import com.justin.search.api.shenma.model.report.CostRecord;
import com.justin.search.api.shenma.model.report.ShenmaDownloadFileRequest;
import com.justin.search.api.shenma.model.report.ShenmaReportRequest;
import com.justin.search.api.shenma.model.report.ShenmaReportResponse;
import com.justin.search.common.enums.PlatFormEnum;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class ShenmaReportService {

    @Autowired
    private ShenmaAccountService shenmaAccountService;

    /**
     * 查询关键词数据报告
     *
     * @param platFormEnum
     * @param request
     * @return
     */
    public ShenmaReportResponse winfo(PlatFormEnum platFormEnum, ShenmaReportRequest request) {
        String url = "https://e.uc.cn/shc/api/report/winfo";
        ShenmaReportResponse shenmaReportResponse = shenmaAccountService.httpRequest(platFormEnum, url, request, new ParameterizedTypeReference<ShenmaResponse<ShenmaReportResponse>>() {
        });
        System.out.println(JSON.toJSONString(shenmaReportResponse));
        return shenmaReportResponse;
    }

    /**
     * 下载文件
     *
     * @param platFormEnum
     * @param request
     */
    public Resource downloadFile(PlatFormEnum platFormEnum, ShenmaDownloadFileRequest request) {
        String url = "https://e.uc.cn/shc/api/report/downloadFile";
        return shenmaAccountService.downloadFile(platFormEnum, url, request);
    }


    /**
     * 下载报表
     *
     * @param platFormEnum
     * @param startDate
     * @param endDate
     * @param taskCode
     */
    public void report(PlatFormEnum platFormEnum, Date startDate, Date endDate, String taskCode) {
        String startDateStr = DateUtil.formatDate(startDate);
        String endDateStr = DateUtil.formatDate(endDate);
        ShenmaReportRequest shenmaReportRequest = new ShenmaReportRequest(startDateStr, endDateStr, 1, Collections.emptyList());
        ShenmaReportResponse winfo = winfo(platFormEnum, shenmaReportRequest);
        if (Objects.isNull(winfo)) {
            return;
        }
        try {
            Thread.sleep(1000 * 6);
        } catch (InterruptedException e) {
            log.error("线程休眠异常,{}", e.getMessage(), e);
        }
        //Resource apiResult = downloadFile(platFormEnum, new ShenmaDownloadFileRequest(winfo.getTaskId(), true));
        Resource apiResult = downloadFile(platFormEnum, new ShenmaDownloadFileRequest(1179775672L, true));
        if (Objects.isNull(apiResult)) {
            return;
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(apiResult.getInputStream(), StandardCharsets.UTF_8))) {
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(bufferedReader);
            csvReaderBuilder.withSkipLines(1);
            CSVReader reader = csvReaderBuilder.build();
            String[] row;
            List<String[]> strings = new ArrayList<>();
            long i = 0;
            while (Objects.nonNull(row = reader.readNext())) {
                i++;
                strings.add(row);
                if (strings.size() == 1000) {
                    saveData(platFormEnum, strings, taskCode, startDate, endDate);
                    strings.clear();
                }
            }
            reader.close();
            System.out.println(i);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
    }


    public void saveData(PlatFormEnum platFormEnum, List<String[]> rowList, String taskCode, Date startDate, Date endDate) {
        List<CostRecord> costRecords = new ArrayList<>();
        for (String[] row : rowList) {
            String dateStr = row[0];
            String accountId = row[1];
            String accountName = row[2];
            String unitId = row[3];
            String unit = row[4];
            String planId = row[5];
            String plan = row[6];
            String keywordId = row[7];
            String keyword = row[8];
            String amount = row[9];
            String click = row[10];
            String cost = row[11];
            String ctr = row[12];
            String avgClickPrice = row[13];
            String avgRanking = row[14];

/*             Date date = DateUtil.parse(dateStr, "yyyy-MM-dd");
           ChannelKeywordShenma ks = new ChannelKeywordShenma(platFormEnum.getName(), plan, Long.parseLong(planId), unit, Long.parseLong(unitId),
                    keyword, Long.parseLong(keywordId), Integer.parseInt(amount), Integer.parseInt(click), new BigDecimal(cost), new Double(ctr), new BigDecimal(avgClickPrice),
                    new Double(avgRanking), date, startDate, endDate);*/


            CostRecord cr = new CostRecord();
            cr.setPlanCode(plan);
            cr.setPlanCode(plan);
            cr.setUnitCode(unit);
            if (cr.getPlanCode().contains("已删除") || cr.getUnitCode().contains("已删除")) {
                // 已删除的计划或单元不导入
            }
            cr.setTaskCode(taskCode);
            cr.setChannel(platFormEnum.getName());

            cr.setId(Long.parseLong(keywordId));
            cr.setStartTime(startDate);
            cr.setEndTime(endDate);
            cr.setKeyword(keyword);
            cr.setCost(new BigDecimal(cost));
            cr.setCpc(new BigDecimal(avgClickPrice));
            cr.setClick(Integer.parseInt(click));
            cr.setImpression(Integer.parseInt(amount));
            if (StringUtils.isNotEmpty(ctr)) {
                ctr = ctr.replaceAll("%", "");
                cr.setCtr(new BigDecimal(ctr));
            }
            cr.setPosition(new BigDecimal(avgRanking));
            costRecords.add(cr);
        }
        //System.out.println(JSON.toJSONString(costRecords));
    }
}
