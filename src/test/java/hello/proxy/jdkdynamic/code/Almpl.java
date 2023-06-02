package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Almpl implements AInterface{
    @Override
    public String call() {
        log.info("A 호출");
        return "A";
    }
}
