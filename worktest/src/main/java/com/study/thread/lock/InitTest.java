package com.study.thread.lock;

class InitTest{
  boolean inited = false;
  synchronized void init(){
    if(inited){
      return;
    }
    // 省略 doInit 的实现
//    doInit();
    inited=true;
  }
}