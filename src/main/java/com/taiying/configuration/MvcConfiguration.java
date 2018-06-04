package com.taiying.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Iterator;
import java.util.List;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Iterator iterator = converters.iterator();

        while (iterator.hasNext()) {
            HttpMessageConverter o = (HttpMessageConverter)iterator.next();
            if (o instanceof StringHttpMessageConverter) {
                iterator.remove();
            }
        }
    }
}
