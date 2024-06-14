package com.oni.book.gutenberg;

import com.oni.datatypes.gutenberg.raw.RawBook;
import com.oni.datatypes.shared.Constants;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Service
public class GutenbergExternalAPI {

    private final WebClient.Builder webClientBuilder;
    private final ReactorClientHttpConnector followRedirectConnector =
            new ReactorClientHttpConnector(HttpClient.create().followRedirect(true));

    @Autowired
    public GutenbergExternalAPI(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public RawBook getRawBook(@NotNull int bookId) {
        WebClient webClient =
                webClientBuilder
                        .clientConnector(followRedirectConnector)
                        .baseUrl(Constants.GUTENBERG_API_URL)
                        .build();

        return webClient.get()
                .uri(String.valueOf(bookId))
                .retrieve()
                .bodyToMono(RawBook.class)
                .block();
    }

    public String getBookContents(@NotNull String bookContentUrl) {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        WebClient webClient =
                webClientBuilder
                        .clientConnector(followRedirectConnector)
                        .exchangeStrategies(strategies)
                        .baseUrl(bookContentUrl)
                        .build();

        return webClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
