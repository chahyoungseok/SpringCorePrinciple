package com.example.advanced.app.v5;

import com.example.advanced.trace.callback.TraceTemplate;
import com.example.advanced.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    /**
     * OrderControllerV5 자체가 @RestController로 등록 되있기 때문에 싱글톤으로 관리된다.
     * 그러므로 생성자도 단 1번 호출되므로, TraceTemplate 도 한번만 생성되게된다.
     *
     * **/
    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId){
        return template.execute("OrderController.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });

    }
}
