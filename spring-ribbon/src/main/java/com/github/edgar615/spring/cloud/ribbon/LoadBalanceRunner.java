package com.github.edgar615.spring.cloud.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class LoadBalanceRunner implements ApplicationRunner {


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ResponseEntity<Account> accountResponseEntity =
                restTemplate.exchange("http://eureka-client/api/accounts/{id}", HttpMethod.GET, null, Account.class, 1);
        System.out.println(accountResponseEntity.getBody().getName());

        accountResponseEntity =
                restTemplate.exchange("http://eureka-client/api/accounts/{id}", HttpMethod.GET, null, Account.class, 1);
        System.out.println(accountResponseEntity.getBody().getName());
    }
}
