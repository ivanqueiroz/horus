package dev.ivanqueiroz.horus.web.controller;

import dev.ivanqueiroz.horus.model.Currency;
import dev.ivanqueiroz.horus.service.CurrencyConverterService;
import dev.ivanqueiroz.horus.web.dto.CurrencyDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = "Application Operations")
@RestController
@RequestMapping(path = "/currency")
@RequiredArgsConstructor
public class CurrencyController {

  private final CurrencyConverterService currencyConverterService;
  private final ModelMapper modelMapper;

  @ResponseBody
  @GetMapping(value = "/convert")
  @ApiOperation(value = "Convert between two currencies ")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Return currency transaction data."), @ApiResponse(code = 400, message = "You dont have permission to use this resource."),
    @ApiResponse(code = 500, message = "Internal exception."),})
  public CurrencyDto convert(@Valid CurrencyDto currencyDto) {
    log.info("Method convert called with parameters {}.", currencyDto);
    return convertToDto(currencyConverterService.calculateConversion(currencyDto.getAmount(), currencyDto.getUserId(), currencyDto.getCurrencySource(), currencyDto.getCurrencyDestiny()));
  }

  private CurrencyDto convertToDto(Currency currency) {
    final CurrencyDto currencyDto = modelMapper.map(currency, CurrencyDto.class);
    currencyDto.setTransactionId(currency.getId());
    if (log.isDebugEnabled()) {
      log.debug("Currency {} converted to dto {}.", currency, currencyDto);
    }
    return currencyDto;
  }

  @GetMapping("/transactions")
  @ApiOperation(value = "Return a list with 0 o more transactions of informed user id.")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Return transaction list of user id"), @ApiResponse(code = 400, message = "You dont have permission to use this resource."),
    @ApiResponse(code = 500, message = "Internal exception."),})
  public List<CurrencyDto> getAllTransactions(@RequestParam @ApiParam(value = "User identification.", example = "1") Long userId) {
    log.info("Method allTransactions called with parameter {}.", userId);
    return currencyConverterService.getAllTransactionsFromUser(userId).stream().map(this::convertToDto).collect(Collectors.toList());
  }

}
