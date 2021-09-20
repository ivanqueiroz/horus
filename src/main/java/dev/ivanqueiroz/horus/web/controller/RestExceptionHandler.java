package dev.ivanqueiroz.horus.web.controller;

import dev.ivanqueiroz.horus.web.dto.ApiErrorDto;
import dev.ivanqueiroz.horus.web.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ApiErrorDto> handlerMethodThrowable(Throwable ex) {
    log.error("Handling unknown error.", ex);
    Set<ErrorDto> errors = Set.of(buildError("Unknown Error", ex.getMessage()));
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseErrorBuilder(HttpStatus.INTERNAL_SERVER_ERROR, errors));
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<ApiErrorDto> handleValidationExceptions(BindException ex) {
    log.error("Handling validation error.", ex);
    Set<ErrorDto> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> buildError(error.getCode(), error.getDefaultMessage())).collect(Collectors.toSet());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseErrorBuilder(HttpStatus.BAD_REQUEST, errors));
  }

  private ErrorDto buildError(String code, String message) {
    return new ErrorDto(code, message);
  }

  private ApiErrorDto baseErrorBuilder(HttpStatus httpStatus, Set<ErrorDto> errorList) {
    return new ApiErrorDto(new Date(), httpStatus.value(), httpStatus.name(), errorList);
  }
}
