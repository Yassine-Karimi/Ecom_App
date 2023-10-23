package yas.kr.orderservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import yas.kr.orderservice.entities.Order;
import yas.kr.orderservice.entities.ProductItem;
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
import java.util.stream.Collectors;

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

		String customerList= customers.stream().map(Customer::toString).collect(Collectors.joining(", "));
		System.out.println(customerList);

		List<Product> products =inventoryRestClientService.allProducts().getContent().stream().toList();
		String productString = products.stream()
				.map(Product::toString)  // Assuming Product has a reasonable toString() method
				.collect(Collectors.joining(", "));
		System.out.println(productString);
		Long customerId =1L;
		Random random =new Random();
		Customer customer=customerRestClientService.customerById(customerId);
		for (int i = 0; i < 20; i++) {
           Order order = Order.builder()
				   .customerId(customers.get(random.nextInt(customers.size())).getId())
				   .status(Math.random()>0.5? OrderStatus.PENDING:OrderStatus.CREATED)
				   .createAt(new Date())
				   .build();

		   Order savedOrder = orderRepository.save(order);
			for (int j = 0; j < products.size(); j++) {
				if(Math.random()>0.70) {
					ProductItem productItem = ProductItem.builder()
							.order(savedOrder)
							.productId(products.get(j).getId())
							.price(products.get(j).getPrice())
							.quantity(1 + random.nextInt(10))
							.discount(Math.random())
							.build();
					productItemRepository.save(productItem);
				}

			}
		}

	};



	}

}
