package ex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ClienteApiRest2Application {
	@GetMapping("/api")
	public String ola() {
		return "Ola mundo";
	}
	public static void main(String[] args) {
		SpringApplication.run(ClienteApiRest2Application.class, args);
	}

}
