package com.api.openapiclientsdk;

import com.api.openapiclientsdk.client.OpenApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuchengji
 * @date 2023年03月13日下午4:05
 * @Description
 */
@Configuration
@ConfigurationProperties("openapi.client")
@Data
@ComponentScan
public class OpenApiClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public OpenApiClient openApiClient(){
        return  new OpenApiClient(accessKey,secretKey);
    }
}
