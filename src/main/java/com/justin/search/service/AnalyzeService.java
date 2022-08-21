package com.justin.search.service;

import com.justin.search.common.enums.AnalyzeEnum;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.AnalyzeRequest;
import org.elasticsearch.client.indices.AnalyzeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 分词
 */
@Service
public class AnalyzeService {

    @Autowired
    private RestHighLevelClient client;

    public AnalyzeResponse analyze(AnalyzeEnum analyzeEnum, String text) throws IOException {
        AnalyzeRequest analyzeRequest = AnalyzeRequest.withGlobalAnalyzer(analyzeEnum.getAnalyze(), text);
        return client.indices().analyze(analyzeRequest, RequestOptions.DEFAULT);
    }

}
