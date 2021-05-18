package com.lczapparolli.avaliacao.goldenraspberyawards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GoldenRaspberyAwardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoldenRaspberyAwardsApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return String.format("Rest server is running");
	}
}
