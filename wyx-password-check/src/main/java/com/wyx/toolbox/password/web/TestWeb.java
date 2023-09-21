package com.wyx.toolbox.password.web;

import com.wyx.toolbox.password.utils.PasswordCheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangyu
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestWeb {

    /**
     * http://127.0.0.1:8081/test/password?pwd=xxx&userName=xxx
     */
    @GetMapping("/password")
    public String testPwdCheck(String pwd,String userName) {
        log.info("pwd:{},userName:{}", pwd, userName);
        //校验是否为弱密码 (解密后的密码，登录的用户名称)
        if (!PasswordCheckUtil.evalPassword(pwd, userName)) {
            return "请输入包含大小写字母、数字和符号中的3种组合且不连续的长度为8-16位密码";
        }
        return "SUCCESS";
    }
}
