package dev.ivanqueiroz.horus.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CurrencyDto {
  private Long userId;
  private String currencySource;
  private String currencyDestiny;
  private BigDecimal currencyRate;
  private BigDecimal amount;
  private Date date;
  private BigDecimal result;
}
