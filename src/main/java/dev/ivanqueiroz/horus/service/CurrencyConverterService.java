package dev.ivanqueiroz.horus.service;

import dev.ivanqueiroz.horus.exchangesratesapi.ExchangeRatesClient;
import dev.ivanqueiroz.horus.exchangesratesapi.ExchangeResponse;
import dev.ivanqueiroz.horus.model.Currency;
import dev.ivanqueiroz.horus.repository.CurrencyRepository;
import dev.ivanqueiroz.horus.util.Calculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyConverterService {

  private final CurrencyRepository userTransactionRepository;
  private final ExchangeRatesClient exchangeRatesClient;

  public Currency calculateConversion(BigDecimal amount, Long userId, String currencySource, String currencyDestiny) {
    final Optional<ExchangeResponse> exchangeResponseOptional = exchangeRatesClient.callExchangeRatesApi(userId);
    Currency currency = new Currency();
    exchangeResponseOptional.ifPresent(exchangeResponse -> fillCurrency(userId, amount, currencySource, currencyDestiny, currency, exchangeResponse));
    userTransactionRepository.save(currency);
    log.info("{} saved.", currency);
    return currency;
  }

  private void fillCurrency(Long userId, BigDecimal amount, String currencySource, String currencyDestiny, Currency currency, ExchangeResponse exchangeResponse) {
    final BigDecimal sourceRate = exchangeResponse.getRates().get(currencySource);
    final BigDecimal destinyRate = exchangeResponse.getRates().get(currencyDestiny);
    final BigDecimal resultConversion = Calculator.convert(amount, sourceRate, destinyRate);
    final BigDecimal rate = Calculator.getRate(sourceRate, destinyRate);
    currency.setUserId(userId);
    currency.setAmount(amount);
    currency.setCurrencyDestiny(currencyDestiny);
    currency.setCurrencySource(currencySource);
    currency.setResult(resultConversion);
    currency.setCurrencyRate(rate);
    currency.setDate(Date.from(ZonedDateTime.now(ZoneId.of("UTC")).toInstant()));
  }

  public List<Currency> getAllTransactionsFromUser(Long userId){
    final List<Currency> allTransactions = userTransactionRepository.findByUserId(userId);
    log.info("User {} requested all transactions. Found: {} records", userId, allTransactions.size());
    return allTransactions;
  }
}
