package dev.ivanqueiroz.horus.exchangesratesapi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "exchange-rate-api")
public class ExchangeClientProperties {
  private String baseUrl;
  private String apiKey;
  private String format;
}
