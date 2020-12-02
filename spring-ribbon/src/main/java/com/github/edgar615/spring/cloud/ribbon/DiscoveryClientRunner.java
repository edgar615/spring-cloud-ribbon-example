package com.github.edgar615.spring.cloud.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@Component
public class DiscoveryClientRunner implements ApplicationRunner {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> serviceNames = discoveryClient.getServices();
        for (String serviceName : serviceNames) {
            System.out.println(serviceName);
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
            for (ServiceInstance serviceInstance : serviceInstances) {
                System.out.println(serviceInstance);
            }
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-client");

        String userserviceUri = String.format("%s/api/accounts/%s",
                instances.get(0).getUri().toString(), 1);
        ResponseEntity<Account> accountResponseEntity =
                restTemplate.exchange(userserviceUri, HttpMethod.GET, null, Account.class);
        System.out.println(accountResponseEntity.getBody());
    }
}
