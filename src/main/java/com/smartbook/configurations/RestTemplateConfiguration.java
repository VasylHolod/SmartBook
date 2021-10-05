package com.smartbook.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Configuration
@PropertySource("classpath:oxford.prop")
public class RestTemplateConfiguration {

    @Value( "${oxford.app_id}" )
    private String app_id;
    @Value( "${oxford.app_key}" )
    private String app_key;

    private HttpHeaders header() {
        HttpHeaders header = new HttpHeaders();
        header.add("Accept", "application/json");
        header.add("app_id", app_id);
        header.add("app_key", app_key);
        return header;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());
        restTemplate.setInterceptors(List.of(new MyRestTemplateInterceptor()));
        return restTemplate;
    }

    private class MyRestTemplateInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            request.getHeaders().addAll(header());
            HttpStatus status = execution.execute(request, body).getStatusCode();
            System.out.println("interseptor = " + status);
            return execution.execute(request, body);
        }
    }

    public static class CustomResponseErrorHandler extends DefaultResponseErrorHandler {
        @Override
        public void handleError(ClientHttpResponse clienthttpresponse) throws IOException {
            if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {
//                System.out.println(HttpStatus.FORBIDDEN + " response. Throwing authentication exception");
                throw new AuthenticationServiceException(clienthttpresponse.getStatusCode().toString());
            }
        }

        @Override
        public boolean hasError(ClientHttpResponse clienthttpresponse) throws IOException {
            if (clienthttpresponse.getStatusCode() != HttpStatus.OK) {
//                System.out.println("Status code: " + clienthttpresponse.getStatusCode());
                if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {
//                    System.out.println("Call returned a error 403 forbidden response ");
                    return true;
                }
            }
            return false;
        }
    }
}
