package com.wyx.toolbox.xss.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestWeb {

    /**
     * http://127.0.0.1:8081/test/xss?param=<script>alert("xss");</script>
     */
    @GetMapping("/xss")
    public String testXss(String param) {
        log.info(param);
        return param;
    }
}
