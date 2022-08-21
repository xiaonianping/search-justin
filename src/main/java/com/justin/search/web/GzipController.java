package com.justin.search.web;

import com.justin.search.model.Result;
import com.justin.search.service.GzipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping()
public class GzipController {

    @Autowired
    private GzipService gzipService;

    @RequestMapping("gzip")
    public Result<String> gzipService(HttpServletRequest request) {
        gzipService.request(request);
        return Result.ofSuccess(null);
    }

}
