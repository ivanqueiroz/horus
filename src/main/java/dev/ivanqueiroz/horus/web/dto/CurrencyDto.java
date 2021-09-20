package dev.ivanqueiroz.horus.web.dto;

import dev.ivanqueiroz.horus.validator.ValidCurrency;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class CurrencyDto {

  @ApiModelProperty(hidden = true)
  private Long transactionId;

  @ApiModelProperty(value = "User identification.", example = "2")
  @NotNull(message = "User ID is mandatory.")
  @PositiveOrZero(message = "User ID is mandatory.")
  private Long userId;

  @ApiModelProperty(value = "Source currency of amount.")
  @ValidCurrency
  @NotBlank(message = "Source currency is mandatory (Valid options: BRL, USD, EUR, JPY)")
  private String currencySource;

  @ApiModelProperty(value = "Amount to be converted.", example = "100.20")
  @NotNull(message = "Amount is mandatory.")
  @PositiveOrZero(message = "Amount is mandatory.")
  @Digits(integer = 6, fraction = 2, message = "Amount out of range.")
  private BigDecimal amount;

  @ApiModelProperty(value = "Destiny currency of amount.")
  @ValidCurrency
  @NotBlank(message = "Destiny currency is mandatory (Valid options: BRL, USD, EUR, JPY)")
  private String currencyDestiny;

  @ApiModelProperty(hidden = true)
  private BigDecimal result;

  @ApiModelProperty(hidden = true)
  private BigDecimal currencyRate;

  @ApiModelProperty(hidden = true)
  private Date date;

}
