package com.justin.search.api.shenma;

import com.alibaba.fastjson.JSON;
import com.justin.search.api.shenma.model.common.ShenmaRequest;
import com.justin.search.api.shenma.model.common.ShenmaRequestHeader;
import com.justin.search.api.shenma.model.common.ShenmaResponse;
import com.justin.search.api.shenma.model.report.ShenmaDownloadFileRequest;
import com.justin.search.common.enums.PlatFormEnum;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class ShenmaAccountService {

    @Autowired
    private RestTemplate restTemplate;

    public ShenmaRequestHeader getAccountInfo(PlatFormEnum platFormEnum) {
        String username = "\u4E16\u5F3A\u5143\u4EF601";
        String password = "szsm&0241";
        String token = "19ba4be7-1cce-4144-b679-d9150e78467b";
        return new ShenmaRequestHeader(username, password, token);
    }


    public <T> T httpRequest(PlatFormEnum platFormEnum, String url, Object request, ParameterizedTypeReference<ShenmaResponse<T>> reference) {
        ShenmaRequestHeader header = getAccountInfo(platFormEnum);
        ShenmaRequest<Object> objectShenmaRequest = new ShenmaRequest<>(header, request);
        HttpEntity<ShenmaRequest<Object>> httpEntity = new HttpEntity<>(objectShenmaRequest);
        ResponseEntity<ShenmaResponse<T>> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, reference);
        HttpStatus statusCode = exchange.getStatusCode();
        log.error("exchange:{}", JSON.toJSONString(exchange));
        if (!HttpStatus.OK.equals(statusCode)) {
            return null;
        }
        ShenmaResponse<T> body = exchange.getBody();
        if (Objects.nonNull(body)) {
            return body.getBody();
        }
        return null;
    }

    public Resource downloadFile(PlatFormEnum platFormEnum, String url, ShenmaDownloadFileRequest request) {
        ShenmaRequest<ShenmaDownloadFileRequest> shenmaRequest = new ShenmaRequest<>(getAccountInfo(platFormEnum), request);
        Resource apiResult = null;
        try {
            apiResult = restTemplate.postForObject(url, shenmaRequest, Resource.class);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return apiResult;
    }


    public void test() throws FileNotFoundException {
        CSVReaderBuilder builder = new CSVReaderBuilder(new FileReader(""));
        /*try (CSVReader reader = builder.build()) {
            String[] nextLine;
            long time = 1492457959000L;
            while ((nextLine = reader.readNext()) != null) {
                ts.times.add(time);
                time += 3600 * 1000;
                double value = Double.parseDouble(nextLine[0]);
                ts.values.add(value);
            }
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }*/
    }


}
