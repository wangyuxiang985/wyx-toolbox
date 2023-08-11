package com.wyx.toolbox.xss.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 重新包装一下Request。重写一些获取参数的方法，将每个参数都进行过滤
 * @author wyx
 */
@Slf4j
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    /**
     * 请求体 RequestBody
     */
    private String reqBody;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        log.info("XssHttpServletRequestWrapper come in ...");
        this.request = request;
        reqBody = getBodyString();
    }

    @Override
    public String getQueryString() {
        return StringEscapeUtils.escapeHtml4(super.getQueryString());
    }

    /**
     * 获取parameter参数并利用转义工具类转义
     * @param name 参数名
     * @return 转义后的value值
     */
    @Override
    public String getParameter(String name) {
        log.info("---xss XSSHttpServletRequestWrapper work  getParameter-----");
        String parameter = request.getParameter(name);
        if (parameter != null && parameter.length() > 0) {
            log.info("----filter before--name:{}--value:{}----", name, parameter);
            parameter = StringEscapeUtils.escapeHtml4(parameter);
            log.info("----filter after--name:{}--value:{}----", name, parameter);
        }
        return parameter;
    }

    /**
     * 批量转义
     * @param name 参数名
     */
    @Override
    public String[] getParameterValues(String name) {
        log.info("---xss XssHttpServletRequestWrapper work  getParameterValues-----");
        String[] parameterValues = request.getParameterValues(name);
        if (parameterValues != null && parameterValues.length > 0) {
            for (int i = 0; i < parameterValues.length; i++) {
                parameterValues[i] = StringEscapeUtils.escapeHtml4(parameterValues[i]);
            }
        }
        return parameterValues;
    }

    /**
     *getParameterMap方法转义
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        log.info("---xss XssHttpServletRequestWrapper work  getParameterMap-----");
        Map<String, String[]> map = request.getParameterMap();
        if (map != null && !map.isEmpty()) {
            for (String[] value : map.values()) {
                /*循环所有的value*/
                for (String str : value) {
                    log.info("----filter before--value:{}----", str);
                    str = StringEscapeUtils.escapeHtml4(str);
                    log.info("----filter after--value:{}----", str);
                }
            }
        }
        return map;
    }

    /**
     * 重写输入流的方法，因为使用RequestBody的情况下是不会走上面的方法的
     */
    @Override
    public BufferedReader getReader() throws IOException {
        log.info("---xss XssHttpServletRequestWrapper work  getReader-----");
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * The default behavior of this method is to return getInputStream() on the
     * wrapped request object.
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        log.info("---xss XSSHttpServletRequestWrapper work  getInputStream-----");
        /*创建字节数组输入流*/
        final ByteArrayInputStream bais = new ByteArrayInputStream(reqBody.getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }


    /**
     * 获取请求体
     *
     * @return 请求体
     */
    private String getBodyString() {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader =  new BufferedReader(new InputStreamReader( request.getInputStream()))){
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            log.error("-----get Body String Error:{}----", e.getMessage(), e);
        }
        return builder.toString();
    }
}
