package com.nttdata.reportingservice.configuration;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

/**
 * Class R2dbcConfiguration.
 */
@Configuration
@EnableR2dbcRepositories()
public class R2dbcConfiguration extends AbstractR2dbcConfiguration {

  @Value("${spring.r2dbc.h2.url}")
  private String url;

  /**
   * Method connectionFactory.
   */
  @Bean
  public H2ConnectionFactory connectionFactory() {
    return new H2ConnectionFactory(
            H2ConnectionConfiguration.builder()
                    .url(url)
                    .username("sa")
                    .build()
    );
  }

  @Bean()
  ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
    ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
    initializer.setConnectionFactory(connectionFactory);
    initializer.setDatabasePopulator(
            new ResourceDatabasePopulator(
                    new ClassPathResource("db_setup.sql")
            )
    );
    /*initializer.setDatabaseCleaner(
            new ResourceDatabasePopulator(
                    new ClassPathResource("cleanup.sql")
            )
    );*/
    return initializer;
  }

}
