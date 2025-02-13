package pl.szlify.codingapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.szlify.codingapi.model.TeacherEntity;
import pl.szlify.codingapi.security.XSSFilter;
import pl.szlify.codingapi.repository.TeacherRepository;

@EnableJpaRepositories(basePackages = "pl.szlify.codingapi.repository")
@EntityScan(basePackages = "pl.szlify.codingapi.model")
@SpringBootApplication
public class CodingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingApiApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<XSSFilter> xssFilter() {
		FilterRegistrationBean<XSSFilter> filterRegistration = new FilterRegistrationBean<>();
		filterRegistration.setFilter(new XSSFilter());
		filterRegistration.addUrlPatterns("/*");
		return filterRegistration;
	}

	@Bean
	public CommandLineRunner initAdminUser(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (teacherRepository.findByUsername("admin").isEmpty()) {
				TeacherEntity admin = new TeacherEntity();
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("admin"));
				admin.setRole("ROLE_ADMIN");
				admin.setFirstName("Admin");
				admin.setLastName("User");
				admin.setDeleted(false);

				teacherRepository.save(admin);
				System.out.println("✅ Admin user created: admin/admin");
			} else {
				System.out.println("🔹 Admin user already exists, skipping creation.");
			}
		};
	}
}
