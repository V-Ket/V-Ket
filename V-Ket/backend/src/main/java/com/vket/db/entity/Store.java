package com.vket.db.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id")
    private Long storeId;

    @OneToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "island_id")
    private Island island;

    @OneToMany(mappedBy = "store")
    private List<Goods> goods = new ArrayList<>();

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_content")
    private String storeContent;

    @Column(name = "store_url")
    private String storeUrl;

    @Column(name = "store_status")
    private Long storeStatus;

    @Column(name = "store_island_num")
    private Long storeIslandNum;


}
