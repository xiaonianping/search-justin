package com.justin.search.service;

import com.alibaba.fastjson.JSON;
import com.justin.search.model.KongLog;
import com.justin.search.utils.JsonUtil;
import com.justin.search.utils.NetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class GzipService {

    public static final String str = "\u001f�\b\u0000\u0000\u0000\u0000\u0000\u0000\u0003�V*J-Q�2�Q�-NW�Rzַ�ٔm��.|6u����==�g�{��\u001d*`\t\u0002J:J)�%�JVy�99�\u0000\u0014n\u0001�A\u0000\u0000\u0000";

    @Autowired
    private NetUtil netUtil;

    public void request(HttpServletRequest request) {
        try {
            String bodyData = JsonUtil.getBodyDataByInputStream(request);
            KongLog kongLog = JsonUtil.buildData(bodyData);
            kongLog.setLogMessage(bodyData);
            log.info("kongLog:{}", bodyData);
        } catch (IOException e) {
            log.info("request error:{}", e.getMessage(), e);
            e.printStackTrace();
        }
    }

}
