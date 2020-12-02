package com.github.edgar615.ribbon.hello;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.RestClient;

import java.net.URI;

public class EurekaExample {
    public static void main(String[] args) throws Exception {
        ConfigurationManager.loadPropertiesFromResources("eureka.properties");  // 1
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/api/accounts/1")).build(); // 3
        RestClient client = (RestClient) ClientFactory.getNamedClient("eureka-client");
        for (int i = 0; i < 20; i++)  {
            HttpResponse response =  client.executeWithLoadBalancer(request); // 4
            System.out.println("Status code for " + response.getRequestedURI() + "  :" + response.getStatus());
        }
    }
}
