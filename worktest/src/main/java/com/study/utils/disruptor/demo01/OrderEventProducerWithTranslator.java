package com.study.utils.disruptor.demo01;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class OrderEventProducerWithTranslator {
    private final RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducerWithTranslator(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<OrderEvent, String> TRANSLATOR =
            (event, sequence, str) -> event.setId(str);

    public void onData(String str) {
        ringBuffer.publishEvent(TRANSLATOR, str);
    }
}
