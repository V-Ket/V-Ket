package com.vket.db.entity;

import com.vket.api.request.GoodsUpdateReq;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_price")
    private Long goodsPrice;

    @Column(name = "goods_img")
    private String goodsImg;

    @Column(name = "goods_quantity")
    private Long goodsQuantity;

    @Column(name = "goods_content")
    private String goodsContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public void updateQuantity(Long goodsQuantity){
        this.goodsQuantity = goodsQuantity;
    }
}
