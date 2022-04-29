package com.druwa.order.model;

import com.druwa.order.entity.CafeOrder;
import com.druwa.order.entity.CafeOrderProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CafeOrderResponse {
    private Long id;
    private String orderName;
    private String orderDong;
    private String orderHo;
    private LocalDateTime orderDate;
    private String orderState;
    private List<CafeOrderProductResponse> cafeOrderProductList;

    public CafeOrderResponse(CafeOrder cafeOrder) {
        this.id = cafeOrder.getId();
        this.orderName = cafeOrder.getOrderName();
        this.orderDong = cafeOrder.getOrderDong();
        this.orderHo = cafeOrder.getOrderHo();
        this.orderDate = cafeOrder.getOrderDate();
        this.orderState = cafeOrder.getOrderState();
        this.cafeOrderProductList = getCafeOrderProductList(cafeOrder.getCafeOrderProductList());
    }

    private List<CafeOrderProductResponse> getCafeOrderProductList(List<CafeOrderProduct> list) {
        List<CafeOrderProductResponse> responseList = new ArrayList<>();
        list.forEach(cafeOrderProduct -> responseList.add(new CafeOrderProductResponse(cafeOrderProduct)));
        return responseList;
    }

    public Long getId() {
        return id;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getOrderDong() {
        return orderDong;
    }

    public String getOrderHo() {
        return orderHo;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getOrderState() {
        return orderState;
    }

    public List<CafeOrderProductResponse> getCafeOrderProductList() {
        return cafeOrderProductList;
    }
}
