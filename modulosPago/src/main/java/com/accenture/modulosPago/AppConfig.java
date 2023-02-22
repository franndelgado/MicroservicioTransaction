package com.accenture.modulosPago;


import com.google.gson.Gson;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @LoadBalanced
    @Bean("clientRest")
    public RestTemplate registrarRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Mapper getMapper(){
        return new DozerBeanMapper();}

    @Bean
    public Gson getGson(){
        return new Gson();
    }
}
