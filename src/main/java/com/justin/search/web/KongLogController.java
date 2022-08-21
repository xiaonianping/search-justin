package com.justin.search.web;

import com.justin.search.service.KongLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * justin_xiao
 */
@RequestMapping("/kong")
@RestController
public class KongLogController {
    private static final Logger log = LoggerFactory.getLogger(KongLogController.class);

    @Autowired
    private KongLogService kongLogService;

    @PostMapping("/save")
    public ResponseEntity<String> saveBody(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        kongLogService.saveRequestLog(request);
        long end = System.currentTimeMillis();
        log.info("存储日志time: {} ms", end - start);
        return ResponseEntity.ok("存储成功");
    }
}
