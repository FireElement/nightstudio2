package com.ns.common.util.config;

import com.ns.common.util.filter.token.TokenFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by xuezhucao on 16/6/20.
 */
@Configuration
public class FilterConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenFilter()).addPathPatterns("/**");
//        registry.addInterceptor(new SigFilter()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
