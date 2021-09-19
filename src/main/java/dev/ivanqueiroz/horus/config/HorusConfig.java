package dev.ivanqueiroz.horus.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class HorusConfig {

  @Bean
  public WebClient webClient() {
    return WebClient.create();
  }

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    return modelMapper;
  }

  @Bean
  public TaskExecutor taskExecutor() {
    return new SimpleAsyncTaskExecutor();
  }

  @Bean
  public CacheManager cacheManager() {
    final ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
    concurrentMapCacheManager.setAllowNullValues(false);
    return concurrentMapCacheManager;
  }

  @Bean
  public Docket api() {
    final ApiInfo apiInfo = new ApiInfo("Horus Currency Converter REST API", "The API realizes conversion between currencies of 4 types: (BRL, USD, EUR, JPY).",
       "1.0.0-RC1", "", new Contact("Ivan Queiroz", "", "ivanqueiroz@gmail.com"), "", "", Collections.emptyList());
    final List<Response> globalResponses = Arrays.asList(
      new ResponseBuilder()
           .code("200")
           .description("OK")
           .build(),
       new ResponseBuilder()
           .code("400")
           .description("Bad Request")
           .build(),
       new ResponseBuilder()
           .code("500")
           .description("Internal Error")
           .build());
    return new Docket(DocumentationType.SWAGGER_2)
      .useDefaultResponseMessages(false)
       .globalResponses(HttpMethod.GET, globalResponses)
       .globalResponses(HttpMethod.POST, globalResponses)
       .globalResponses(HttpMethod.DELETE, globalResponses)
       .globalResponses(HttpMethod.PATCH, globalResponses)
      .select()
      .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
      .paths(PathSelectors.any())
      .build()
      .apiInfo(apiInfo);
  }

}
