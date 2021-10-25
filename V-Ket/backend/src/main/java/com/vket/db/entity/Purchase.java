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
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long purchaseId;

    @Column(name = "purchase_status")
    private String purchaseStatus;

    @OneToMany(mappedBy = "purchase")
    private List<Deal> deals = new ArrayList<>();
}
