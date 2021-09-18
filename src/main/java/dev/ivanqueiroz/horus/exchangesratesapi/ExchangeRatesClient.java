package dev.ivanqueiroz.horus.exchangesratesapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExchangeRatesClient {

  private final WebClient webClient;
  private final ExchangeClientProperties exchangeClientProperties;

  public Optional<ExchangeResponse> getRate() {
    String baseUrl = exchangeClientProperties.getBaseUrl();
    String apiKey = exchangeClientProperties.getApiKey();
    String format = exchangeClientProperties.getFormat();

    return webClient.get()
      .uri(baseUrl + "/latest?access_key={apiKey}&symbols={listCurrency}&format={format}", apiKey, "BRL,USD,EUR,JPY", format)
      .retrieve()
      .bodyToMono(ExchangeResponse.class)
      .blockOptional();
  }
}
