package dev.ivanqueiroz.horus.web.controller;

import dev.ivanqueiroz.horus.model.Currency;
import dev.ivanqueiroz.horus.service.CurrencyConverterService;
import dev.ivanqueiroz.horus.web.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/currency")
@RequiredArgsConstructor
public class CurrencyController {

  private final CurrencyConverterService currencyConverterService;
  private final ModelMapper modelMapper;

  @ResponseBody
  @GetMapping(value = "/convert")
  public CurrencyDto convert(@RequestParam BigDecimal amount, @RequestParam Long userId, @RequestParam String currencySource, @RequestParam String currencyDestiny) {
    return convertToDto(currencyConverterService.calculateConversion(amount, userId, currencySource, currencyDestiny));
  }

  private CurrencyDto convertToDto(Currency currency) {
    final CurrencyDto currencyDto = modelMapper.map(currency, CurrencyDto.class);
    currencyDto.setTransactionId(currency.getId());
    return currencyDto;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

}
