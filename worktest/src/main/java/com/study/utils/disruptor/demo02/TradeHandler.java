package com.study.utils.disruptor.demo02;

import java.util.UUID;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class TradeHandler implements EventHandler<Trade>, WorkHandler<Trade> {  
      
    @Override  
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {  
        this.onEvent(event);  
    }  
  
    @Override  
    public void onEvent(Trade event) throws Exception {
        event.setId(UUID.randomUUID().toString());
        System.out.println(event.getId());  
    }  
}