package com.justin.search;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.justin.search.model.Article;
import com.justin.search.service.ArticleService;
import com.justin.search.service.IndexService;
import com.justin.search.service.KongLogService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@SpringBootTest
public
class SearchApplicationTests {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private IndexService indexService;

    @Autowired
    private KongLogService kongLogService;

/*    @Resource
    private IMapping<User, UserVO> iMapping;*/

    @Test
    public void mapstructTest() {
/*        User user = new User();
        user.setId(1L);
        user.setName("justin");
        user.setCity("shenzhen");
        UserVO userVO = iMapping.sourceToTarget(user);
        System.out.println(JSON.toJSONString(userVO));*/

    }


    @Test
    void contextLoads0() throws IOException {
        String index = "article";
        int size = 100;
        Long id = 70000L;
        List<Article> articles = null;

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(10000));

        do {
            articles = articleService.selectById(id, size);
            if (null == articles || articles.size() < 1) {
                break;
            }
            id = articles.get(articles.size() - 1).getId();

            List<Article> finalArticles = articles;
            finalArticles.forEach(article -> {
                Map map = JSONObject.parseObject(JSON.toJSONString(article), Map.class);
                IndexRequest source = new IndexRequest(index).id(article.getId().toString()).source(map);
                long l = System.currentTimeMillis();
                IndexResponse indexResponse = indexService.create(source);
                long time = System.currentTimeMillis() - l;
                String name = Thread.currentThread().getName();
                log.info("Thread:{},Status:{},time:{},article:{}", name, indexResponse.status().getStatus(), time, finalArticles.size());
            });
        } while (articles.size() > 0);

    }


    @Test
    void contextLoads() throws IOException {
        String index = "article";
        int size = 100;
        Long id = 40000L;
        List<Article> articles = null;

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(10000));

        do {
            long l1 = System.currentTimeMillis();
            articles = articleService.selectById(id, size);
            long qtime = System.currentTimeMillis() - l1;
            if (null == articles || articles.size() < 1) {
                break;
            }
            id = articles.get(articles.size() - 1).getId();
            BulkRequest bulkRequest = new BulkRequest();

            List<Article> finalArticles = articles;

            finalArticles.forEach(article -> {
                Map map = JSONObject.parseObject(JSON.toJSONString(article), Map.class);
                bulkRequest.add(new IndexRequest(index).source(map));
            });
            long l = System.currentTimeMillis();

            BulkResponse bulk = indexService.bulk(bulkRequest);
            String name = Thread.currentThread().getName();
            long time = System.currentTimeMillis() - l;
            log.info("Thread:{},Status:{},time:{},qtime:{},id:{}", name, bulk.status().getStatus(), time, qtime, id);


        } while (articles.size() > 0);

    }

}
