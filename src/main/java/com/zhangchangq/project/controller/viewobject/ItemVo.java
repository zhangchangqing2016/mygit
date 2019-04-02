package com.zhangchangq.project.controller.viewobject;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public class ItemVo {

    private Integer id;
    //商品名
    private String title;
    //价格
    private BigDecimal price;
    //商品库存
    private Integer stock;

    //商品的描述
    private String description;
    //商品的销量
    private Integer sales;
    //商品描述图片
    private String imgUrl;
    //记录商品是否在秒杀活动中,以及对于的状态,0表示没有秒杀活动,1表示秒杀活动待开始,2表示秒杀活动进行中
    private Integer promoStatus;
    //秒杀活动价格
    private BigDecimal promoPrice;
    //秒杀活动id
    private Integer promoId;
    //秒杀活动开始时间
    private DateTime startDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getPromoStatus() {
        return promoStatus;
    }

    public void setPromoStatus(Integer promoStatus) {
        this.promoStatus = promoStatus;
    }

    public BigDecimal getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(BigDecimal promoPrice) {
        this.promoPrice = promoPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "ItemVo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", sales=" + sales +
                ", imgUrl='" + imgUrl + '\'' +
                ", promoStatus=" + promoStatus +
                ", promoPrice=" + promoPrice +
                ", promoId=" + promoId +
                ", startDate=" + startDate +
                '}';
    }
}
