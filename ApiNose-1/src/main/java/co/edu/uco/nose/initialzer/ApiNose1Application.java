package co.edu.uco.nose.initialzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "co.edu.uco.nose" })
@SpringBootApplication
public class ApiNose1Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiNose1Application.class, args);
	}

}
