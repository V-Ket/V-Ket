package com.vket.db.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_seller")
    private String orderSeller;

    @Column(name = "order_goods_name")
    private String orderGoodsName;

    @Column(name = "order_price")
    private Long orderPrice;

    @Column(name = "order_quantity")
    private Long orderQuantity;

    @Column(name = "order_img")
    private String orderImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;
}
