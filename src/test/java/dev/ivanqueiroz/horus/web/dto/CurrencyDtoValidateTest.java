package dev.ivanqueiroz.horus.web.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CurrencyDtoValidateTest {
  private Validator validator;

  @BeforeEach
  void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void checkValidationErrors() {
    CurrencyDto currencyDto = new CurrencyDto();
    currencyDto.setCurrencyRate(new BigDecimal("1.0"));
    currencyDto.setUserId(1L);
    Set<ConstraintViolation<CurrencyDto>> violations = validator.validate(currencyDto);
    assertFalse(violations.isEmpty());
  }

  @Test
  void checkCurrencyValidation() {
    CurrencyDto currencyDto = new CurrencyDto();
    final BigDecimal one = new BigDecimal("1.0");
    currencyDto.setCurrencyRate(one);
    currencyDto.setUserId(1L);
    currencyDto.setAmount(one);
    currencyDto.setCurrencySource("BRL");
    currencyDto.setCurrencyDestiny("JPY");
    Set<ConstraintViolation<CurrencyDto>> violations = validator.validate(currencyDto);
    assertTrue(violations.isEmpty());

    currencyDto.setCurrencyDestiny("FFFF");
    violations = validator.validate(currencyDto);
    assertFalse(violations.isEmpty());
  }

}
