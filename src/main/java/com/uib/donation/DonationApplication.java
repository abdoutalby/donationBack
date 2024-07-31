package com.uib.donation;

import com.uib.donation.models.User;
import com.uib.donation.repositories.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "My API",
				version = "1.0",
				description = "API documentation"
		)
)
public class DonationApplication  implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DonationApplication.class);
	@Autowired
private UserRepository userRepository;

@Autowired
private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DonationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<User> optionalUser = userRepository.findByEmail("admin@admin.com") ;
		if(optionalUser.isEmpty()){
		User user = new User();
		user.setEmail("admin@admin.com");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setUserType(User.UserType.ADMIN);
		user.setUsername("admin");
		user.setStatus(User.UserStatus.ENABLED);
		user.setName("admin");
		userRepository.save(user);
		log.info("admin account setup succ");
	} else {
		log.info("admin already exist ");
		}
	}
}
