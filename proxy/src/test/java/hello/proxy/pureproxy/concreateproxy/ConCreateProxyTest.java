package hello.proxy.pureproxy.concreateproxy;

import hello.proxy.pureproxy.concreateproxy.code.ConCreateClient;
import hello.proxy.pureproxy.concreateproxy.code.ConCreateLogic;
import hello.proxy.pureproxy.concreateproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConCreateProxyTest {

    @Test
    void noProxy(){
        ConCreateLogic conCreateLogic = new ConCreateLogic();
        ConCreateClient client = new ConCreateClient(conCreateLogic);

        client.execute();
    }

    @Test
    void addProxy(){
        ConCreateLogic conCreateLogic = new ConCreateLogic();
        TimeProxy timeProxy = new TimeProxy(conCreateLogic);
        ConCreateClient client = new ConCreateClient(timeProxy);

        client.execute();
    }
}
