package dev.ivanqueiroz.horus.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.reactive.function.client.WebClient;

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

}
