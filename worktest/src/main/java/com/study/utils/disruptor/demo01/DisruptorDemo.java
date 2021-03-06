package com.study.utils.disruptor.demo01;

import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;

@Slf4j
public class DisruptorDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1,创建
        Disruptor<OrderEvent> disruptor = new Disruptor<>(
                OrderEvent::new,
                1024 * 1024,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new YieldingWaitStrategy()
        );
        disruptor.handleEventsWith(new OrderEventHandler01()).then(new OrderEventHandler03());
        disruptor.setDefaultExceptionHandler(new ExceptionHandler<OrderEvent>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, OrderEvent event) {
                log.debug("handleEventException");
            }

            @Override
            public void handleOnStartException(Throwable ex) {
                log.debug("handleOnStartException");
            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                log.debug("handleOnShutdownException");
            }
        });
        disruptor.start();
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        OrderEventProducer eventProducer = new OrderEventProducer(ringBuffer);
        OrderEventProducerWithTranslator producer =
                new OrderEventProducerWithTranslator(ringBuffer);
        for (int i = 0; i < 2; i++) {
            producer.onData("xiaoxi"+i);
        }
    }
}
