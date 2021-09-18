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
  private Long transactionId;
  @NotBlank(message = "User ID is mandatory.")
  private Long userId;
  @NotBlank(message = "Source currency is mandatory (Valid options: BRL, USD, EUR, JPY)")
  private String currencySource;
  @PositiveOrZero(message = "Amount is mandatory.")
  @Digits(integer=6, fraction=2, message = "Amount out of range.")
  private BigDecimal amount;
  @NotBlank(message = "Destiny currency is mandatory (Valid options: BRL, USD, EUR, JPY)")
  private String currencyDestiny;
  private BigDecimal result;
  private BigDecimal currencyRate;
  private Date date;

}
