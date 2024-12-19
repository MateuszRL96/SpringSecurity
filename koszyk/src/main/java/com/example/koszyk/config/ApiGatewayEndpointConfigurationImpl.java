package com.example.koszyk.config;

import com.example.koszyk.entity.Response;

import jakarta.annotation.PostConstruct;
import lombok.Value;
import org.example.ApiGatewayEndpointConfiguration;
import org.example.enntity.Role;
import org.example.enntity.Endpoint;
import org.example.enntity.HttpMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiGatewayEndpointConfigurationImpl implements ApiGatewayEndpointConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ApiGatewayEndpointConfigurationImpl.class);

    @PostConstruct
    public void startOperation(){
        initMap();
        register();
    }

    @Override
    public void initMap() {
        endpointList.add(new Endpoint("/api/v1/basket", HttpMethod.GET, Role.GUEST));
        endpointList.add(new Endpoint("/api/v1/basket", HttpMethod.POST, Role.GUEST));
        endpointList.add(new Endpoint("/api/v1/basket", HttpMethod.DELETE, Role.GUEST));
    }

    @Override
    public void register() {
        RestTemplate restTemplate = new RestTemplate();
        String GATEWAY_URL = "http://localhost:8888/api/v1/gateway";
        try {
            ResponseEntity<Response> response = restTemplate.postForEntity(GATEWAY_URL, endpointList, Response.class);
            if (response.getStatusCode().isError()) {
                throw new RuntimeException("Error response from gateway");
            }
        } catch (Exception e) {
            logger.error("Failed to register endpoints with API Gateway", e);
        }
    }
}
