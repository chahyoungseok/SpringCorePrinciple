package com.example.advanced.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    @Test
    void callbackV1(){
        TimeLogTemplate template1 = new TimeLogTemplate();
        template1.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        TimeLogTemplate template2= new TimeLogTemplate();
        template2.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    @Test
    void callbackV2(){
        TimeLogTemplate template1 = new TimeLogTemplate();
        template1.execute(() -> log.info("비즈니스 로직1 실행"));

        TimeLogTemplate template2= new TimeLogTemplate();
        template2.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
