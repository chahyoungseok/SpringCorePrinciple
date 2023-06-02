package hello.proxy.config;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace){
        OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryV1Impl();

        return (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTraceBasicHandler(orderRepositoryV1, trace));
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace){
        OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRepositoryV1(trace));
        return (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceBasicHandler(orderServiceV1, trace));
    }

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace){
        OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(trace));
        return (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceBasicHandler(orderControllerV1, trace));
    }
}
