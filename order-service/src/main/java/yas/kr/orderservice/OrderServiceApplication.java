package yas.kr.orderservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import yas.kr.orderservice.entities.Order;
import yas.kr.orderservice.enums.OrderStatus;
import yas.kr.orderservice.model.Customer;
import yas.kr.orderservice.model.Product;
import yas.kr.orderservice.repository.OrderRepository;
import yas.kr.orderservice.repository.ProductItemRepository;
import yas.kr.orderservice.services.CustomerRestClientService;
import yas.kr.orderservice.services.InventoryRestClientService;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
@Bean
	CommandLineRunner start(
			OrderRepository orderRepository,
			ProductItemRepository productItemRepository,
			CustomerRestClientService customerRestClientService,
			InventoryRestClientService inventoryRestClientService)
{
	return args -> {
		List<Customer> customers=customerRestClientService.allCustomer().getContent().stream().toList();
		List<Product> products =inventoryRestClientService.allProducts().getContent().stream().toList();
		Long customerId =1L;
		Random random =new Random();
		Customer customer=customerRestClientService.customerById(customerId);
		for (int i = 0; i < 20; i++) {
           Order order = Order.builder()
				   .customerId(customers.get(random.nextInt(customers.size())).getId())
				   .status(Math.random()>0.5? OrderStatus.PENDING:OrderStatus.CREATED)
				   .createAt(new Date())
				   .build();
		}

	};



	}

}
