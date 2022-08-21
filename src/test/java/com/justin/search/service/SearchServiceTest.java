package com.justin.search.service;

import com.alibaba.fastjson.JSON;
import com.justin.search.SearchApplicationTests;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.security.GetUsersRequest;
import org.elasticsearch.client.security.GetUsersResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

class SearchServiceTest extends SearchApplicationTests {


    @Autowired
    private SearchService searchService;

    @Autowired
    private AnalyzeService analyzeService;

    @Autowired
    private RestHighLevelClient client;

    @Test
    public void getClientTest() throws IOException {
        /*AnalyzeResponse analyze = analyzeService.analyze(AnalyzeEnum.IK_MAX_WORD, "中华人民共和国");
        System.out.println(JSON.toJSONString(analyze, true));*/
        searchService.getIndexList();

        GetUsersRequest request = new GetUsersRequest("elastic", "kibana");
        GetUsersResponse response = client.security().getUsers(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response, true));
        /*GetUsersRequest getUsersRequest = new GetUsersRequest("es");
        GetUsersResponse response = restHighLevelClient.security().getUsers(getUsersRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response, true));*/
    }

}