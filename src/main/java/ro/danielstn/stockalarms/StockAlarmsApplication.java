package ro.danielstn.stockalarms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories
@EnableWebSecurity
public class StockAlarmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockAlarmsApplication.class, args);
	}

}
