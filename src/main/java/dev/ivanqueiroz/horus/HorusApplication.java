package dev.ivanqueiroz.horus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Objects;

import static dev.ivanqueiroz.horus.util.Constants.HOUR;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@ConfigurationPropertiesScan({"dev.ivanqueiroz.horus.exchangesratesapi"})
@Slf4j
public class HorusApplication {

  @Autowired
  private CacheManager cacheManager;

  public static void main(String[] args) {
    SpringApplication.run(HorusApplication.class, args);
  }

  @Scheduled(fixedRate = 2 * HOUR)
  public void evictAllCaches() {
    log.info("Cleaning caches.");
    cacheManager.getCacheNames()
      .forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
  }

}
