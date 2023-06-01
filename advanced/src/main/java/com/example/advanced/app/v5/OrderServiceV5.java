package com.example.advanced.app.v5;

import com.example.advanced.trace.callback.TraceCallback;
import com.example.advanced.trace.callback.TraceTemplate;
import com.example.advanced.trace.logtrace.LogTrace;
import com.example.advanced.trace.template.AbstractTemplate;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId){
        template.execute("OrderService.orderItem()", (TraceCallback<Void>) () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
