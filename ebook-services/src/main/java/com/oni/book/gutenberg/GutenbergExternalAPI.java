package com.oni.book.gutenberg;

import com.oni.datatypes.shared.Constants;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Service
public class GutenbergExternalAPI {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public GutenbergExternalAPI(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<String> getBookDetails(@NotNull int bookId) {
        WebClient webClient =
                webClientBuilder
                        .clientConnector(new ReactorClientHttpConnector(HttpClient.create().followRedirect(true)))
                        .baseUrl(Constants.GUTENBERG_API_URL).build();

        return webClient.get()
                .uri(String.valueOf(bookId))
                .retrieve()
                .bodyToMono(String.class);
    }

}
