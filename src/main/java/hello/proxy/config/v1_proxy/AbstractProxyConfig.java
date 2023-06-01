package hello.proxy.config.v1_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.abstract_proxy.OrderControllerAbstractProxy;
import hello.proxy.config.v1_proxy.abstract_proxy.OrderRepositoryAbstractProxy;
import hello.proxy.config.v1_proxy.abstract_proxy.OrderServiceAbstractProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AbstractProxyConfig {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace trace){
        OrderControllerV2 orderControllerV2 = new OrderControllerV2(orderServiceV2(trace));
        return new OrderControllerAbstractProxy(orderControllerV2, trace);
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace trace) {
        OrderServiceV2 orderServiceV2 = new OrderServiceV2(orderRepositoryV2(trace));
        return new OrderServiceAbstractProxy(orderServiceV2, trace);
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace trace) {
        OrderRepositoryV2 orderRepositoryV2 = new OrderRepositoryV2();
        return new OrderRepositoryAbstractProxy(orderRepositoryV2, trace);
    }
}
