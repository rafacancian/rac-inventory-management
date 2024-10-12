package com.estoque.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.estoque.entities.Category;
import com.estoque.entities.Order;
import com.estoque.entities.OrderItem;
import com.estoque.entities.Payment;
import com.estoque.entities.Product;
import com.estoque.entities.User;
import com.estoque.entities.enums.OrderStatus;
import com.estoque.repositories.CategoryRepository;
import com.estoque.repositories.OrderItemRepository;
import com.estoque.repositories.OrderRepository;
import com.estoque.repositories.ProductRepository;
import com.estoque.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;


	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Hatch");
		Category cat2 = new Category(null, "Off Road");
		Category cat3 = new Category(null, "Conversivel");

		Product p1 = new Product(null, "HB20", "Carro da Hyundai.", 41000.0, "");
		Product p2 = new Product(null, "Onix", "Carro da Chevrolet.", 43200.0, "");
		Product p3 = new Product(null, "Ford Focus", "Carro da Ford.", 65000.0, "");
		Product p4 = new Product(null, "Jeep Renegate", "Carro da Jeep.", 71500.0, "");
		Product p5 = new Product(null, "Audi A3", "Carro da Audi.", 120000.0, "");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		p1.getCategories().add(cat1);
		p2.getCategories().add(cat1);
		p3.getCategories().add(cat1);
		p4.getCategories().add(cat2);
		p5.getCategories().add(cat3);

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		User u1 = new User(null, "Rafael", "rafael.test@gmail.com", "981488231", "123456");
		User u2 = new User(null, "Cancian", "cancian.test@gmail.com", "981488232", "123456");

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.WAITING_PAYMENT, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.PAID, u1);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:00Z"), OrderStatus.PAID, u2);
		Order o4 = new Order(null, Instant.parse("2019-07-23T10:01:12Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o5 = new Order(null, Instant.parse("2019-07-24T17:45:23Z"), OrderStatus.WAITING_PAYMENT, u1);

		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4, o5));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:00:07Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);

	}

}
