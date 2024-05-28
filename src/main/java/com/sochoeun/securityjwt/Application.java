package com.sochoeun.securityjwt;

import com.sochoeun.securityjwt.model.Role;
import com.sochoeun.securityjwt.model.User;
import com.sochoeun.securityjwt.repository.RoleRepository;
import com.sochoeun.securityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			UserRepository userRepository,
			RoleRepository roleRepository
	) {
		return args -> {
			if (roleRepository.findByName("SUPER-ADMIN").isEmpty()) {
				roleRepository.save(Role.builder().name("SUPER-ADMIN").build());
			}

			User super_admin = User.builder()
					.firstname("SUPER")
					.lastname("ADMIN")
					.email("super-admin@gmail.com")
					.password(passwordEncoder.encode("super-admin"))
					.profile("")
					.status(true)
					.roles(roleRepository.findAll())
					.build();
			userRepository.save(super_admin);
		};
	}

}
