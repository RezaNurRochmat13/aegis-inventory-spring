package com.aegis.inventory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction extends Auditing {
    public enum OrderStatus {
        PENDING, COMPLETED, CANCELLED, REFUND
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
