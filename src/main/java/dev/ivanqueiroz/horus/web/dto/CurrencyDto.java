package dev.ivanqueiroz.horus.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CurrencyDto {
  @NotBlank(message = "User ID is mandatory.")
  private Long userId;

  @NotBlank(message = "Source currency is mandatory (Valid options: BRL, USD, EUR, JPY)")
  private String currencySource;
  @NotBlank(message = "Destiny currency is mandatory (Valid options: BRL, USD, EUR, JPY)")
  private String currencyDestiny;
  private BigDecimal currencyRate;
  @PositiveOrZero(message = "Amount is mandatory.")
  @Digits(integer=6, fraction=2, message = "Amount out of range.")
  private BigDecimal amount;
  private Date date;
  private BigDecimal result;
  private Long transactionId;
}
