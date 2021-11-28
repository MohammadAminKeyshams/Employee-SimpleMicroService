package com.suncorp.emplyee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/handShake")
public class HandShake {
    @Autowired
    WebClient.Builder loadBalancedWebClientBuilder;
    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private Environment environment;

    @GetMapping("/sayHello")
    public String sayHello(){
        List<ServiceInstance> services = discoveryClient.getInstances("employee");

        for (ServiceInstance service : services) {
            System.out.println(service);
        }

        return "Hello Im Employee Service"+environment.getProperty("local.server.port");
    }

}
