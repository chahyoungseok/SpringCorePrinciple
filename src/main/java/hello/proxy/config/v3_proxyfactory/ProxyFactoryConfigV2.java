package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace trace){
        OrderRepositoryV2 orderRepositoryV2 = new OrderRepositoryV2();

        ProxyFactory proxyFactory = new ProxyFactory(orderRepositoryV2);
        proxyFactory.addAdvisor(getAdvisor(trace));

        return (OrderRepositoryV2) proxyFactory.getProxy();
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace trace){
        OrderServiceV2 orderServiceV2 = new OrderServiceV2(orderRepositoryV2(trace));

        ProxyFactory proxyFactory = new ProxyFactory(orderServiceV2);
        proxyFactory.addAdvisor(getAdvisor(trace));

        return (OrderServiceV2) proxyFactory.getProxy();
    }

    @Bean
    public OrderControllerV2 controllerV2(LogTrace trace){
        OrderControllerV2 controllerV2 = new OrderControllerV2(orderServiceV2(trace));

        ProxyFactory proxyFactory = new ProxyFactory(controllerV2);
        proxyFactory.addAdvisor(getAdvisor(trace));

        return (OrderControllerV2) proxyFactory.getProxy();
    }


    private Advisor getAdvisor(LogTrace trace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*, order*, save*");

        return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(trace));
    }
}
