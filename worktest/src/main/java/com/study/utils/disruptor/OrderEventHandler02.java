package com.study.utils.disruptor;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderEventHandler02 implements EventHandler<OrderEvent>{

    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        log.info("event-02: {}, sequence: {}, endOfBatch: {}", event, sequence, endOfBatch);
    }
}