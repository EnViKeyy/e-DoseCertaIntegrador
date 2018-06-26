package br.unicentro.e_dosecerta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EDoseCertaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EDoseCertaApplication.class, args);
	}
}
