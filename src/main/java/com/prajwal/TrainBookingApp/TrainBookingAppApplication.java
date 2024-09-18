package com.prajwal.TrainBookingApp;

import com.prajwal.TrainBookingApp.record.requests.RegisterRequest;
import com.prajwal.TrainBookingApp.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.prajwal.TrainBookingApp.model.Role.ADMIN;
import static com.prajwal.TrainBookingApp.model.Role.USER;

@SpringBootApplication
public class TrainBookingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainBookingAppApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(
//			AuthenticationService service
//	) {
//		return args -> {
//			var admin = RegisterRequest.builder()
//					.firstname("Admin")
//					.lastname("Admin")
//					.username("admin")
//					.password("admin")
//					.role(ADMIN)
//					.build();
//			System.out.println("Admin token: " + service.register(admin).getToken());
//
//			var user = RegisterRequest.builder()
//					.firstname("User")
//					.lastname("User")
//					.username("user")
//					.password("user")
//					.role(USER)
//					.build();
//			System.out.println("User token: " + service.register(user).getToken());
//
//		};
//	}
}
