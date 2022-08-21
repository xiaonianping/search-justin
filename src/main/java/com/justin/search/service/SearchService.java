package com.justin.search.service;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexTemplatesRequest;
import org.elasticsearch.client.indices.GetIndexTemplatesResponse;
import org.elasticsearch.client.indices.IndexTemplateMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private RestHighLevelClient client;

    public void getIndexList() throws IOException {
        GetIndexTemplatesRequest getIndexTemplatesRequest = new GetIndexTemplatesRequest();
        GetIndexTemplatesResponse indexTemplate = client.indices().getIndexTemplate(getIndexTemplatesRequest, RequestOptions.DEFAULT);
        List<IndexTemplateMetaData> indexTemplates = indexTemplate.getIndexTemplates();
        List<String> collect = indexTemplates.stream().map(IndexTemplateMetaData::name).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect,true));
    }
}
