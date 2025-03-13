package co.edu.unisabana.api.handlers;

import co.edu.unisabana.api.ApiApplication;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import jakarta.servlet.ServletContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.ServletContextAware;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SpringBootApplication(scanBasePackages = "co.edu.unisabana.api")
public class LambdaHandler extends SpringBootServletInitializer implements RequestStreamHandler, ServletContextAware {

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    private ServletContext servletContext;

    static {
        try {
            handler = new SpringBootProxyHandlerBuilder<AwsProxyRequest>()
                    .defaultProxy()
                    .springBootApplication(ApiApplication.class)
                    .buildAndInitialize();
        } catch (Exception e) {
            throw new RuntimeException("Error inicializando AWS Lambda Handler", e);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(LambdaHandler.class, args);
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
