package com.justin.search.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.justin.search.mapper.UserMapper;
import com.justin.search.model.User;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private IndexService indexService;

    public static final String index_name = "user";


    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }


    public int insert(User record) {
        return userMapper.insert(record);
    }


    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }


    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }


    public void indexCreate() {
        AtomicInteger count = new AtomicInteger();
        BulkRequest bulkRequest = new BulkRequest();
        for (long i = 1; i <= 1045761; i++) {
            User user = selectByPrimaryKey(i);
            Optional.ofNullable(user).ifPresent(u -> {
                IndexRequest indexRequest = new IndexRequest();
                Map map = JSONObject.parseObject(JSONObject.toJSONString(u), Map.class);
                indexRequest.index(index_name).source(map);
                bulkRequest.add(indexRequest);
                if (count.incrementAndGet() == 1000) {
                    BulkResponse response = indexService.bulk(bulkRequest);
                    log.info(JSON.toJSONString(response.status().getStatus()));
                    count.set(0);
                }
            });
        }

    }

}
