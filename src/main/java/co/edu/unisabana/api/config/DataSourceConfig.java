package co.edu.unisabana.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private final Environment env;

    public DataSourceConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        String url = "jdbc:postgresql://hopper.proxy.rlwy.net:33079/railway";
        String username = "postgres";
        String password = "MlzylQcxaOrLddgRvFHFLgFMMdeCBbAp";

        /*if (url == null || username == null || password == null) {
            System.err.println("🚨 ERROR: Las variables de entorno para la base de datos no están configuradas correctamente.");
            System.err.println("SPRING_DATASOURCE_URL: " + url);
            System.err.println("SPRING_DATASOURCE_USERNAME: " + username);
            System.err.println("SPRING_DATASOURCE_PASSWORD: " + (password != null ? "********" : null));
            throw new RuntimeException("Las variables de entorno para la base de datos no están configuradas correctamente.");
        }*/

        System.out.println("✅ Conectando a la base de datos con:");
        System.out.println("URL: " + url);
        System.out.println("Usuario: " + username);

        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
