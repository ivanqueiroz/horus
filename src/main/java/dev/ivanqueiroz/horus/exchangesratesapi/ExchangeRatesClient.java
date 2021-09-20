package dev.ivanqueiroz.horus.exchangesratesapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeRatesClient {

  private final WebClient webClient;
  private final ExchangeClientProperties exchangeClientProperties;

  @Cacheable("rates")
  public Optional<ExchangeResponse> callExchangeRatesApi(Long userId) {
    if (userId > 0) {
      return this.getRate();
    }
    return Optional.empty();
  }

  public Optional<ExchangeResponse> getRate() {
    String baseUrl = exchangeClientProperties.getBaseUrl();
    String apiKey = exchangeClientProperties.getApiKey();
    String format = exchangeClientProperties.getFormat();
    log.info("Calling API {}", baseUrl);
    return webClient.get()
      .uri(baseUrl + "/latest?access_key={apiKey}&symbols={listCurrency}&format={format}", apiKey, "BRL,USD,EUR,JPY", format)
      .retrieve()
      .bodyToMono(ExchangeResponse.class)
      .blockOptional();
  }

}
