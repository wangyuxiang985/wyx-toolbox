package com.wyx.toolbox.xss.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.wyx.toolbox.xss.config.serializer.XssJacksonDeserializer;
import com.wyx.toolbox.xss.config.serializer.XssJacksonSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * mvc配置
 * @author wyx
 */
@Configuration
public class MySelfWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    /**
     * 注册、配置自定义的序列化方法
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        ObjectMapper mapper = builder.build();
        /*注入自定义的序列化工具，将RequestBody的参数进行转译后传输*/
        SimpleModule simpleModule = new SimpleModule();
        // XSS序列化
        simpleModule.addSerializer(String.class, new XssJacksonSerializer());
        simpleModule.addDeserializer(String.class, new XssJacksonDeserializer());
        mapper.registerModule(simpleModule);
        converters.add(new MappingJackson2HttpMessageConverter(mapper));
    }
}
