package com.wyx.toolbox.xss.config.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;

/**
 * 反序列化，用来处理请求中的JSON数据
 * 处理RequestBody方式接收的参数
 * @author wyx
 */
public class XssJacksonDeserializer  extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // 判断一下 值是不是JSON的格式，如果是JSON的话，那就不处理了。
        if (isJson(jp.getText())) {
            return jp.getText();
        }
        return StringEscapeUtils.escapeHtml4(jp.getText());
    }
    /**
     * 判断字符串是不是JSON
     *可以考虑更换其他判断json的方式
     * @param str 待判断串
     * @return true：是json串
     */
    private boolean isJson(String str) {
        boolean result = false;
        if (str != null && str.length() > 0) {
            str = str.trim();
            if (str.startsWith("{") && str.endsWith("}")) {
                result = true;
            } else if (str.startsWith("[") && str.endsWith("]")) {
                result = true;
            }
        }
        return result;
    }
}
