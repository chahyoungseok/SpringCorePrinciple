package hello.proxy.config.v1_proxy.abstract_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

/**
 * 자식 클래스는 부모 클래스의 생성자를 호출해야하므로 super(null)을 해야한다.
 * 파라미터가 없는 기본 생성자가 부모 클래스에 있었다면 자동으로 super()을 호출했을 것이다.
 * **/

public class OrderControllerAbstractProxy extends OrderControllerV2 {

    private final OrderControllerV2 target;
    private final LogTrace trace;

    public OrderControllerAbstractProxy(OrderControllerV2 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderController.request()");
            String result = target.request(itemId);
            trace.end(status);

            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
