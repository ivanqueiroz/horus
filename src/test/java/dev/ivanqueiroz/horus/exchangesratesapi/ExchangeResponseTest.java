package dev.ivanqueiroz.horus.exchangesratesapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class ExchangeResponseTest {

  @Autowired
  private JacksonTester<ExchangeResponse> jacksonTester;

  @Test
  void deserializeFromCorrectFormat() throws IOException {
    String json = "{ \"success\":true, \"timestamp\":1631958243, \"base\":\"EUR\", \"date\":\"2021-09-18\", \"rates\":{ \"BRL\":6.201951, \"USD\":1.17259, \"EUR\":1, \"JPY\":128.901692 } }";

    final BigDecimal expectedBrlRate = new BigDecimal("128.901692");
    final GregorianCalendar gregorianCalendar = new GregorianCalendar(2021, Calendar.SEPTEMBER, 18);
    gregorianCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    final Date expectedDate = gregorianCalendar.getTime();

    ExchangeResponse exchangeResponse = jacksonTester.parseObject(json);

    assertEquals(expectedBrlRate, exchangeResponse.getRates().get("JPY"));
    assertEquals(expectedDate, exchangeResponse.getDate());
    assertEquals(Boolean.TRUE, exchangeResponse.getSuccess());
    assertEquals(1631958243L, exchangeResponse.getTimestamp());
    assertEquals("EUR", exchangeResponse.getBase());

  }

}
