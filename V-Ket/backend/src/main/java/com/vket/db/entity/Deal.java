package com.vket.db.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_id")
    private Long dealId;

    @Column(name = "deal_seller")
    private String dealSeller;

    @Column(name = "deal_goods_name")
    private String dealGoodsName;

    @Column(name = "deal_price")
    private Long dealPrice;

    @Column(name = "deal_quantity")
    private Long dealQuantity;

    @Column(name = "deal_img")
    private String dealImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    public void updatePurchase(Purchase purchase){
        this.purchase = purchase;
    }
}
