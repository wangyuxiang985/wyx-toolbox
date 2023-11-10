package com.wyx.toolbox.datamask.web;

import com.wyx.toolbox.datamask.vo.DemoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoWeb {

    /**
     * http://127.0.0.1:8081/test/datamask
     */
    @GetMapping("/datamask")
    public DemoVo testDemoVo() {
        DemoVo demoVo = new DemoVo();
        demoVo.setId(1L);
        demoVo.setUserName("老王啊");
        demoVo.setPhone("17788888888");
        demoVo.setEmail("aaaaad@qq.com");
        return demoVo;
    }
}
