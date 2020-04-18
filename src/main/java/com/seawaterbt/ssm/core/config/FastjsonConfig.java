package com.seawaterbt.ssm.core.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * fastjson配置类
 *
 * @author fengshuonan
 * @date 2017-05-23 22:56
 */
@Configuration
public class FastjsonConfig {

    @Bean
    public FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue
        );
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        ValueFilter valueFilter = (o, s, o1) -> {
            {
                if (null == o1) {
                    o1 = "";
                }
                if (o1 instanceof String) {
                    o1 = ((String) o1).trim();
                }
                return o1;
            }
        };
        fastJsonConfig.setSerializeFilters(valueFilter);
        converter.setFastJsonConfig(fastJsonConfig);
        return converter;
    }

}
