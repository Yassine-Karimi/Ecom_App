package yas.kr.billingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.Versioned;

import java.util.Map;

@SpringBootApplication
public class BillingServiceApplication {
	@Autowired
    private VaultTemplate vaultTemplate;
	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
			Versioned.Metadata resp= vaultTemplate
					.opsForVersionedKeyValue("secret")
					.put("keyPair", Map.of("privKey","54321","pubKey","8999"));
		};
	}




}
