package com.justin.search.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * ElasticSearch 配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchConfig {
    private List<EsConfigAddress> address;
    private Integer connectTimeout;
    private Integer socketTimeout;
    private Integer connectionRequestTimeout;
    private Integer maxConnectNum;
    private Integer maxConnectPerRoute;
    private String userName;
    private String password;

    @Bean
    public RestHighLevelClient getRestHighLevelClient() {
        List<HttpHost> hostLists = new ArrayList<>();
        address.forEach(esConfigAddress -> hostLists.add(new HttpHost(esConfigAddress.getHost(), esConfigAddress.getPort(), esConfigAddress.getSchema())));
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
        RestClientBuilder builder = RestClient.builder(hostLists.toArray(new HttpHost[]{}));
        // 异步连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeout);
            requestConfigBuilder.setSocketTimeout(socketTimeout);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeout);
            return requestConfigBuilder;
        });
        // 异步连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
        });
        return new RestHighLevelClient(builder);
    }


    @Data
    public static class EsConfigAddress {
        private String schema;
        private String host;
        private Integer port;
    }
}
