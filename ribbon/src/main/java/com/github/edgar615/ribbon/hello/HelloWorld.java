package com.github.edgar615.ribbon.hello;

import com.netflix.client.AbstractLoadBalancerAwareClient;
import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.HttpClientRequest;
import com.netflix.niws.client.http.RestClient;

import java.net.URI;

public class HelloWorld {
    public static void main(String[] args) throws Exception {
        ConfigurationManager.loadPropertiesFromResources("sample-client.properties");  // 1
        System.out.println(ConfigurationManager.getConfigInstance().getProperty("sample-client.ribbon.listOfServers"));
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/")).build(); // 3
        RestClient client = (RestClient) ClientFactory.getNamedClient("sample-client");
        for (int i = 0; i < 20; i++)  {
            HttpResponse response =  client.executeWithLoadBalancer(request); // 4
            System.out.println("Status code for " + response.getRequestedURI() + "  :" + response.getStatus());
        }
        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();
        System.out.println(lb.getLoadBalancerStats());
        ConfigurationManager.getConfigInstance().setProperty(
                "sample-client.ribbon.listOfServers", "www.linkedin.com:80,www.github.com:80"); // 5
        System.out.println("changing servers ...");
        Thread.sleep(3000); // 6
        for (int i = 0; i < 20; i++)  {
            HttpResponse response = client.executeWithLoadBalancer(request);
            System.out.println("Status code for " + response.getRequestedURI() + "  : " + response.getStatus());
//            response.close();
        }
        System.out.println(lb.getLoadBalancerStats()); // 7
        for (int i = 0; i < 20; i ++) {
          Server server = lb.chooseServer();
          System.out.println(server.getHost());
        }
    }
}
