package com.example.advanced.trace.strategy.code;

import com.example.advanced.trace.strategy.code.strategy.ContextV2;
import com.example.advanced.trace.strategy.code.strategy.Strategy;
import com.example.advanced.trace.strategy.code.strategy.StrategyLogic1;
import com.example.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1(){
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2(){
        ContextV2 context1 = new ContextV2();
        context1.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        ContextV2 context2 = new ContextV2();
        context2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    @Test
    void strategyV3(){
        ContextV2 context1 = new ContextV2();
        context1.execute(() -> log.info("비즈니스 로직1 실행"));

        ContextV2 context2 = new ContextV2();
        context2.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
