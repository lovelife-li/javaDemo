package com.study.utils.disruptor.demo01;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderEventHandler01 implements EventHandler<OrderEvent>, WorkHandler<OrderEvent> {
    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) {
        log.info("event-01: {}, sequence: {}, endOfBatch: {}", event, sequence, endOfBatch);
    }
    @Override
    public void onEvent(OrderEvent event) {
        log.info("event-1: {}", event);
    }
}