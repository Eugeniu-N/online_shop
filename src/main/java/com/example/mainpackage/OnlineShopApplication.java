package com.example.mainpackage;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.mainpackage.items.Item;
import com.example.mainpackage.items.ItemService;
import com.example.mainpackage.orders.ItemOrder;
import com.example.mainpackage.orders.ItemOrderService;
import com.example.mainpackage.security.ApplicationUser;
import com.example.mainpackage.security.ApplicationUserRole;
import com.example.mainpackage.security.ApplicationUserServiceImpl1;

@SpringBootApplication
public class OnlineShopApplication {

	@Autowired
	private ApplicationUserServiceImpl1 applicationUserService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}
	
	//Insert a few customers into the table and execute some test query
	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			
			ApplicationUser admin = new ApplicationUser(ApplicationUserRole.ADMIN, "admin", passwordEncoder.encode("admin"), true, true, true, true);
			ApplicationUser standard = new ApplicationUser(ApplicationUserRole.STANDARD_USER, "user", passwordEncoder.encode("user"), true, true, true, true);
			
			this.applicationUserService.save(admin);
			this.applicationUserService.save(standard);
			
			Iterable<ApplicationUser> iter = (Iterable<ApplicationUser>) this.applicationUserService.getAllApplicationUsers();
			for (ApplicationUser user: iter) {
				System.out.println(user.toString());
			}
			
			Random randomGenerator = new Random(System.currentTimeMillis());
			for (int i = 0; i < 100; i++) {
				itemService.addNewItem(new Item(UUID.randomUUID().toString(), randomGenerator.nextInt(500)));
			}
			Iterable<Item> iter2 = (Iterable<Item>) itemService.findAll();
			for (Item item: iter2) {
				//System.out.println(item.toString());
			}
		};
	}

}
