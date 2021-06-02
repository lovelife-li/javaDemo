package com.study.javanewspecial.java8.demo03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//订单商品类
@Data
@AllArgsConstructor
@NoArgsConstructor
class OrderItem {
    private Long productId;//商品ID
    private String productName;//商品名称
    private Double productPrice;//商品价格
    private Integer productQuantity;//商品数量
}