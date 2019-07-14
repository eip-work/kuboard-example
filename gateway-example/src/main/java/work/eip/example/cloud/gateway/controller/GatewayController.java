package work.eip.example.cloud.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author: colin
 * @Date: 2019/2/22 13:35
 * @Description:
 * @Version: V1.0
 */
@RestController
public class GatewayController {

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }


}
