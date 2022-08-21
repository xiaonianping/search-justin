package com.justin.search.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Cancellable;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class IndexService {

    @Autowired
    private RestHighLevelClient client;

    public CreateIndexResponse createIndex(String index) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        return createIndexResponse;
    }


    public IndexResponse create(IndexRequest indexRequest) {
        try {
            return client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public BulkResponse bulk(BulkRequest bulkRequest) {
        try {
            BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            return bulk;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public void bulkAsync(BulkRequest bulkRequest) {
        Cancellable cancellable = client.bulkAsync(bulkRequest, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkItemResponses) {
                RestStatus status = bulkItemResponses.status();
                log.info("Thread:{},status:{}", Thread.currentThread().getName(), status.getStatus());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
