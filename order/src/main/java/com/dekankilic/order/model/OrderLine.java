package com.dekankilic.order.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer productId; // This is the id of the Product in Product Domain
}
