package co.edu.unisabana.api.config;

import org.springframework.boot.ssl.NoSuchSslBundleException;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@Configuration
public class SslConfig {
    @Bean
    public SslBundles sslBundles() {
        return new SslBundles() {
            @Override
            public SslBundle getBundle(String name) throws NoSuchSslBundleException {
                return null;
            }

            @Override
            public void addBundleUpdateHandler(String name, Consumer<SslBundle> updateHandler) throws NoSuchSslBundleException {

            }

            @Override
            public List<String> getBundleNames() {
                return List.of();
            }
        };
    }
}

