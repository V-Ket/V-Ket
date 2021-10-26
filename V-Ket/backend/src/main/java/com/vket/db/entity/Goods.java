package com.vket.db.entity;

import com.vket.api.request.GoodsUpdateReq;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
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

//    @Builder
//    public Goods(String goodsName, Long goodsPrice, String goodsImg, Long goodsQuantity, String goodsContent) {
//        this.goodsName = goodsName;
//        this.goodsPrice = goodsPrice;
//        this.goodsImg = goodsImg;
//        this.goodsQuantity = goodsQuantity;
//        this.goodsContent = goodsContent;
//        // storeId로 찾아서 store 저장하기?
//    }

    @Builder
    public Goods(String goodsName, Long goodsPrice, String goodsImg, Long goodsQuantity, String goodsContent, Store store) {
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsImg = goodsImg;
        this.goodsQuantity = goodsQuantity;
        this.goodsContent = goodsContent;
        this.store = store;
    }

    public void updateGoodsInfo(GoodsUpdateReq goodsUpdateReq) {
        this.goodsName = goodsUpdateReq.getGoodsName();
        this.goodsPrice = goodsUpdateReq.getGoodsPrice();
        this.goodsImg = goodsUpdateReq.getGoodsImg();
        this.goodsQuantity = goodsUpdateReq.getGoodsQuantity();
        this.goodsContent = goodsUpdateReq.getGoodsContent();
    }
}
