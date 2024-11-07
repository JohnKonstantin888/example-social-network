package com.example.examplesocialnetwork.config;

import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MainConfiguration {

    @Bean
    public JsonMapper jsonMapper() {
        return new JsonMapper();
    }
}
