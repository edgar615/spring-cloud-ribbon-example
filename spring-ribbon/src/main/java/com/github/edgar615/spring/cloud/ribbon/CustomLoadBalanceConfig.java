package com.github.edgar615.spring.cloud.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CustomLoadBalanceConfig {

    @Bean
    @ConditionalOnMissingBean
    public IRule accountRule() {
        return new RandomRule();
    }
}
