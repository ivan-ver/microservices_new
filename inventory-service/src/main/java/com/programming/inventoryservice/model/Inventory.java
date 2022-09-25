package com.programming.inventoryservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "skuCode")
    private String skuCode;
    @Column(name = "quantity")
    private Integer quantity;

}
