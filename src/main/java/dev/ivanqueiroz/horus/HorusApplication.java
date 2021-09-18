package dev.ivanqueiroz.horus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan({"dev.ivanqueiroz.horus.exchangesratesapi"})
public class HorusApplication {

  public static void main(String[] args) {
    SpringApplication.run(HorusApplication.class, args);
  }

}
