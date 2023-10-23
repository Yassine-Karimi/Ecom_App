package yas.kr.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import yas.kr.inventoryservice.entities.Product;
import yas.kr.inventoryservice.repositories.ProductRepository;

import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient
 public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			Random random = new Random();
			for(int i = 1; i < 10 ; i++) {
				productRepository.saveAll(List.of(

						Product.builder()
								.name("Computer" + i).
								quantity(1 + random.nextInt(200)).
								price(1200 * Math.random() * 10000).
								build()


				));
			}
			//productRepository.findAll().forEach(System.out::println);

	    };
   }
}
