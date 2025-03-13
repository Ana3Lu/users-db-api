package co.edu.unisabana.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "co.edu.unisabana.api.db.jpa")
@EntityScan(basePackages = "co.edu.unisabana.api.db.orm")
public class ApiApplication {

	public static void main(String[] args) {
		// Leer el perfil desde el Manifest si existe
		String profile = System.getProperty("spring.profiles.active");
		if (profile == null) {
			try (InputStream manifestStream = ApiApplication.class.getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF")) {
				if (manifestStream != null) {
					Properties properties = new Properties();
					properties.load(manifestStream);
					profile = properties.getProperty("Spring-Boot-Profile", "dev");
					System.setProperty("spring.profiles.active", profile);
				}
			} catch (Exception ignored) {
			}
		}

		System.out.println("🔹 Iniciando API con perfil: " + System.getProperty("spring.profiles.active"));
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("co.edu.unisabana.api.db.orm");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return em;
	}
}
