package dev.ivanqueiroz.horus.web.controller;

import dev.ivanqueiroz.horus.model.Currency;
import dev.ivanqueiroz.horus.service.CurrencyConverterService;
import dev.ivanqueiroz.horus.web.dto.CurrencyDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/currency")
@RequiredArgsConstructor
public class CurrencyController {

  private final CurrencyConverterService currencyConverterService;
  private final ModelMapper modelMapper;

  @ResponseBody
  @GetMapping(value = "/convert")
  @ApiOperation(value = "Convert between two currencies ")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Return currency transaction data."),
    @ApiResponse(code = 400, message = "You dont haver permission to use this resource."),
    @ApiResponse(code = 500, message = "Internal exception."),
  })
  public CurrencyDto convert(@Valid CurrencyDto currencyDto) {
    return convertToDto(currencyConverterService.calculateConversion(currencyDto.getAmount(), currencyDto.getUserId(), currencyDto.getCurrencySource(), currencyDto.getCurrencyDestiny()));
  }

  private CurrencyDto convertToDto(Currency currency) {
    final CurrencyDto currencyDto = modelMapper.map(currency, CurrencyDto.class);
    currencyDto.setTransactionId(currency.getId());
    return currencyDto;
  }

  @GetMapping("/transactions")
  @ApiOperation(value = "Return a list with 0 o more transactions of informed user id.")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Return transaction list of user id"),
    @ApiResponse(code = 400, message = "You dont haver permission to use this resource."),
    @ApiResponse(code = 500, message = "Internal exception."),
  })
  public List<CurrencyDto> getAllTransactions(@RequestParam @ApiParam(value = "User identification.", example = "1") Long userId) {
    return currencyConverterService.getAllTransactionsFromUser(userId).stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BindException.class)
  public Map<String, String> handleValidationExceptions(BindException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

}
