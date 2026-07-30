package py.ecommerce.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;
import py.ecommerce.order.decoder.GenericFeignErrorDecoder;

@Configuration
public class FeingConfig {

    @Bean
    public ErrorDecoder decoder(){
        return new GenericFeignErrorDecoder();
    }
}
