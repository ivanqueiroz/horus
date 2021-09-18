package dev.ivanqueiroz.horus.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

  @Test
  void convert() {
    BigDecimal expectedJpn = new BigDecimal("103.920277");
    final BigDecimal sourceRateBrl = new BigDecimal("6.201951");
    final BigDecimal destinyRateJpn = new BigDecimal("128.901692");
    final BigDecimal amount = new BigDecimal("5");
    final BigDecimal resultJpn = Calculator.convert(amount, sourceRateBrl, destinyRateJpn);
    assertEquals(expectedJpn, resultJpn);
  }

  @Test
  void getRate() {
    final BigDecimal sourceRateBrl = new BigDecimal("6.201951");
    final BigDecimal destinyRateJpn = new BigDecimal("128.901692");
    final BigDecimal resultJpn = Calculator.getRate(sourceRateBrl, destinyRateJpn);
    final BigDecimal expectedJpnRate = new BigDecimal("20.784056");
    assertEquals(expectedJpnRate, resultJpn);
  }
}
