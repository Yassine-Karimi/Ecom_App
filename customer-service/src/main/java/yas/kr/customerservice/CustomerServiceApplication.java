package yas.kr.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import yas.kr.customerservice.entities.Customer;
import yas.kr.customerservice.repository.CustomerRepository;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
           customerRepository.saveAll(List.of(
				   Customer.builder().name("yassine").email("yassine@gmail.com").build(),
				   Customer.builder().name("med").email("med@gmail.com").build(),
				   Customer.builder().name("laila").email("laila@gmail.com").build()

		   ));
             customerRepository.findAll().forEach(System.out::println);
		};
	}

}
