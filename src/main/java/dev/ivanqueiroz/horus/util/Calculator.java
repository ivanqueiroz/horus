package dev.ivanqueiroz.horus.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

  private Calculator() {
    throw new IllegalAccessError("You couldn't access here!");
  }

  public static BigDecimal convert(BigDecimal value, BigDecimal sourceRate, BigDecimal destinyRate) {
    return value.multiply(destinyRate).divide(sourceRate, 6, RoundingMode.UP);
  }

  public static BigDecimal getRate(BigDecimal sourceRate, BigDecimal destinyRate){
    return destinyRate.divide(sourceRate, 6, RoundingMode.UP);
  }

}
