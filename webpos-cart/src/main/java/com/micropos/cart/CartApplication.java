package com.micropos.cart;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;
import com.micropos.cart.repository.CartRepository;
import com.micropos.cart.repository.ItemRepository;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@SpringBootApplication
@EnableDiscoveryClient
public class CartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {

		TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(1)).build();
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom().failureRateThreshold(50)
				.waitDurationInOpenState(Duration.ofMillis(1000)).slidingWindowSize(2).build();

		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.timeLimiterConfig(timeLimiterConfig).circuitBreakerConfig(circuitBreakerConfig).build());

	}

	@Autowired
	@Bean
	CommandLineRunner run (ItemRepository itemRepository, CartRepository cartRepository) {

		Cart cart1 = cartRepository.save(new Cart());
		Cart cart2 = cartRepository.save(new Cart());

		List<Item> items1 = new ArrayList<>();
		List<Item> items2 = new ArrayList<>();

		items1.add(new Item(null, cart1.getId(), "PD1", "iPhonoe 15", 5999, "1.png", 1));
		items1.add(new Item(null, cart1.getId(), "PD2", "iPhonoe 15 PLUS", 6999, "2.png", 2));
		items1.add(new Item(null, cart1.getId(), "PD3", "iPhonoe 15 Pro", 7999, "3.png", 3));
		items1.add(new Item(null, cart1.getId(), "PD4", "iPhonoe 15 Pro Max", 8999, "4.png", 4));

		items2.add(new Item(null, cart2.getId(), "PD14", "AirPods", 999, "14.png", 14));
		items2.add(new Item(null, cart2.getId(), "PD15", "AirPods Pro", 1999, "15.png", 15));
		items2.add(new Item(null, cart2.getId(), "PD16", "AirPods Max", 3999, "16.png", 16));

		cart1.setItems(items1);
		cart2.setItems(items2);

		return args -> {
			itemRepository.saveAll(items1);
			itemRepository.saveAll(items2);
			cartRepository.save(cart1);
			cartRepository.save(cart2);
		};

	}

}
