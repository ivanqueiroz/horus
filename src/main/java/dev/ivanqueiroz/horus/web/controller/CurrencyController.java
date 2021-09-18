package dev.ivanqueiroz.horus.web.controller;

import dev.ivanqueiroz.horus.model.Currency;
import dev.ivanqueiroz.horus.service.CurrencyConverterService;
import dev.ivanqueiroz.horus.web.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/currency")
@RequiredArgsConstructor
public class CurrencyController {

  private final CurrencyConverterService currencyConverterService;

  @ResponseBody
  @GetMapping(value = "/convert")
  public CurrencyDto convert(@RequestParam BigDecimal amount, @RequestParam Long userId, @RequestParam String currencySource, @RequestParam String currencyDestiny) {
    final Currency currency = currencyConverterService.calculateConversion(amount, userId, currencySource, currencyDestiny);
    CurrencyDto currencyDto = new CurrencyDto();
    currencyDto.setUserId(userId);
    currencyDto.setDate(currency.getDate());
    currencyDto.setCurrencyRate(currency.getCurrencyRate());
    currencyDto.setAmount(currency.getAmount());
    currencyDto.setResult(currency.getResult());
    currencyDto.setCurrencySource(currency.getCurrencySource());
    currencyDto.setCurrencyDestiny(currency.getCurrencyDestiny());
    return currencyDto;
  }

}
