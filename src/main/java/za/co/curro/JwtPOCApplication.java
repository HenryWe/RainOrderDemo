package za.co.curro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class JwtPOCApplication implements CommandLineRunner {

	Environment environment;

	public JwtPOCApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(JwtPOCApplication.class, args);
	}

	@Override
	public void run(String... args) {

		log.info("Active Profile: " + environment.getActiveProfiles().toString());
	}
}
