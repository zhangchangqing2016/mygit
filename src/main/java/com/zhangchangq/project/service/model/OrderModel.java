package com.zhangchangq.project.service.model;

import java.math.BigDecimal;

/**
 * 用户下单交易模型
 */
public class OrderModel {
    //交易号
    private String id;
    //购买的用户id
    private Integer userId;
    //商品号
    private Integer itemId;
    //购买数量
    private BigDecimal itemPrice;

    private Integer amount;
    //金额
    private BigDecimal orderPrice;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }



    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", itemPrice=" + itemPrice +
                ", amount=" + amount +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
