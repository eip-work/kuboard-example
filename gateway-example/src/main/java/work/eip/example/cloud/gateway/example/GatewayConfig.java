package work.eip.example.cloud.gateway.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile("example")
@Configuration
@Slf4j
public class GatewayConfig {

    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }

    @Bean
    public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
        log.info("profile example");
        return new RequestTimeGatewayFilterFactory();
    }


}
