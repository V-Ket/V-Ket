package com.vket.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Island {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "island_id")
    private Long islandId;

    @Column(name = "islane_name")
    private String islandName;

    @OneToMany(mappedBy = "island")
    private List<Store> stores = new ArrayList<>();

}
