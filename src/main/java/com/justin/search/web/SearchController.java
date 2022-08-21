package com.justin.search.web;

import com.justin.search.common.enums.AnalyzeEnum;
import com.justin.search.model.Result;
import com.justin.search.service.AnalyzeService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.indices.AnalyzeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping()
public class SearchController {

    @Autowired
    private AnalyzeService analyzeService;

    @RequestMapping("analyze")
    public ResponseEntity<AnalyzeResponse> analyze(String text) throws IOException {
        log.info("text:{}", text);
        AnalyzeResponse analyze = analyzeService.analyze(AnalyzeEnum.IK_SMART, text);
        return ResponseEntity.ok(analyze);
    }

    @RequestMapping("get")
    public Result<String> get(String text) {
        log.info("text:{}", text);
        return Result.ofSuccess(text);
    }
}
