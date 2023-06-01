package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Bean 으로 등록되면 파라미터 처럼 사용가능
 * Spring Bean 에 Proxy를 등록하고 Proxy가 실제 객체들을 참조하게 만듬.
 * **/

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace){
        OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderServiceV1(trace));
        return new OrderControllerInterfaceProxy(controllerImpl, trace);
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {
        OrderServiceV1Impl orderServiceImpl = new OrderServiceV1Impl(orderRepositoryV1(trace));
        return new OrderServiceInterfaceProxy(orderServiceImpl, trace);
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryV1Impl orderRepositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(orderRepositoryImpl, trace);
    }
}
