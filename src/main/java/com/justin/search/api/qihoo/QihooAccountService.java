package com.justin.search.api.qihoo;

import com.alibaba.fastjson.JSON;
import com.justin.search.common.enums.PlatFormEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class QihooAccountService {

    @Autowired
    private RestTemplate restTemplate;

    public HttpHeaders getAccountInfo(PlatFormEnum platFormEnum) {
        HttpHeaders httpHead = new HttpHeaders();
        httpHead.add("apiKey", "94b77c36e09dab8ed400ebf7b18f482a");
        httpHead.add("accessToken", "6da3c759765911d16d1e2189ff2478ae074518f61847c19c2");
        return httpHead;
    }


    @SuppressWarnings({"unchecked"})
    public <T> T httpUriRequest(PlatFormEnum platFormEnum, String url, Object uriVariables, ParameterizedTypeReference<T> reference) {
        HttpHeaders header = getAccountInfo(platFormEnum);
        HttpEntity<Object> httpEntity = new HttpEntity<>(header);
        ResponseEntity<T> responseEntity = null;
        try {
            BeanMap beanMap = BeanMap.create(uriVariables);
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, reference, beanMap);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (Objects.isNull(responseEntity) || !HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            log.error("exchange:{}", JSON.toJSONString(responseEntity));
            return null;
        }
        return responseEntity.getBody();
    }


}
