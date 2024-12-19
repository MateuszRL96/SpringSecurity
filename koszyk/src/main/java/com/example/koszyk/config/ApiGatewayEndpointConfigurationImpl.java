package com.example.koszyk.config;

import com.example.koszyk.entity.Response;
import jakarta.annotation.PostConstruct;
import jakarta.websocket.Endpoint;
import lombok.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiGatewayEndpointConfigurationImpl implements ApiGatewayEndpointConfiguration {

    @Value("${api-gateway.url}")
    private String GATEWAY_URL;

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
        ResponseEntity<Response> response = restTemplate.postForEntity(GATEWAY_URL, endpointList, Response.class);
        if (response.getStatusCode().isError()) throw new RuntimeException();
    }
}
