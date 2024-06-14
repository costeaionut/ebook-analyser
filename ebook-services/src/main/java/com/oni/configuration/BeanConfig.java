package com.oni.configuration;

import com.oni.book.gutenberg.GutenbergExternalAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfig {

    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }

    @Bean
    public GutenbergExternalAPI gutenbergExternalAPI(){
        return new GutenbergExternalAPI(webClientBuilder());
    }
}
