package hello.proxy.pureproxy.concreateproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConCreateLogic {

    public String operation(){
        log.info("ConCreate Logic 실행");
        return "data";
    }
}
