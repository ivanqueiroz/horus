package dev.ivanqueiroz.horus.exchangesratesapi;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRatesClientTest {

  private MockWebServer mockWebServer;
  private ExchangeRatesClient exchangeRateClient;

  @BeforeEach
  void setupMockWebServer() {
    mockWebServer = new MockWebServer();

    ExchangeClientProperties properties = new ExchangeClientProperties();
    properties.setBaseUrl(mockWebServer.url("/").url().toString());
    properties.setApiKey("ba99491b4d18b57a0ebd5779f62d745c");
    properties.setFormat("1");

    exchangeRateClient = new ExchangeRatesClient(WebClient.create(), properties);
  }

  @Test
  void getRate() {
    String json = "{ \"success\":true, \"timestamp\":1631958243, \"base\":\"EUR\", \"date\":\"2021-09-18\", \"rates\":{ \"BRL\":6.201951, \"USD\":1.17259, \"EUR\":1, \"JPY\":128.901692 } }";

    mockWebServer.enqueue(
      new MockResponse().setResponseCode(200)
        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .setBody(json)
    );

    final BigDecimal expectedBrlRate = new BigDecimal("128.901692");
    final Optional<ExchangeResponse> rate = exchangeRateClient.getRate();
    assertEquals(expectedBrlRate,rate.orElse(new ExchangeResponse()).getRates().get("JPY"));
  }
}
