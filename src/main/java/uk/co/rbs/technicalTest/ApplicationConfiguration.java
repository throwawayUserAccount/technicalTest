package uk.co.rbs.technicalTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 11/22/16.
 */
@Configuration
@ComponentScan
class ApplicationConfiguration {

  @Bean
  public Application application(){
    return new Application();
  }
}