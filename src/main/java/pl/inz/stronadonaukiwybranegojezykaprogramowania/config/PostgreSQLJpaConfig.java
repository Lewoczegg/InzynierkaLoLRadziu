package pl.inz.stronadonaukiwybranegojezykaprogramowania.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Profile("postgresql")
@EntityScan(basePackages = "pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity")
@EnableJpaRepositories(basePackages = "pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository")
public class PostgreSQLJpaConfig {
}
