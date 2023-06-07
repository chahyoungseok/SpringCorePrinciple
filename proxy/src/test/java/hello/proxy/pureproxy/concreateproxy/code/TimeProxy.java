package hello.proxy.pureproxy.concreateproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConCreateLogic{

    private ConCreateLogic conCreateLogic;

    public TimeProxy(ConCreateLogic conCreateLogic) {
        this.conCreateLogic = conCreateLogic;
    }

    @Override
    public String operation() {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        String result = conCreateLogic.operation();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("TimeDecorator 종료 resultTime={}ms", resultTime);
        return result;
    }
}
